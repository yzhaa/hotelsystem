package com.yzh.www.view;

import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Room;
import com.yzh.www.serviceImpl.CustomServiceImpl;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CalendarView {
    private MangerImpl controlAction;
    private Scene scene;
    private Stage stage;
    private TableView<Room> tableView;
    private int roomId;
    private int roomPrice;
    private Date date;
    private String duration;
    private String[] selectStr;

    void addCalendarStage(int roomId,int roomPrice, TableView<Room> tv){
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        this.tableView = tv;
        stage = new Stage();
        GridPane gp = new GridPane();
        Label la1 = new Label("选择日期");
        Label la2 = new Label("输入入住天数");
        Button btn = new Button("确认");
        scene = new Scene(gp,250,300);
        ListView<String> lv = new ListView<>();
        TextField tf = MyTextField.accontTextField();
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

        ObservableList<String> ol= FXCollections.observableArrayList();
        for(int i=0;i<7;i++){
            ol.add(sdf.format(date));
            date.setTime(date.getTime()+ Constant.DAYTIME);
        }
        btn.setOnAction((ActionEvent)->{
            duration = tf.getText();
            scene.setRoot(addService());
        });
        lv.setPrefSize(150,200);
        lv.setItems(ol);
        gp.setAlignment(Pos.TOP_CENTER);
        gp.setVgap(10);
        gp.setPadding(new Insets(10));
        gp.addColumn(1,la1,lv,la2,tf,btn);
        stage.setTitle("预定房间");
        stage.setScene(scene);
        stage.show();
    }

    private VBox addService(){
        String[] strings = new CustomServiceImpl().getServiceNames();
        selectStr = new String[strings.length];
        VBox vBox = new VBox(10);
        Button btn=new Button("确定");
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.TOP_CENTER);
        for(int i=0;i<strings.length;i++){
            CheckBox cb = new CheckBox(strings[i]);
            vBox.getChildren().add(cb);
            final int k=i;
            cb.setOnAction((ActionEvent e)->{
                if(cb.isSelected()){
                    selectStr[k] = cb.getText();
                }
                else selectStr[k]=null;
            });
        }
        btn.setOnAction((ActionEvent e)->{
            controlAction = new MangerImpl();
            if(controlAction.creatOrder(this)){
                MyAlert.setAlert("成功预定",1);
                stage.close();
            }
        });
        vBox.getChildren().add(btn);
        return vBox;
    }
    public void update(){

    }

    public String getDuration() {
        return duration;
    }

    public TableView<Room> getTableView() {
        return tableView;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public Date getDate() {
        return date;
    }

    public String[] getSelectStr() {
        return selectStr;
    }
}
