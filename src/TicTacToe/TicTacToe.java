package TicTacToe;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import Server.server;

import java.net.Socket;

public class TicTacToe extends server
{
    public boolean playable = true;
    public boolean myTurn = true;
    public Tile[][] board = new Tile[3][3];

    //public Socket socket;

    private Pane root = new Pane();

    //public server server;

    public Scene createContent() {
        //root.setPrefSize(600, 600);
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                tile.TileNumber = counter;
                root.getChildren().add(tile);
                counter++;
                board[j][i] = tile;
            }
        }

        Scene scene = new Scene(root, 700, 700);
        return scene;
    }

    public void DoTurn(int tileNumber)
    {
        System.out.println(server.socket);
        server.out.println("move " + tileNumber);
        System.out.println("Do Turn" + tileNumber);
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/
}
