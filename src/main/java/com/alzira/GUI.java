package com.alzira;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {
    Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Parent fxml = FXMLLoader.load(Main.class.getResource("/FXML/login.fxml"));
        // -----------------------------------------------------------------------
        Scene scene = new Scene(fxml, 300, 200);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Main.class.getResource("/FXML/CSS/fx-login.css").toExternalForm());
        // -----------------------------------------------------------------------
        ToolBar toolbar = (ToolBar) scene.lookup("#painel");
        toolbar.setOnMousePressed(pressEvent -> {
            toolbar.setOnMouseDragged(dragEvent -> {
                primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
        // -----------------------------------------------------------------------
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void clique(ActionEvent mouseEvent) {
        Button botao = (Button) mouseEvent.getSource();
        Stage stage = (Stage) botao.getScene().getWindow();

        stage.close();
    }
}
