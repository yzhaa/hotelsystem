package com.yzh.www.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;




public class MyPointVBox {
    private ListView<TextArea> listView;
    private Label label;
     VBox creatPointVBox(){
        VBox vBox = new VBox(20);
        label = new Label("评分");
        listView = new ListView<>();
        listView.setPrefWidth(200);
        listView.setMaxHeight(270);
        vBox.setPadding(new Insets(10, 20, 20, 20));
        vBox.getChildren().addAll(label, listView);
        return vBox;
    }

     ListView<TextArea> getListView() {
        return listView;
    }

    public Label getLabel() {
        return label;
    }
}
