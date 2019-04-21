package com.yzh.www.util;

import javafx.event.Event;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * 对TextField进行小改造
 */

public class MyTextField {
    /**
     * 禁止输入法
     * @return 返回以及禁止了输入法的TextField
     */
        private static TextField BaseTextField() {
            TextField textField = new TextField();
            textField.setOnInputMethodTextChanged(Event::consume);
            return textField;
        }

    /**
     * 禁止输入非数字之外的其他东西，且规定长度小于1等于0
     * @return  返回规定好输入的TextField
     */
    public static TextField accontTextField() {

            TextField textField = BaseTextField();
            textField.setOnKeyTyped(event ->  {
                if (event.getCharacter().matches("[0-9]*")) {
                    if(textField.getText()!=null){
                       if( textField.getText().length()>9){
                           event.consume();
                       }
                    }
                } else {
                    event.consume();
                }
            });
            return textField;
        }

    /**
     * 禁止输入非数字之外的其他东西，且规定长度小于等于11
     * @return 返回规定好输入的TextField
     */
    public  static TextField phoneNumberTextField() {
            TextField textField = BaseTextField();
            textField.setOnKeyTyped(event -> {
                if (event.getCharacter().matches("[0-9]*")) {
                    if (textField.getText() != null) {
                        if (textField.getText().length() > 10) {
                            event.consume();
                        }
                    }
                }
                else {
                    event.consume();
                }
            });
            return textField;
        }

    /**
     * 禁止输入非数字之外的其他东西，且规定长度小于等于18
     * @return 返回规定好输入的TextField
     */
    public static TextField idCardTextFile() {

            TextField textField = BaseTextField();
            textField.setOnKeyTyped(event ->  {
                if (event.getCharacter().matches("[0-9]*")) {
                    if(textField.getText()!=null){
                        if (textField.getText().length() > 17) {
                            event.consume();
                        }
                    }

                } else {
                    event.consume();
                }
            });
            return textField;
        }

    /**
     * 禁止输入非数字之外的其他东西，且规定为1-10
     * @return  返回规定好输入的TextField
     */
        public  static TextField pointTextField() {
            TextField textField = BaseTextField();
            textField.setOnKeyTyped(event ->  {
                if (textField.getText().equals("")) {
                    if (!event.getCharacter().matches("[1-9]")) {
                        event.consume();
                    }
                } else if (textField.getText().equals("1")) {
                    if (!event.getCharacter().matches("[0]")) {
                        event.consume();
                    }
                } else {
                    event.consume();
                }
            });
            return textField;
        }


    /**
     * 规定密码 PasswordField输入的长度小于等于16
     * @return 返回规定好输入的TextField
     */
    public static PasswordField passwordTextFiled(){
        PasswordField textField = new PasswordField();
        textField.setOnInputMethodTextChanged(Event::consume);
        textField.setOnKeyTyped(event -> {
            if (textField.getText().length() > 15) {
                event.consume();
            }
        });
            return textField;
        }

    /**
     * 规定价格输入的TextField
     * @return 返回对应规定了的extField
     */
        public static  TextField priceField(){
            TextField textField = BaseTextField();
            textField.setOnKeyTyped(event ->  {
                if (event.getCharacter().matches("[0-9]*")) {
                    if (textField.getText() != null) {
                        if (textField.getText().length() > 8) {
                            event.consume();
                        }
                    }
                } else event.consume();
            });
            return textField;
        }
}
