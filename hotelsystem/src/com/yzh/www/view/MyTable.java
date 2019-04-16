package com.yzh.www.view;

import com.yzh.www.controller.ControlAction;
import com.yzh.www.controller.ControlActionImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Room;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * 管理员，顾客，超级管理员中对tableview的设定
 */

public class MyTable {

    /**
     * 显示房间信息tableview的设定
     * @return 返回设定好的tableview
     */
      public TableView roomTV() {
       TableView tableView = new TableView<>();
       TableColumn<Room, String> tc1 = new TableColumn("房间名");
       TableColumn<Room, String> tc2 = new TableColumn("房间类型");
       TableColumn<Room, String> tc3 = new TableColumn("信息");
       TableColumn<Room, String> tc4 = new TableColumn("价格");
       tc1.setMinWidth(100);
       tc2.setMinWidth(100);
       tc3.setMinWidth(200);
       tc4.setMinWidth(50);
       tc1.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
       tc2.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));
       tc3.setCellValueFactory(new PropertyValueFactory<>("infoProperty"));
       tc4.setCellValueFactory(new PropertyValueFactory<>("priceProperty"));
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

       public TableView creatHTV(){
        TableView tableView = new TableView<>();
        TableColumn<Hotel, String> tc1 = new TableColumn("酒店名");
        TableColumn<Hotel, String> tc2 = new TableColumn("酒店类型");
        TableColumn<Hotel, String> tc3 = new TableColumn("信息");
        TableColumn<Hotel, String> tc4 = new TableColumn<>("管理员帐号");
        tc1.setMinWidth(50);
        tc2.setMinWidth(100);
        tc3.setMinWidth(100);
        tc4.setMinWidth(50);
        tc1.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("accontProperty"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("infoProperty"));
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
     * @param av
     * @return
     */

     public   TableColumn hotelDTC(AdministratorView av)  {
      TableColumn tableColumn = new TableColumn("删除");
      tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, Button>,
              ObservableValue<Button>>() {
            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<Hotel, Button> param) {
             Button button = new Button("删除");
             button.setOnAction(event ->  {
                     new ControlActionImpl().changeHotel( param.getValue(), 2);
                     av.update();
                   });
              return  new SimpleObjectProperty<>(button);
            }
      });
      return tableColumn;
     }
/**
 *  对房间中tableview中的删除TableColumn进行设定
 */

     public   TableColumn roomDTC(ManagerView mv)  {
         TableColumn tableColumn = new TableColumn("删除");
         tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Button>,
                 ObservableValue<Button>>() {
             @Override
             public ObservableValue<Button> call(TableColumn.CellDataFeatures<Room, Button> param) {
              Button button = new Button("删除");
              button.setOnAction(event ->  {
                  new ControlActionImpl().changeRoom( param.getValue(),2);
                  mv.updateAll();
           });
           return  new SimpleObjectProperty<>(button);
          }
         });
         return tableColumn;
        }


    public   TableColumn roomRTC(TableView tv)  {
        TableColumn tableColumn = new TableColumn("预约");
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Button>,
                ObservableValue<Button>>() {
            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<Room, Button> param) {
                Button button = new Button("预约");
                button.setOnAction(event ->  {
                        Room room = param.getValue();
                        new CalendarView().addCalendarStage(room.getId(),room.getPrice(),tv);
                });
                return  new SimpleObjectProperty<>(button);
            }
        });
        return tableColumn;
    }




}