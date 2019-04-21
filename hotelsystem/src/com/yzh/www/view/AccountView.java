package com.yzh.www.view;

import com.yzh.www.manger.MangerImpl;
import com.yzh.www.entity.Account;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * 账单界面
 */

public class AccountView {
    private Text text;
    private ListView<TextArea> listView;
    private int choice;

    /**
     * 账单的主界面
     * @param choice 用于选择你要显示的是客户的账单还是酒店的账单
     * @return 返回包括账单界面的Stage对象
     */
    Stage creatStage(int choice){
        this.choice = choice;
        Stage stage = new Stage();
        GridPane gp = new GridPane();
        Scene scene = new Scene(gp);
        Label lag = new Label();
        Label lat = new Label("总计：");
        HBox hBox = new HBox(10);
        listView = new ListView<>();
        text = new Text();
        ImageView imageView = new ImageView(new Image("\\Image\\account.png"));

        update();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        lag.setGraphic(imageView);
        listView.setPrefHeight(370);
        listView.setPrefWidth(350);
        hBox.getChildren().addAll(lat, text);
        gp.setPadding(new Insets(10));
        gp.addColumn(0,lag,listView,hBox);
        stage.setTitle("账单");
        stage.setResizable(false);
        stage.setScene(scene);
        return stage;
    }

    /**
     * 加载账单的数据
     */
    public void update(){
        ArrayList<Account> arrayList =new MangerImpl().loadAccount(choice);
        List<TextArea> contence = new ArrayList<>();
        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        ta.setPrefWidth(330);
        ta.setPrefHeight(350);
        StringBuilder sb = new StringBuilder();
            int sum=0;
            for (Account account:arrayList ) {
                sb.append(account.toString());
                sum +=(account.getMoney());
            }
        contence.add(ta);
        ta.setText(sb.toString());
        text.setText(Integer.toString(sum));
        listView.setItems(FXCollections.observableList(contence));
    }

}
