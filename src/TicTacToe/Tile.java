package TicTacToe;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.*;

public class Tile extends StackPane {
    public Text text = new Text();

    public int TileNumber;
    public TicTacToe ttt;

    public Tile() {
        Rectangle border = new Rectangle(20, 20, 200, 200);
        border.setFill(null);
        border.setStroke(javafx.scene.paint.Color.BLACK);

        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {
                if (ttt.myTurn == false) {
                    System.out.println("button pressed but its not my turn" + ttt);
                    return;
                }
                ttt.DoTurn(TileNumber);
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