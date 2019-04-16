package com.yzh.www.view;

import com.yzh.www.controller.ControlActionImpl;
import com.yzh.www.entity.User;
import com.yzh.www.util.MyAlert;
import com.yzh.www.util.MyTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Storage.UserStorage;

public class ChangeInfoView {
    private UserStorage userStorage;
    private ControlActionImpl controlAction;
    private TextField[] textFields;
    private Stage stage;

    public ChangeInfoView(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Stage addStage() {
        User user = userStorage.getUser();
        Stage stage = new Stage();
        GridPane gp = new GridPane();
        Scene scene = new Scene(gp);
        Button button = new Button("确认");
        Label lables[] = {new Label("用户名"), new Label("手机号"), new Label("身份证"), new Label("密码")};
        TextField tfs[] = {new TextField(), MyTextField.phoneNumberTextField(), MyTextField.idCardTextFile(),
                MyTextField.passwordTextFiled()};
        tfs[0].setText(user.getUserName());
        tfs[1].setText(user.getPhoneNumber());
        tfs[2].setText(user.getIdCard());
        textFields = tfs;
        button.setOnAction((ActionEvent e) -> {
            controlAction = new ControlActionImpl();
            if (controlAction.changeInfo(this)) {
                MyAlert.setAlert("修改成功", 1);
                stage.close();
            }
        });

        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.addColumn(0, lables);
        gp.addColumn(1, tfs);
        gp.add(button, 1, 4);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("修改个人信息");
        return stage;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public TextField[] getTextFields() {
        return textFields;
    }


}
