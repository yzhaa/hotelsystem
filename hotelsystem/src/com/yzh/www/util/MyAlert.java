package com.yzh.www.util;


import javafx.scene.control.Alert;

/**
 * 设定自己可以复用的MyAlert
 */
public class MyAlert {
    /**
     * 设置MyAlert要提示的信息以及类型
     * @param tip 提示信息
     * @param choice  0为错误提示，非0为信息提示
     */
    public static void setAlert(String tip,int choice) {
        if(choice==0){
            errorAlert(tip);
        }
        else {
            infoAlert(tip);
        }
    }

    private static void infoAlert(String tip){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(tip);
        alert.showAndWait();
    }
    private static void errorAlert(String tip){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(tip);
        alert.showAndWait();
    }

}
