package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    RadioButton op1, op2, op3;
    @FXML
    Button nextMove, start, save , b00, b01, b02, b10, b11, b12, b20, b21, b22;
    @FXML
    ToggleGroup g1;
    @FXML
    Label info;
    @FXML
    TextField playerName,player2name;
    @FXML
    Text playerNameText, player2nametext;
    @FXML
    MenuItem about;

    private int n = 0; //cuenta las casillas que se marcan
    private String player, player2; //nombre de los jugadores
    private boolean turn = true; //para determinar de quién es el turno
    private int nturnos = 0; //turnos para player vs cpu

    //esconder los campos que no me interesan al iniciar el programa
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setVisible(false);
        info.setVisible(false);
        nextMove.setVisible(false);
        playerName.setVisible(false);
        playerNameText.setVisible(false);
        save.setVisible(false);
        player2nametext.setVisible(false);
        player2name.setVisible(false);
        disableButtons();

    }

    //alterna la visibilidad de los campos para la información de los jugadores dependiendo del modo de juego seleccionado
    public void selected(ActionEvent actionEvent) {
        if(op1.isSelected()){
            if(player2nametext.isVisible()) player2nametext.setVisible(false);
            if(player2name.isVisible()) player2name.setVisible(false);
            playerName.setVisible(true);
            playerNameText.setVisible(true);
            save.setVisible(true);
        }

        if(op2.isSelected()){
            playerName.setVisible(true);
            playerNameText.setVisible(true);
            player2nametext.setVisible(true);
            player2name.setVisible(true);
            save.setVisible(true);
        }

        if(op3.isSelected()){
            playerName.setVisible(false);
            playerNameText.setVisible(false);
            save.setVisible(false);
            player2nametext.setVisible(false);
            player2name.setVisible(false);
        }
        start.setVisible(true);
    }
    //guarda los datos de los jugadores
    public void save(ActionEvent actionEvent) {
        if(op1.isSelected()){
            player = playerName.getText();
            playerName.setVisible(false);
            playerNameText.setVisible(false);
        }
        else if(op2.isSelected()){
            player = playerName.getText();
            player2 = player2name.getText();
            playerName.setVisible(false);
            playerNameText.setVisible(false);
            player2nametext.setVisible(false);
            player2name.setVisible(false);
        }

        save.setVisible(false);

    }

    //click de los botones, dependiendo si es el modo player vs cpu o player vs player
    public void onClick(ActionEvent actionEvent) {

        RadioButton rb = (RadioButton) g1.getSelectedToggle();

        if (rb != null){

            if (op1.isSelected()) pvcGame(actionEvent);
            if (op2.isSelected()) pvpGame(actionEvent);

        }

    }


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
            info.setText("O turn");

            while(!b1 && nturnos <=3){
                rNum = (int) (Math.random()*9);
                button = buttons.get(rNum);

                if(button.getText().equals("")){
                    button.setText("O");
                    nturnos++;
                    checkWinner();
                    info.setText(player + " turn");
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
            info.setText(player2 + " turn");
            if(b.getText().equals("")){
                b.setText("X");
                checkWinner();
                turn = false;
            }
        }else {
            info.setText(player + " turn");
            if(b.getText().equals("")){
                b.setText("O");
                checkWinner();
                turn = true;
            }
        }



    }

    //CPU VS CPU
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

    //boton para poner el siguiente movimiento de la cpu, que sinó va muy rápido en el modo cpu vs cpu
    public void nextMove(ActionEvent actionEvent) {
        if (op3.isSelected()) cvcGame(actionEvent);
    }


    //Metodo comprobar ganador/empate
    public boolean checkWinner(){
        n++;
        //COMPROBAR HORIZONTALES
        if(!b00.getText().equals("") && !b01.getText().equals("") && b00.getText().equals(b01.getText()) && b01.getText().equals(b02.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b00.getText().equals("O")) info.setText(b00.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b00.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b10.getText().equals("") && !b11.getText().equals("") && b10.getText().equals(b11.getText()) && b11.getText().equals(b12.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b10.getText().equals("O")) info.setText(b10.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b10.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b10.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b21.getText().equals("") && !b20.getText().equals("") && b20.getText().equals(b21.getText()) && b21.getText().equals(b22.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b21.getText().equals("O")) info.setText(b21.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b21.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b20.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        //COMPROBAR VERTICALES
        if(!b00.getText().equals("") && !b10.getText().equals("") && b00.getText().equals(b10.getText()) && b10.getText().equals(b20.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b00.getText().equals("O")) info.setText(b00.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b00.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b01.getText().equals("") && !b11.getText().equals("") && b01.getText().equals(b11.getText()) && b11.getText().equals(b21.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b01.getText().equals("O")) info.setText(b01.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b01.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b01.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b02.getText().equals("") && !b12.getText().equals("") && b02.getText().equals(b12.getText()) && b12.getText().equals(b22.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b02.getText().equals("O")) info.setText(b02.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b02.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b02.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }

        //COMPROBAR DIAGONALES
        if (!b00.getText().equals("") && !b11.getText().equals("") && b00.getText().equals(b11.getText()) && b11.getText().equals(b22.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b00.getText().equals("O")) info.setText(b00.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b00.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b00.getText() + " wins");
            if(op3.isSelected()) nextMove.setVisible(false);
            start.setVisible(true);
            return true;

        }
        if (!b02.getText().equals("") && !b11.getText().equals("") && b02.getText().equals(b11.getText()) && b11.getText().equals(b20.getText())) {
            disableButtons();
            if(op1.isSelected()){
                if(b02.getText().equals("O")) info.setText(b02.getText() + " wins");
                else info.setText(player + " wins");
            }
            if(op2.isSelected()){
                if(b02.getText().equals("O")) info.setText(player2 + " wins");
                else info.setText(player + " wins");
            }
            if(op3.isSelected()) info.setText(b02.getText() + " wins");
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

    //el boton start resetea el tablero
    public void starts(ActionEvent actionEvent) {
        n = 0;
        clearButtons();
        enableButtons();
        if (op3.isSelected()){
            nextMove.setVisible(true);
            cvcGame(actionEvent);
        }

    }


    //GESTIÓN DE LOS BOTONES
    //limpia los botones para el restart
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

    //desactiva los botones
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

    //activa los botones
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
