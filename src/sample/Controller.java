package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    RadioButton op1, op2, op3;
    @FXML
    Button start, b00, b01, b02, b10, b11, b12, b20, b21, b22;
    @FXML
    ToggleGroup g1;
    @FXML
    Label info;

    public void selected(ActionEvent actionEvent) {
        start.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setVisible(false);
        info.setVisible(false);
        disableButtons();

    }

    public void onClick(ActionEvent actionEvent) {

        Button b = (Button) actionEvent.getSource();
        b.setText("X");
    }


    public void startGame(ActionEvent actionEvent) {
        RadioButton rb = (RadioButton) g1.getSelectedToggle();

        int op = -1;
        if(rb.getId().equals("op1")) op = 1;
        if(rb.getId().equals("op2")) op = 2;
        if(rb.getId().equals("op3")) op = 3;


        switch(op){

            case 1:
                pvcGame();
                break;
            case 2:
                pvpGame();
                break;
            case 3:
                cvcGame();
                break;
        }
    }

    public void pvcGame(){
        enableButtons();

    }

    public void pvpGame(){
        enableButtons();
        info.setText("x turn");
        info.setVisible(true);

    }

    public void cvcGame(){
        enableButtons();

    }


    public void disableButtons(){
        b00.setDisable(true);
        b01.setDisable(true);
        b02.setDisable(true);
        b10.setDisable(true);
        b11.setDisable(true);
        b12.setDisable(true);
        b20.setDisable(true);
        b21.setDisable(true);
        b22.setDisable(true);
    }

    public void enableButtons(){
        b00.setDisable(false);
        b01.setDisable(false);
        b02.setDisable(false);
        b10.setDisable(false);
        b11.setDisable(false);
        b12.setDisable(false);
        b20.setDisable(false);
        b21.setDisable(false);
        b22.setDisable(false);
    }
}
