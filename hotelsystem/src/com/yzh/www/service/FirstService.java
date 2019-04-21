package com.yzh.www.service;

import com.yzh.www.view.ChangeInfoView;
import com.yzh.www.view.RegistView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface FirstService {
    /**
     * 检查输入的帐号密码，是否存在
     *
     * @param choice   用户的类型
     * @param accont   用户的帐号
     * @param password 用户的密码
     * @return 返回是否存在该类型帐号
     */
    boolean cheakInfo(int choice, String accont, String password);

    /**
     * 根据用户登陆的类型，切换场景
     *
     * @param choice 用户登陆的类型
     * @param stage  容纳Scene的stage
     */
    int updataScene(int choice, Stage stage);

    void retainUserInfo(String accont, String password, boolean isSelcet);

    /**
     *  改变用户信息
     * @param changeInfoView 改变用户信息的界面对象，包含着用户需要修改的信息
     * @return  返回修改信息是否成功
     */
     int changeUserInfo(ChangeInfoView changeInfoView);

    /**
     * 存储在登陆界面的输入的帐号密码，如果选择记住密码，则下次不用输入
     * @param textField   用于输入帐号的textField
     * @param passwordField  用于输入密码的passwordField
     * @param radioButton  是否记住密码的 radioButton
     */
     void geLoginInfo(TextField textField, PasswordField passwordField, RadioButton radioButton);

    /**
     * 注册一个新用户
     *
     * @param rv 注册界面的对象，包含着注册的信息
     * @return 返回注册是否成功
     */
    int addUser(RegistView rv);
}
