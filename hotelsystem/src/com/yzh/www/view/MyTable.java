package com.yzh.www.view;


import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Room;
import com.yzh.www.util.MyAlert;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * 管理员，顾客，超级管理员中对tableview的设定
 */

 class MyTable {

    /**
     * 显示房间信息tableview的设定
     * @return 返回设定好的tableview
     */
    TableView<Room> roomTV() {
       TableView<Room> tableView = new TableView<>();
       TableColumn<Room, String> tc1 = new TableColumn<>("房间名");
       TableColumn<Room, String> tc2 = new TableColumn<>("房间类型");
       TableColumn<Room, String> tc3 = new TableColumn<>("信息");
       TableColumn<Room, String> tc4 = new TableColumn<>("价格");
        tc1.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("infoProperty"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("priceProperty"));
        tc1.setMinWidth(100);
        tc2.setMinWidth(100);
        tc3.setMinWidth(200);
        tc4.setMinWidth(50);
        tc1.setSortable(false);
       tc2.setSortable(false);
       tc3.setSortable(false);
       tableView.setMaxHeight(500);
       tableView.setMinWidth(400);
       tableView.getColumns().addAll(tc1, tc2, tc3, tc4);
       return tableView;
      }


    /**
     * 显示酒店信息的tableview的设定
     * @return 返回用于显示酒店信息的tableview
     */

    TableView<Hotel> creatHTV(){
        TableView <Hotel>tableView = new TableView<>();
        TableColumn<Hotel, String> tc1 = new TableColumn<>("酒店名");
        TableColumn<Hotel, String> tc2 = new TableColumn<>("酒店类型");
        TableColumn<Hotel, String> tc3 = new TableColumn<>("信息");
        TableColumn<Hotel, String> tc4 = new TableColumn<>("管理员帐号");
        tc1.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("accontProperty"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("infoProperty"));
        tc1.setMinWidth(50);
        tc2.setMinWidth(100);
        tc3.setMinWidth(100);
        tc4.setMinWidth(50);
        tc1.setSortable(false);
        tc2.setSortable(false);
        tc3.setSortable(false);
        tc4.setSortable(false);
        tableView.setMaxHeight(500);
        tableView.setMinWidth(400);
        tableView.getColumns().addAll(tc1, tc2, tc3, tc4);
        return tableView;
       }

    /**
     * 对酒店中tableview中的删除TableColumn进行设定
     */

    TableColumn<Hotel,Button> hotelDTC(AdministratorView av)  {
         TableColumn<Hotel,Button> tableColumn = new TableColumn<>("删除");
         tableColumn.setCellValueFactory(param -> {
             Button button = new Button("删除");
             button.setOnAction(event -> {
                 av.cheack(new MangerImpl().changeHotel(param.getValue(), 2));
                 av.update();
             });
             return new SimpleObjectProperty<>(button);
         });
      return tableColumn;
     }

    /**
     * 对房间中tableview中的删除TableColumn进行设定
     */

    TableColumn<Room, Button> roomDTC(ManagerView mv) {
        TableColumn<Room, Button> tableColumn = new TableColumn<>("删除");
        tableColumn.setCellValueFactory(param -> {
            Button button = new Button("删除");
            button.setOnAction(event -> {
                switch (new MangerImpl().changeRoom(param.getValue(), 2)) {
                    case 2:
                        MyAlert.setAlert("删除成功", 1);
                        mv.updateAll();
                        break;
                    case 3:
                        MyAlert.setAlert("该房间有人住", 0);
                }
            });
            return new SimpleObjectProperty<>(button);
        });
        return tableColumn;
    }

    TableColumn<Room, Button> roomRTC(TableView<Room> tv) {
        TableColumn<Room, Button> tableColumn = new TableColumn<>("预约");
        tableColumn.setCellValueFactory(param -> {
            Button button = new Button("预约");
            button.setOnAction(event -> {
                Room room = param.getValue();
                new CalendarView().addCalendarStage(room.getId(), room.getPrice(), tv);
            });
            return new SimpleObjectProperty<>(button);
        });
        return tableColumn;
    }


}