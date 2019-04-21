package com.yzh.www.view;

import com.yzh.www.manger.Manger;
import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Room;
import com.yzh.www.factory.ServiceFactory;
import com.yzh.www.util.Constant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Storage.HotelStorage;
import Storage.CustomerStorage;
import java.util.ArrayList;


public class CustomView {
    private Manger manger;
    private int hotelid;
    private ArrayList<Hotel> arrayList;
    private ListView<TextArea> lvService;
    private ListView<Hotel> lVHotel;
    private ListView <TextArea>lVpoint;
    private TextArea textAreaComment;
    private TextArea textAreaService;
    private ArrayList<TextArea> arealistService;
    private ArrayList<TextArea> arealistComment;
    private TableView<Room> tableView;
    private Stage stage;

    public CustomView(Stage stage) {
        this.stage = stage;
    }

    public Scene fistScene() {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 700, 500);
        borderPane.setTop(addHBox(new CustomerStorage().getUser().getUserName()));
        borderPane.setCenter(addHotelGridPane());
        borderPane.setLeft(addLeftVBox());
        borderPane.setRight(displayHotelVbox());
        borderPane.setPadding(new Insets(0, 30, 20, 0));
        return scene;
    }

    private Scene secondScene() {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 1000, 500);
        borderPane.setTop(addHBox(new CustomerStorage().getUser().getUserName()));
        borderPane.setCenter(addGridPane());
        borderPane.setLeft(addTLeftVBox());
        borderPane.setRight(addRightVBox());
        borderPane.setPadding(new Insets(0, 30, 20, 0));
        return scene;
    }


    private HBox addHBox(String userName) {
        Label label = new Label("欢迎" + userName);
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10, 20, 10, 20));
        label.setFont(Font.font("Geneva", 20));
        hBox.getChildren().addAll(label);
        return hBox;
    }


    private VBox displayHotelVbox() {
        VBox vBox = new VBox();
        Label la = new Label("酒店列表");
        lVHotel = new ListView<>();
        lVHotel.setMaxHeight(250);
        lVHotel.setMaxWidth(150);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(la, lVHotel);
        lVHotel.setOnMouseClicked(event -> {
            HotelStorage hf = new HotelStorage();
            hf.setHotel(lVHotel.getSelectionModel().getSelectedItem());
            if (hf.getHotel() != null) {
                hotelid = hf.getHotel().getId();
                manger = new MangerImpl();
                stage.setScene(secondScene());
                update(hotelid);
            }
        });
        return vBox;
    }

    private TableView creatTableView() {
        MyTable myTable = new MyTable();
        manger = new MangerImpl();
        tableView = myTable.roomTV();
        TableColumn<Room, Button> tc = myTable.roomRTC(tableView);
        tc.setSortable(false);
        tc.setMaxWidth(50);
        tableView.getColumns().add(tc);
        tableView.setItems(FXCollections.observableArrayList(manger.loadEmptyRoom()));
        return tableView;
    }


    private GridPane addHotelGridPane() {
        ImageView imageView = new ImageView(new Image("\\Image\\hotel.jpg"));
        GridPane gp = new GridPane();
        Text text = new Text("       酒店查询");
        Label label = new Label("", imageView);
        TextField tf = new TextField();
        ChoiceBox<String> cb = new ChoiceBox<>();
        Button button = new Button("查找");
        HBox hBox = new HBox();
        gp.setAlignment(Pos.TOP_CENTER);
        gp.setPadding(new Insets(20));

        button.setOnAction((ActionEvent e) -> {
            arrayList = ServiceFactory.getManger().loadHotel(cb.getSelectionModel().getSelectedItem(), tf);
            lVHotel.setItems(FXCollections.observableArrayList(arrayList));
        });
        gp.setVgap(30);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        text.setFont(Font.font(24));
        hBox.setSpacing(80);
        hBox.getChildren().addAll(cb, button);
        gp.addColumn(0, text, label, tf, hBox);
        cb.setItems(FXCollections.observableArrayList(Constant.FIVESTARS, Constant.FOURSTARS, Constant.THREESTARS,
                Constant.CHEAPSTARS));
        tf.setPromptText("输入要查找的酒店");
        return gp;
    }


    private GridPane addGridPane() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.add(creatTableView(), 0, 2);
        return gp;
    }


    private VBox addRightVBox() {
        VBox vBox = new VBox();
        Label label = new Label("酒店服务");
        lvService = new ListView<>();
        lvService.setMaxWidth(200);
        vBox.setSpacing(15);
        vBox.getChildren().addAll(label, lvService);
        return vBox;
    }

   void update(int hotelid) {
       System.out.println(hotelid);
        if(hotelid!=0){
            textAreaService.setText( (String) ServiceFactory.getManger().loadService(2, hotelid).get(0));
            lvService.setItems(FXCollections.observableArrayList(arealistService));
            textAreaComment.setText((String)( ServiceFactory.getManger().loadComment(2,hotelid)).get(0));
            lVpoint.setItems(FXCollections.observableArrayList(arealistComment));
        }
    }

    private VBox addTLeftVBox() {
        arealistComment = new ArrayList<>();
        arealistService = new ArrayList<>();
        VBox vBox = addLeftVBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(10));
        MyPointVBox mpvb = new MyPointVBox();
        mpvb.creatPointVBox();
        Label label = mpvb.getLabel();
        textAreaComment = new TextArea();
        textAreaService = new TextArea();

        Button button = new Button("返回");
        lVpoint = mpvb.getListView();

        textAreaComment.setEditable(false);
        textAreaComment.setWrapText(true);
        textAreaComment.setPrefWidth(180);
        textAreaComment.setPrefHeight(250);
        textAreaService.setWrapText(true);
        textAreaService.setEditable(false);
        textAreaService.setPrefWidth(180);
        textAreaService.setPrefHeight(380);
        arealistComment.add(textAreaComment);
        arealistService.add(textAreaService);
        vBox.getChildren().addAll(label, lVpoint, button);
        button.setOnAction((ActionEvent e) ->
                stage.setScene(fistScene()));
        return vBox;
    }

    private VBox addLeftVBox() {
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 40, 20, 20));
        MenuBar mb = new MyMenuBar().creatMenuBar();
        MenuItem mi3 = new MenuItem("查看订单");
        mb.getMenus().get(0).getItems().add(mi3);
        mb.getMenus().get(0).getItems().get(0).setOnAction((ActionEvent e) ->
                new ChangeInfoView(new CustomerStorage()).addStage().show());

        mb.getMenus().get(0).getItems().get(2).setOnAction((ActionEvent e) ->
                new OrderView().creatEnrollStage(this).show());

        mb.getMenus().get(0).getItems().get(1).setOnAction(event ->
                new AccountView().creatStage(1).show());

        vBox.getChildren().add(mb);
        return vBox;
    }

    TableView<Room> getTableView() {
        return tableView;
    }

    public int getHotelid() {
        return hotelid;
    }
}
