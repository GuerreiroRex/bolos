package com.alzira;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Controller implements Initializable {
    private WebEngine webEngine;
    @FXML
    private WebView webView;

    @FXML
    private Button fechar;

    @FXML
    private ToolBar painel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();

        webEngine.load(Main.class.getResource("/TELAS/HTML/login.html").toExternalForm());

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
}
