package com.yzh.www.view;

import javafx.scene.control.*;
/**
 * 一个简单的菜单项，方便管理员，顾客界面都可以用到
 */

 class MyMenuBar {
     MenuBar creatMenuBar(){
        Menu menu1=new Menu("我的");
        MenuBar mb=new MenuBar();
        MenuItem mi1=new MenuItem("修改资料");
        MenuItem mi2=new MenuItem("查询账单");
        mb.setMaxHeight(25);
        mb.setMaxWidth(65);
        menu1.getItems().addAll(mi1, mi2);
        mb.getMenus().add(menu1);
        return mb;
    }
}
