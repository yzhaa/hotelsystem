package com.yzh.www.view;

import com.yzh.www.manger.Manger;
import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Order;
import com.yzh.www.entity.Room;
import com.yzh.www.serviceImpl.CustomServiceImpl;
import com.yzh.www.util.MyAlert;
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
    private ListView<Order> listView;
    private TableView<Room> tableView;
    private Manger manger;

    OrderView() {
        this.manger = new MangerImpl();
    }

    /**
     * 订单界面的初始化等
     */
    Stage creatEnrollStage(CustomView customView){
        tableView=customView.getTableView();
        Stage stage=new Stage();
        GridPane gridPane=new GridPane();
        Scene scene=new Scene(gridPane);
        listView = new ListView<>();
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
        if(manger.deleteOrder(this)) {
            MyAlert.setAlert("删除成功",1);
        }
        else {
            MyAlert.setAlert("请选择要删除的订单", 0);
        }
        });

        submit.setOnAction((ActionEvent)->{
            switch (manger.creatComment(command.getText(), point.getText(),
                     listView.getSelectionModel().getSelectedItem())){
                case 1:
                    MyAlert.setAlert("选择要评价的订单", 0);
                    break;
                case 2:MyAlert.setAlert("请输入评价的分数",0);
                    break;
                case 3:MyAlert.setAlert("评价成功",1);
                    customView.update(customView.getHotelid());
                    break;
            }
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


