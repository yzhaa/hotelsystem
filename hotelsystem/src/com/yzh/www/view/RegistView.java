package com.yzh.www.view;

import com.yzh.www.manger.Manger;
import com.yzh.www.manger.MangerImpl;
import com.yzh.www.util.MyAlert;
import com.yzh.www.util.MyTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *帐号注册界面的布局及设定
 */
public class RegistView {
    private Manger manger;
    private TextField textField;
    private TextField password;
    private int choice;
    private PasswordField passwordField;
    private Stage stage;


    Stage addLogonStage(){
        stage=new Stage();
        VBox vBox=new VBox(10);
        HBox hBox1=new HBox();
        HBox  hBox2=new HBox();
        HBox  hBox3=new HBox();
        Scene newScene=new Scene(vBox,250,200);
        ChoiceBox<String> choiceBox=new ChoiceBox<>(FXCollections.observableArrayList("顾客","管理员"));
        Label label1=new Label("帐号:");
        Label label2=new Label("密码:");
        Label label3=new Label("确认密码:");
        textField = MyTextField.accontTextField();
        password = MyTextField.passwordTextFiled();
        passwordField = MyTextField.passwordTextFiled();
        Button button=new Button("注册");
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->{
            switch (newValue.intValue()){
                case 0:choice=1;break;
                case 1:choice=2;break;
            }
        } );
        button.setOnAction((ActionEvent e)->{
            manger = new MangerImpl();
            switch (manger.enroll(this)){
                case 0: MyAlert.setAlert("密码不一致或密码为空", 0);break;
                case 1: MyAlert.setAlert("输入帐号或者选择注册类型",0);break;
                case 2:MyAlert.setAlert("注册成功", 1);break;
                case 3: MyAlert.setAlert("该用户已存在",0);break;
            }
        });
        hBox1.getChildren().addAll(label1,textField);
        hBox2.getChildren().addAll(label2,password);
        hBox3.getChildren().addAll(label3,passwordField);
        hBox1.setSpacing(30);
        hBox2.setSpacing(30);
        hBox3.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(choiceBox,hBox1,hBox2,hBox3,button);
        vBox.setPadding(new Insets(10,10,10,10));
        stage.setTitle("注册帐户");
        stage.setScene(newScene);
        return  stage;
    }

    public Stage getStage() {
        return stage;
    }

    public TextField getTextField() {
        return textField;
    }

    public TextField getPassword() {
        return password;
    }

    public int getChoice() {
        return choice;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

}
