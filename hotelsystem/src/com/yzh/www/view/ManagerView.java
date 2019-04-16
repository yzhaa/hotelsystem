package com.yzh.www.view;

import com.yzh.www.controller.ControlAction;
import com.yzh.www.controller.ControlActionImpl;
import com.yzh.www.entity.Order;
import com.yzh.www.entity.Room;
import com.yzh.www.entity.Service;
import com.yzh.www.util.MyTextField;
import javafx.collections.FXCollections;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Storage.MangerStorage;


public class ManagerView {
    private ControlAction controlAction;
    private TableView tableView;
    private ListView listViewOrder;
    private ListView listViewPoint;
    private ListView listViewService;
    private TextField priceTf;
    private TextField contenceTf;
    private TextField nameTf;
    private Stage stage;


    public ManagerView() {
        this.controlAction = new ControlActionImpl();
    }

    public MenuBar creatMenuBar(){
        MenuBar mb = new MyMenuBar().creatMenuBar();
        MenuItem mi3 = new MenuItem("查看评分");
        mb.getMenus().get(0).getItems().add(mi3);
        mb.getMenus().get(0).getItems().get(0).setOnAction((ActionEvent e)->{
            new  ChangeInfoView(new MangerStorage()).addStage().show();
        });
        mb.getMenus().get(0).getItems().get(1).setOnAction((ActionEvent e)->{
            new  AccountView().creatStage(2).show();
        });
        mb.getMenus().get(0).getItems().get(2).setOnAction((ActionEvent e)->{
            addCommentStage();
        });

        return mb;
    }

    public Scene creatScene() {
        GridPane gp = new GridPane();
        VBox vBox=new VBox();
        Scene scene = new Scene(gp);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(creatMenuBar(),addVSeviceVBox(),addOrderVBox());
        gp.setPadding(new Insets(20));
        gp.setVgap(30);
        gp.setVgap(20);
        gp.add(vBox,0,0);
        gp.add(addVRoomVBox(),1,0);
        updateAll();
        return scene;
    }


    public HBox creatHBox(){
        HBox hBox = new HBox();
        ImageView imageView=new ImageView(new Image("\\Image\\manager.jpg"));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Label label = new Label();
        label.setGraphic(imageView);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(label);
        return hBox;
    }

    public Stage changeRoom(Room room){
        Stage stage=new Stage();
        GridPane gp = new GridPane();
        Scene scene = new Scene(gp);
        Button button=new Button("确认");
        TextField tfname = new TextField();
        TextField tftype = new TextField();
        TextField tfinfo = new TextField();
        TextField tfprice = MyTextField.priceField();
        tfname.setText(room.getName());
        tfinfo.setText(room.getInfo());
        tfprice.setText(room.getPrice() + "");
        tftype.setText(room.getType());
        Label la1 = new Label("名字");
        Label la2 = new Label("类型");
        Label la3 = new Label("信息");
        Label la4 = new Label("价格");
        button.setOnAction((ActionEvent e)->{
            controlAction = new ControlActionImpl();
            room.setName(tfname.getText());
            room.setsPrice(tfprice.getText());
            room.setInfo(tfinfo.getText());
            room.setType(tftype.getText());
           if( controlAction.changeRoom(room, 1))
               stage.close();
            updateTv();
        });
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.addColumn(0,la1,la2,la3,la4);
        gp.addColumn(1,tfname,tftype,tfinfo,tfprice);
        gp.add(button,1,4);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("修改房间信息");
        return stage;
    }

    public TableView creatTableView(){
        MyTable myTable = new MyTable();
        TableView tv = myTable.roomTV();
        TableColumn<Room, String> tc = myTable.roomDTC(this);
        tableView = tv;
        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                changeRoom((Room) tv.getSelectionModel().getSelectedItem()).show();
            }
        });
        tv.setPrefWidth(520);
        tc.setMaxWidth(50);
        tc.setSortable(false);
        tv.getColumns().add(tc);
        return tv;
    }

    public VBox addVRoomVBox() {
        VBox vBox = new VBox(20);
        HBox hBox = new HBox();
        TextField addstyle = new TextField();
        TextField addname = new TextField();
        Button addButton = new Button("Add");
        TextField addprice = MyTextField.priceField();
        TextField addinfo = new TextField();
        addname.setPromptText("输入房间名称");
        addstyle.setPromptText("输入房间类型");
        addprice.setPromptText("输入房间价格");
        addinfo.setPromptText("输入房间信息");
        addname.setMaxWidth(90);
        addstyle.setMaxWidth(90);
        addprice.setMaxWidth(90);
        addinfo.setMaxWidth(90);
        addButton.setOnAction((ActionEvent e) -> {
            Room room=new Room(new MangerStorage().getUser().getHotel(),addname.getText(),addstyle.getText(),
                                 addinfo.getText(), addprice.getText());
            addname.clear();
            addinfo.clear();
            addprice.clear();
            addstyle.clear();
            if(controlAction.changeRoom(room, 3)){
                tableView.getItems().add(room);
            }
        });
        vBox.setPadding(new Insets(25, 20, 20, 20));
        hBox.getChildren().addAll(addButton, addname, addstyle,addinfo, addprice);
        vBox.getChildren().addAll(creatHBox(),creatTableView(), hBox);
        return vBox;
    }

    public VBox addVSeviceVBox() {
        HBox hBox2 = new HBox();
        Button button1 = new Button("删除服务");
        Button button2 = new Button("添加服务");
        VBox vBox = new VBox();
        Label label = new Label("酒店服务:");
        listViewService = new ListView();
        button1.setMaxWidth(70);
        button1.setFont(Font.font(11));
        button1.setMaxWidth(70);
        button1.setOnAction((ActionEvent e) -> {
            controlAction.deleteService(((Service) listViewService.getSelectionModel().getSelectedItem()));
            updateService();
        });
        button2.setOnAction((ActionEvent e) -> {
            changeService(null, 2);
        });

        listViewService.setMaxWidth(180);
        listViewService.setMaxHeight(220);
        listViewService.setOnMouseClicked(event ->{
            if(event.getClickCount()==2){
                changeService((Service) listViewService.getSelectionModel().getSelectedItem(), 1);
            }
        } );

        hBox2.getChildren().addAll(button1,button2 );
        vBox.setSpacing(10);
        vBox.getChildren().setAll(label, listViewService, hBox2);
        return vBox;
    }

    public  VBox addOrderVBox(){
        HBox hBox = new HBox();
        Button button = new Button("删除订单");
        VBox vBox = new VBox();
        Label label = new Label("顾客订单:");
        listViewOrder = new ListView();
        listViewOrder.setMaxWidth(180);
        listViewOrder.setMaxHeight(220);
        button.setOnAction((ActionEvent e)->{
            controlAction.deleteOrder((Order) listViewOrder.getSelectionModel().getSelectedItem());
            updateLVS();
        });

        hBox.getChildren().addAll(label, button);
        hBox.setSpacing(40);
        vBox.setSpacing(10);
        vBox.getChildren().setAll(hBox ,listViewOrder);
        return vBox;
    }


    public void addCommentStage(){
        Stage stage = new Stage();
        MyPointVBox mpvb = new MyPointVBox();
        VBox vBox =mpvb .creatPointVBox();
        listViewPoint = mpvb.getListView();
        Scene scene = new Scene(vBox);
        listViewPoint.setItems(FXCollections.observableList(controlAction.loadComment()));
        stage.setTitle("评价");
        stage.setScene(scene);
        stage.show();
    }



    public void changeService(Service service,int choice){
        priceTf = MyTextField.accontTextField();
        contenceTf = new TextField();
        nameTf = new TextField();
        stage = new Stage();
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        Scene scene = new Scene(vBox,300,180);
        HBox hBox1 = new HBox(20);
        HBox hBox2 = new HBox(20);
        HBox hBox3 = new HBox(20);
        Label la1 = new Label("服务名字");
        Label la2 = new Label("服务价格");
        Label la3 = new Label("服务内容");
        Button btn = new Button("确定");

        if(service!=null){
            priceTf.setText(service.getPrice()+"");
            contenceTf.setText(service.getContent());
            nameTf.setText(service.getName());
        }

        btn.setOnAction(ActionEvent->{
            if (controlAction.changeService(nameTf.getText(), priceTf.getText(),
                    contenceTf.getText(), choice, service)) {
                updateService();
                stage.close();
            }
        });

        hBox1.getChildren().addAll(la1, nameTf);
        hBox2.getChildren().addAll(la2, priceTf);
        hBox3.getChildren().addAll(la3, contenceTf);
        vBox.getChildren().addAll(hBox1, hBox2 , hBox3,btn);
        stage.setResizable(false);
        if(choice==1){
            stage.setTitle("修改服务");
        }else {
            stage.setTitle("添加服务");
        }
        stage.setScene(scene);
        stage.show();
    }



    public void updateAll(){
        updateTv();
        updateLVS();
        updateService();
    }

    public void updateTv(){
        tableView.setItems(FXCollections.observableList(controlAction.loadRoomByManager()));
    }

    public void updateLVS(){
        listViewOrder.setItems(FXCollections.observableList(controlAction.loadOrder()));
    }

    public void updateService(){
       listViewService.setItems(FXCollections.observableList(controlAction.loadService()));
    }

}
