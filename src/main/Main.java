package main;

import TicTacToe.*;
import Server.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application{

    public Boolean connected = false;

    private Button loginBtn;
    public Button logoutBtn;
    public Scene LoginScene;
    public Scene TicTacToeScene;
    public Stage PrimaryStage;

    TextField ipAddress = new TextField();
    TextField portNumber = new TextField();
    TextField nameField = new TextField();

    private String ip;
    private String port;
    private String name;

    private String opponentName;
    private String gameType;

    public Server server;
    public TicTacToe ttt;
    public CommandHandler ch;
    public Main main;

    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;

    public Boolean myTurn = false;
    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        server = new Server();
        ch = new CommandHandler();
        main = this;
        PrimaryStage = stage;
        PrimaryStage.setScene(LoginScene);
        PrimaryStage.show();

        //Setting an action for the Submit button
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    System.out.println("button press : " + ttt);
                    server.Connect(ipAddress.getText(), portNumber.getText(), main);
                    server.ch = ch;
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
        });
    }

    public void init()
    {
        ttt = new TicTacToe();
        LoginScene = initLoginScene();
        TicTacToeScene = initTicTacToeScene();
        //default value's
        ipAddress.setText("localhost");
        portNumber.setText("7790");
        nameField.setText("client");
        name = nameField.getText();
        port = portNumber.getText();
        ip = ipAddress.getText();
    }

    private Scene initLoginScene()
    {
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the Name text field
        final TextField nameField = new TextField();
        nameField.setPromptText("Enter your user name.");
        nameField.setPrefColumnCount(10);
        nameField.getText();
        GridPane.setConstraints(nameField, 0, 0);
        grid.getChildren().add(nameField);

        //Defining the ip text field
        final TextField ipAddress = new TextField();
        ipAddress.setPrefColumnCount(15);
        ipAddress.setPromptText("Enter ip address.");
        GridPane.setConstraints(ipAddress, 0, 1);
        grid.getChildren().add(ipAddress);

        //Defining the port text field
        final TextField portNumber = new TextField();
        portNumber.setPrefColumnCount(15);
        portNumber.setPromptText("Enter port number.");
        GridPane.setConstraints(portNumber, 0, 2);
        grid.getChildren().add(portNumber);

        //Defining the Login button
        Button login = new Button("Login");
        loginBtn = login;
        GridPane.setConstraints(loginBtn, 1, 0);
        grid.getChildren().add(loginBtn);

        Scene scene = new Scene(grid, 500, 500);
        return scene;
    }

    public void SetTicTacToeScene()
    {
        if(TicTacToeScene != null) {
            PrimaryStage.setScene(TicTacToeScene);
            PrimaryStage.show();
        }
        else
        {
            System.out.println("Cant init TTT scene. making it again");
            TicTacToeScene = initTicTacToeScene();
            if(PrimaryStage != null)
                PrimaryStage.setScene(TicTacToeScene);
            else
                System.out.println("Cant Find primary stage");
        }
    }

    private Scene initTicTacToeScene()
    {
        if(ttt == null)
            ttt = new TicTacToe();
        return ttt.createContent();
    }

    public String GetIpAddress()
    {
        return ip;
    }

    public String GetPort()
    {
        return port;
    }

    public String GetName()
    {
        return name;
    }

    public String GetOpponentName()
    {
        return opponentName;
    }

    public void SetOpponentName(String name)
    {
        opponentName = name;
    }

    public String GetGameType()
    {
        return gameType;
    }

    public void SetGameTye(String type)
    {
        gameType = type;
    }
}
