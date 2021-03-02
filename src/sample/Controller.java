package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        RadioButton rb = (RadioButton) g1.getSelectedToggle();

        if (rb != null){

            if (op1.isSelected()) pvcGame(actionEvent);
            if (op2.isSelected()) pvpGame(actionEvent);
            if (op3.isSelected()) cvcGame(actionEvent);
        }

    }

    boolean turn = true;

    public void pvcGame(ActionEvent actionEvent){
        Button b = (Button) actionEvent.getSource();
        int rNum;
        List<Button> buttonList = Arrays.asList(b00, b01, b02, b10, b11, b12, b20, b21, b22);

        info.setVisible(true);

            if(turn) {
                info.setText("CPU turn");

                if (b.getText().equals("")){
                    b.setText("X");
                    checkWinner();
                    turn = false;
                }
            }else{
                //TODO HACER LA MAQUINA ESTA
            }





    }


    public void pvpGame(ActionEvent actionEvent){
        Button b = (Button) actionEvent.getSource();

        info.setVisible(true);
        if(turn){
            info.setText("O turn");
            if(b.getText().equals("")){
                b.setText("X");
                checkWinner();
                turn = false;
            }
        }else {
            info.setText("X turn");
            if(b.getText().equals("")){
                b.setText("O");
                checkWinner();
                turn = true;
            }
        }



    }

    public void cvcGame(ActionEvent actionEvent){


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

    //Metodo comprobar ganador
    public boolean checkWinner(){

        //COMPROBAR HORIZONTALES
        if(!b00.getText().equals("") && !b01.getText().equals("") && b00.getText().equals(b01.getText()) && b01.getText().equals(b02.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            return true;

        }
        if (!b10.getText().equals("") && !b11.getText().equals("") && b10.getText().equals(b11.getText()) && b11.getText().equals(b12.getText())) {
            disableButtons();
            info.setText(b10.getText() + " wins");
            return true;

        }
        if (!b21.getText().equals("") && !b20.getText().equals("") && b20.getText().equals(b21.getText()) && b21.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b20.getText() + " wins");
            return true;

        }
        //COMPROBAR VERTICALES
        if(!b00.getText().equals("") && !b10.getText().equals("") && b00.getText().equals(b10.getText()) && b10.getText().equals(b20.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            return true;

        }
        if (!b01.getText().equals("") && !b11.getText().equals("") && b01.getText().equals(b11.getText()) && b11.getText().equals(b21.getText())) {
            disableButtons();
            info.setText(b01.getText() + " wins");
            return true;

        }
        if (!b02.getText().equals("") && !b12.getText().equals("") && b02.getText().equals(b12.getText()) && b12.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b02.getText() + " wins");
            return true;

        }

        //COMPROBAR DIAGONALES
        if (!b00.getText().equals("") && !b11.getText().equals("") && b00.getText().equals(b11.getText()) && b11.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            return true;

        }
        if (!b02.getText().equals("") && !b11.getText().equals("") && b02.getText().equals(b11.getText()) && b11.getText().equals(b20.getText())) {
            disableButtons();
            info.setText(b02.getText() + " wins");
            return true;
        }

        return false;

    }

    public void starts(ActionEvent actionEvent) {
        enableButtons();

    }
}
