package com.example.tictactoeproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.*;

import java.io.IOException;

public class TicTacToe extends Application {

    private boolean playerX = true;
    private int playerXScore = 0, playerOScore = 0;
    Button buttons[][] = new Button[3][3];
    private Label playerXScoreLabel, playerOScoreLabel;
    private BorderPane createContent()
    {
        BorderPane pane  = new BorderPane();


        //Top Label
        Label topLabel = new Label("Tic Tac Toe Game");
        topLabel.setStyle("-fx-font-size : 24pt;  -fx-font-weight : bold;");
        pane.setTop(topLabel);


        //Middle Label
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setOnAction(actionEvent -> buttonClicked(button));
                button.setStyle("-fx-font-size : 16pt;  -fx-font-weight : bold;");

                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        pane.setCenter(gridPane);


        //Bottom Label
        HBox scoreBoard = new HBox(20);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt;  -fx-font-weight : bold;");
        playerOScoreLabel = new Label("Player O : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 16pt;  -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel, playerOScoreLabel);
        pane.setBottom(scoreBoard);

        return pane;
    }

    private void buttonClicked(Button button)
    {
        if(button.getText().equalsIgnoreCase("")){
            if(playerX)
                button.setText("X");
            else
                button.setText("O");

                playerX = !playerX;
        }
        checkWinner();
    }

    private void checkWinner()
    {
        for (int i = 0; i < 3; i++) {
            if(buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().isEmpty()){
                    showWinner(buttons[i][0].getText());
                updateScore(buttons[i][0].getText());
                resetBoard();
            }
            else if(buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().isEmpty()){
                showWinner(buttons[0][i].getText());
                updateScore(buttons[0][i].getText());
                resetBoard();
            }
        }

         if(buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText())
            && !buttons[0][0].getText().isEmpty()){
        showWinner(buttons[0][0].getText());
        updateScore(buttons[0][0].getText());
        resetBoard();
        }
         else if(buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText())
                 && !buttons[0][2].getText().isEmpty()){
             showWinner(buttons[0][2].getText());
             updateScore(buttons[0][2].getText());
             resetBoard();
         }

         boolean tie = true;
        for(Button row[] : buttons){
            for(Button b : row) {
                if (b.getText().equalsIgnoreCase("")) {
                    tie = false;
                    break;
                }
            }
        }

        if(tie)
            tie();
    }

    public void updateScore(String winner)
    {
        if(winner.equalsIgnoreCase("X")){
            playerXScore++;
            playerXScoreLabel.setText("Player X : "+playerXScore);
        }
        else{
            playerOScore++;
            playerOScoreLabel.setText("Player O : "+playerOScore);
        }
    }

    public void showWinner(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation! "+winner+"  won the game!");
        alert.setHeaderText("");
        alert.showAndWait();

    }

    public void tie()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over!  It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();

        resetBoard();
    }

    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button b : row)
                b.setText("");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}