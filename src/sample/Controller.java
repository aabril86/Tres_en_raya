package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    RadioButton op1, op2, op3;
    @FXML
    Button nextMove, start, b00, b01, b02, b10, b11, b12, b20, b21, b22;
    @FXML
    ToggleGroup g1;
    @FXML
    Label info;

    int n = 0;
    public void selected(ActionEvent actionEvent) {
        start.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setVisible(false);
        info.setVisible(false);
        nextMove.setVisible(false);
        disableButtons();

    }



    public void onClick(ActionEvent actionEvent) {

        RadioButton rb = (RadioButton) g1.getSelectedToggle();

        if (rb != null){

            if (op1.isSelected()) pvcGame(actionEvent);
            if (op2.isSelected()) pvpGame(actionEvent);
            //if (op3.isSelected()) cvcGame(actionEvent);
        }

    }

    boolean turn = true;
    int nturnos = 0;

    //PLAYER VS CPU
    public void pvcGame(ActionEvent actionEvent){
        Button b = (Button) actionEvent.getSource();
        List<Button>  buttons = new ArrayList<Button>(Arrays.asList(b00, b01, b02, b10, b11, b12, b20, b21, b22));
        Button button;
        start.setVisible(false);
        int rNum;
        boolean b1 = false;
        info.setVisible(true);
        info.setText("O turn");
        if (b.getText().equals("")){
            b.setText("X");
            checkWinner();

            while(!b1 && nturnos <=3){
                rNum = (int) (Math.random()*9);
                button = buttons.get(rNum);

                if(button.getText().equals("")){
                    button.setText("O");
                    nturnos++;
                    checkWinner();
                    b1 = true;
                }
            }
        }


    }

    //PLAYER VS PLAYER
    public void pvpGame(ActionEvent actionEvent){
        Button b = (Button) actionEvent.getSource();

        start.setVisible(false);
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

    //CPU VS CPU
    //TODO ESTO
    public void cvcGame(ActionEvent actionEvent){
        Button b = (Button) actionEvent.getSource();
        List<Button>  buttons = new ArrayList<Button>(Arrays.asList(b00, b01, b02, b10, b11, b12, b20, b21, b22));
        Button button;
        Boolean b1 = false;
        info.setVisible(true);
        start.setVisible(false);
        while(!b1){

                int rNum = (int) (Math.random()*9);
                button = buttons.get(rNum);

                if("".equals(button.getText())){

                    if(turn){
                        info.setText("O turn");
                        button.setText("X");
                        if(checkWinner()) break;
                        turn = false;
                    }else{
                        info.setText("X turn");
                        button.setText("O");
                        if(checkWinner()) break;
                        turn = true;
                    }
                    b1 = true;
                }
        }

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
        n++;
        //COMPROBAR HORIZONTALES
        if(!b00.getText().equals("") && !b01.getText().equals("") && b00.getText().equals(b01.getText()) && b01.getText().equals(b02.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b10.getText().equals("") && !b11.getText().equals("") && b10.getText().equals(b11.getText()) && b11.getText().equals(b12.getText())) {
            disableButtons();
            info.setText(b10.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b21.getText().equals("") && !b20.getText().equals("") && b20.getText().equals(b21.getText()) && b21.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b20.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        //COMPROBAR VERTICALES
        if(!b00.getText().equals("") && !b10.getText().equals("") && b00.getText().equals(b10.getText()) && b10.getText().equals(b20.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b01.getText().equals("") && !b11.getText().equals("") && b01.getText().equals(b11.getText()) && b11.getText().equals(b21.getText())) {
            disableButtons();
            info.setText(b01.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b02.getText().equals("") && !b12.getText().equals("") && b02.getText().equals(b12.getText()) && b12.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b02.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }

        //COMPROBAR DIAGONALES
        if (!b00.getText().equals("") && !b11.getText().equals("") && b00.getText().equals(b11.getText()) && b11.getText().equals(b22.getText())) {
            disableButtons();
            info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b02.getText().equals("") && !b11.getText().equals("") && b02.getText().equals(b11.getText()) && b11.getText().equals(b20.getText())) {
            disableButtons();
            info.setText(b02.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;
        }

        //COMPROBAR EMPATE
        if (n == 9){
            disableButtons();
            info.setText("Tie");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;
        }

        return false;

    }

    public void starts(ActionEvent actionEvent) {
        n = 0;
        clearButtons();
        enableButtons();
        if (op3.isSelected()){
            nextMove.setVisible(true);
            cvcGame(actionEvent);
        }

    }

    public void clearButtons(){
        b00.setText("");
        b01.setText("");;
        b02.setText("");;
        b10.setText("");
        b11.setText("");
        b12.setText("");
        b20.setText("");
        b21.setText("");
        b22.setText("");

        info.setText("");
    }

    public void nextMove(ActionEvent actionEvent) {
        if (op3.isSelected()) cvcGame(actionEvent);
    }
}
