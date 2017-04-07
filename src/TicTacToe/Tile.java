package TicTacToe;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.text.*;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.awt.*;

import Server.server;

public class Tile extends StackPane {
    public Text text = new Text();

    public int TileNumber;
    TicTacToe ttt = new TicTacToe();

    public Tile() {
        Rectangle border = new Rectangle(20, 20, 200, 200);
        border.setFill(null);
        border.setStroke(javafx.scene.paint.Color.BLACK);

        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            if (!ttt.playable)
                return;

            if (event.getButton() == MouseButton.PRIMARY) {
                if (!ttt.myTurn)
                    return;

                drawX();
                ttt.myTurn = false;
                ttt.DoTurn(TileNumber);
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (ttt.myTurn)
                    return;

                drawO();
                ttt.myTurn = true;
            }
        });
    }

    public String getValue() {
        return text.getText();
    }

    private void drawX() {
        text.setText("X");
    }

    private void drawO() {
        text.setText("O");
    }
}