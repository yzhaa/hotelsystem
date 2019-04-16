package com.yzh.www.view;

import com.yzh.www.controller.ControlAction;
import com.yzh.www.controller.ControlActionImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Order;
import com.yzh.www.service.CustomServiceImpl;
import com.yzh.www.util.MyTextField;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Storage.HotelStorage;

/**
 * 查看订单界面
 */

public class OrderView {
    private ListView listView;
    private TableView tableView;
    private ControlAction controlAction;

    public OrderView() {
        this.controlAction = new ControlActionImpl();
    }

    /**
     * 订单界面的初始化等
     */
    public   Stage creatEnrollStage(TableView tv,ListView lvp){
        tableView=tv;
        Stage stage=new Stage();
        GridPane gridPane=new GridPane();
        Scene scene=new Scene(gridPane);
        listView = new ListView();
        Button submit=new Button("提交评价");
        Button cancel=new Button("取消订单");
        TextField command = new TextField();
        TextField point = MyTextField.pointTextField();
        point.setPromptText("评分：1-10");
        point.setMaxWidth(100);
        command.setPromptText("评论");
        Label oil=new Label("订单：");
        listView.setPrefSize(250,250);
        listView.setItems(FXCollections.observableList(new CustomServiceImpl().findAllOrder()));

        cancel.setOnAction((ActionEvent)->{
                controlAction.deleteOrder(this);
        });
        submit.setOnAction((ActionEvent)->{
           controlAction.creatComment(command.getText(), point.getText(),
                    (Order) listView.getSelectionModel().getSelectedItem());
            controlAction.loadInfo(null, lvp);
        });

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(point, 0, 5);
        gridPane.add(oil,0,0);
        gridPane.add(listView,0,1);
        gridPane.add(cancel,1,2);
        gridPane.add(command,0,6);
        gridPane.add(submit,1,6);
        stage.setScene(scene);
        stage.setTitle("订单信息");
        return stage;
    }

    /**
     * 订单界面的数据更新等
     */
    public void update(){
        CustomServiceImpl csi = new CustomServiceImpl();
        listView.setItems(FXCollections.observableList(csi.findAllOrder()));
        Hotel hotel = new HotelStorage().getHotel();
        if(hotel!=null){
            tableView.setItems(FXCollections.observableList(csi.findEnptyRoom(hotel.getId())));
        }
    }

    public ListView getListView() {
        return listView;
    }

}


