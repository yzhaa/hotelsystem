package com.yzh.www.view;

import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.serviceImpl.AdministratorServiceImpl;
import com.yzh.www.util.Constant;
import com.yzh.www.util.MyAlert;
import com.yzh.www.util.MyTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Storage.AdministratorStorage;
import java.util.ArrayList;

/**
 * 该类是对超级管理员界面的布局
 */

public class AdministratorView {
    private TableView<Hotel>tableView;
    private int lastHotelId;
    private MangerImpl controlAction;

    /**
     * 对超级管理员布局的设置
     * @return 返回包含超级管理员界面布局的Stage对象
     */
    public Scene creatScene(){
        GridPane gp = new GridPane();
        VBox vBox=new VBox();
        Scene scene = new Scene(gp);
        vBox.setSpacing(20);
        gp.setPadding(new Insets(20));
        gp.add(leftVBox(),0,0);
        gp.add(RightVBox(),1,0);
        gp.setVgap(30);
        gp.setVgap(20);
        update();
        return scene;
    }

    /**
     * 对显示酒店的tableview进行刷新
     */

    public void update(){
        ArrayList<Hotel> hotellist = new AdministratorServiceImpl().findAllHotel();
        if(hotellist.size()!=0){
            lastHotelId = ( hotellist.get(hotellist.size()-1)).getId() ;
        }
        tableView.refresh();
        ObservableList<Hotel> data = FXCollections.observableList(hotellist);
        tableView.setItems(FXCollections.observableList(data));
    }

    /**
     * 超级管理员界面左边那一块布局的设定
     */

    private VBox leftVBox(){
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        Button btn = new Button("修改资料");
        Label label = new Label();
        ImageView iv = new ImageView(new Image("\\Image\\administrator.jpg"));
        btn.setOnAction(event ->
            new ChangeInfoView(new AdministratorStorage()).addStage().show());

        iv.setFitHeight(200);
        iv.setFitWidth(200);
        label.setGraphic(iv);
        vBox.getChildren().addAll(btn, label);
        return vBox;
    }

    /**
     * 超级管理员界面右边那一块的布局及设定
     */

    private VBox RightVBox(){
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(20));
        TextField addname = new TextField();
        Button addButton = new Button("Add");
        TextField addtype = new TextField();
        TextField addinfo = new TextField();
        TextField addManaAccont = MyTextField.accontTextField();
        HBox hBox = new HBox(10);
        addname.setPromptText("输入酒店名称");
        addtype.setPromptText("输入酒店类型");
        addManaAccont.setPromptText("输入管理员帐号");
        addinfo.setPromptText("输入酒店信息");

        addButton.setOnAction((ActionEvent)->{
            controlAction=new MangerImpl();
            Hotel hotel = new Hotel(lastHotelId + 1, addname.getText(), addtype.getText(), addinfo.getText(),
                    addManaAccont.getText());
           if (cheack (controlAction.changeHotel(hotel, 3))==9){
                addname.clear();
                addinfo.clear();
                addtype.clear();
                addManaAccont.clear();
                update();
            }
        });

        addname.setMaxWidth(90);
        addtype.setMaxWidth(90);
        addManaAccont.setMaxWidth(105);
        addinfo.setMaxWidth(90);
        hBox.getChildren().addAll(addname, addtype, addinfo, addManaAccont, addButton);
        vBox.getChildren().addAll(creatFinalTv(), hBox);
        return vBox;
    }

    /**
     * 显示酒店信息的tableview的布局及设定
     * @return 返回显示酒店信息的tableview
     */

     int cheack(int result) {
        switch (result) {
            case 1:
                MyAlert.setAlert("请填入完整信息并且酒店类型需为：\n" + Constant.FIVESTARS
                        + "\\" + Constant.FOURSTARS + "\\" + Constant.THREESTARS + "\\" + Constant.CHEAPSTARS, 0);
                break;
            case 2:
                MyAlert.setAlert("酒店类型需为：\n" + Constant.FIVESTARS + "\\" + Constant.FOURSTARS + "\\" +
                        Constant.THREESTARS + "\\" + Constant.CHEAPSTARS, 0);
                break;
            case 3:
                MyAlert.setAlert("无此管理员账户", 0);
                break;
            case 4:
                MyAlert.setAlert("修改成功", 1);
                break;
            case 5:
                MyAlert.setAlert("成功删除", 1);
                break;
            case 6:
                MyAlert.setAlert("该酒店还有人住\n请先删除顾客的订单",0);
                break;
            case 7:
                MyAlert.setAlert("无此管理员", 0);
                break;
            case 8:
                MyAlert.setAlert("该管理员有酒店了", 0);
                break;
            case 9:
                MyAlert.setAlert("创建成功", 1);
        }
        return result;
    }

    private TableView creatFinalTv(){
        MyTable myTable = new MyTable();
        TableColumn<Hotel,Button> tc = myTable.hotelDTC(this);
        TableView<Hotel> tv = myTable.creatHTV();
        (tv.getColumns().get(2)).setMinWidth(200);
        (tv.getColumns().get(0)).setMinWidth(100);
        tv.setPrefWidth(540);
        tc.setPrefWidth(50);
        tc.setSortable(false);
        tv.getColumns().add(tc);
        tableView =tv;
        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                changeHotel( tv.getSelectionModel().getSelectedItem()).show();
            }
        });
        return tv;
    }

    /**
     * 对修改酒店信息弹窗的设定
     * @param hotel 所要修改的酒店对象
     * @return 返回包含酒店信息设置界面的Stage对象
     */
    private Stage changeHotel(Hotel hotel) {
            Stage stage=new Stage();
            GridPane gp = new GridPane();
            Scene scene = new Scene(gp);
            Button button=new Button("确认");
            TextField tfname = new TextField();
            TextField tftype = new TextField();
            TextField tfinfo = new TextField();
            TextField tfaccont = MyTextField.accontTextField();
            tfname.setText(hotel.getName());
            tftype.setText(hotel.getType());
            tfinfo.setText(hotel.getInfo());
            tfaccont.setText(""+hotel.getManager().getAccont());
            Label la1 = new Label("名字");
            Label la2 = new Label("类型");
            Label la3 = new Label("信息");
            Label la4 = new Label("帐号");


            button.setOnAction((ActionEvent e)->{
                controlAction = new MangerImpl();
                hotel.setName(tfname.getText());
                hotel.setInfo(tfinfo.getText());
                hotel.setType(tftype.getText());
                hotel.setManagerAccont(tfaccont.getText());
                if( cheack(controlAction.changeHotel(hotel, 1))==4){
                    stage.close();
                }
                update();
            });
            gp.setAlignment(Pos.CENTER);
            gp.setPadding(new Insets(20));
            gp.setHgap(10);
            gp.setVgap(10);
            gp.addColumn(0,la1,la2,la3,la4);
            gp.addColumn(1,tfname,tftype,tfinfo,tfaccont);
            gp.add(button,1,4);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("修改酒店信息");
            return stage;
        }

    }



