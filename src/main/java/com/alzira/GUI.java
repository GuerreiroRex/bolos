package com.alzira;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {
    static private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/IMAGES/logo-alt.png")));
        stage = primaryStage;

        trocarTela("login");
        

    }

    public static void trocarTela(String nome) throws IOException {
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
        double largura = tela.getWidth() * 0.25;
        double altura = tela.getHeight() * 0.3;

        Parent fxml = FXMLLoader.load(Main.class.getResource("/FXML/" + nome + ".fxml"));

        Scene scene = new Scene(fxml, largura, altura);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Main.class.getResource("/FXML/CSS/fx-" + nome + ".css").toExternalForm());

        // ------------------------------------------------------------------------
        ToolBar toolbar = (ToolBar) scene.lookup("#painel");
        toolbar.setOnMousePressed(pressEvent -> {
            toolbar.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
        
        VBox vbox = (VBox) scene.lookup("#caixa");
        vbox.getChildren().clear();
        vbox.getChildren().add(Controller.getWebView());

        stage.setScene(scene);
        stage.show();
        ResizeHelper.addResizeListener(stage);
    }

    public static Scene getScene() {
        return stage.getScene();
    }

    @FXML
    private void clique(ActionEvent mouseEvent) {
        Button botao = (Button) mouseEvent.getSource();
        Stage stage = (Stage) botao.getScene().getWindow();

        stage.close();
    }
}
