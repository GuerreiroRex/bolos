package com.alzira;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Controller implements Initializable {
    private static WebView webView;

    @FXML
    private VBox quadro;
    @FXML
    private Button fechar;
    @FXML
    private ToolBar painel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] data = location.toString().split("/");
        // ------------------------------------------------------------------------

        webView = new WebView();

        System.out.println(location);

        WebEngine webEngine = webView.getEngine();

        webEngine.load(Main.class.getResource("/TELAS/HTML/" + data[data.length-1].replace(".fxml", "") + ".html").toExternalForm());

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observableValue, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("Bridge", new Bridge());
                }
            }
        });
    }

    public static WebView getWebView() {
        return webView;
    }

    @FXML
    private void cliqueFechar(ActionEvent mouseEvent) {
        Stage stage = (Stage) fechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cliqueMin(ActionEvent mouseEvent) {
        Stage stage = (Stage) fechar.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void cliqueMax(ActionEvent mouseEvent) {
        Stage stage = (Stage) fechar.getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}
