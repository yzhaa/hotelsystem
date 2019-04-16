package com.yzh.www.view;

import com.yzh.www.controller.ControlAction;
import com.yzh.www.controller.ControlActionImpl;
import com.yzh.www.util.MyTextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainView extends Application {
    private ControlAction controlAction;
    private int choiceUser;
    private Stage  stage;
    TextField textField;
    PasswordField passwordField;
    RadioButton radioButton;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        stage.setResizable(false);
        addMainScene(primaryStage);
    }

    public HBox addoOerateHBox(){
        HBox hBox=new HBox();
        Button button1=new Button("登入");
        Button  button2=new Button("注册");

        button1.setOnAction((ActionEvent e)->{
            controlAction = new ControlActionImpl();
            controlAction.login(choiceUser, textField.getText(), passwordField.getText(), stage, radioButton.isSelected());
        });

        button2.setOnAction((ActionEvent e)->{
            new RegistView().addLogonStage().show();
        });

        hBox.setSpacing(50);
        hBox.getChildren().addAll(button2,button1);
        return hBox;
    }

    public ChoiceBox addChoiceBox(){
        ChoiceBox cb=new ChoiceBox(FXCollections.observableArrayList("顾客","管理员","超级管理员"));
        cb.getSelectionModel().selectedIndexProperty().addListener(((observable, oldValue, newValue) ->{
            switch (newValue.intValue()){
                case 0:choiceUser=1; break;
                case 1:choiceUser=2;break;
                case 2:choiceUser=3;break;
            }
        }));
        return cb;
    }

    public void addMainScene(Stage primaryStage){
        primaryStage.setTitle("TVHotel");
        GridPane pane=new GridPane();
        Scene scene=new Scene(pane,400, 300);
        Text text=new Text("Welcome to TV Hotel");
        textField = MyTextField.accontTextField();
        passwordField = MyTextField.passwordTextFiled();
        Label  label1=new Label("账号");
        Label  label2=new Label("密码");
        radioButton=new RadioButton("记住密码");
        controlAction=new ControlActionImpl();
        
        controlAction.getLoginInfo(textField,passwordField,radioButton);
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL,15));;
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(5);
        pane.setVgap(10);
        pane.setPadding(new Insets(10,0, 10,10));
        pane.add(text,1,2,2,1);
        pane.add(label1,0,6);
        pane.add(textField,1,6);
        pane.add(label2,0,8);
        pane.add(passwordField,1,8);
        pane.add(addChoiceBox(),1,4);
        pane.add(addoOerateHBox(),1,9);
        pane.add(radioButton,3,8);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}