package TicTacToe;

import Server.Server;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import main.Main;
import Server.*;
public class TicTacToe extends Main
{
    //public boolean myTurn = false;
    public Tile[][] board = new Tile[3][3];

    //public Socket socket;

    private Pane root = new Pane();

    public Scene createContent() {
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.ttt = this;
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                tile.TileNumber = counter;
                root.getChildren().add(tile);
                counter++;
                board[j][i] = tile;
            }
        }
        return new Scene(root, 700, 700);
    }

    public void DoTurn(int tileNumber)
    {
        //Server.out.println("move " + tileNumber);
        out.println("move" + tileNumber);
    }
}
