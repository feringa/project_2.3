package Server;

import TicTacToe.TicTacToe;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

import TicTacToe.Tile;

public class server extends Application{

    //public Button[] buttons = new Button[8];
    private ObservableList<Button> moveButtons = FXCollections.observableArrayList();
    private Boolean connected = false;

    private Button loginBtn;
    public Button logoutBtn;
    public Scene LoginScene;
    public Scene TicTacToeScene;
    public Stage PrimaryStage;

    public TicTacToe ttt;

    TextField ipAddress = new TextField();
    TextField portNumber = new TextField();
    TextField nameField = new TextField();

    public static PrintWriter out;
    public static BufferedReader in;

    public static Socket socket;

    public static void main(String args[])
    {
        launch(args);
    }

    public void init()
    {
        LoginScene = initLoginScene();
        TicTacToeScene = initTicTacToeScene();
        //default value's
        ipAddress.setText("localhost");
        portNumber.setText("7790");
        nameField.setText("Default name");
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
        /*LoginStage.setScene(scene);
        LoginStage.setHeight(250);
        LoginStage.setWidth(250);*/
        return scene;
    }

    private Scene initTicTacToeScene()
    {
        ttt = new TicTacToe();
        return ttt.createContent();
    }

    public void Connect(String host, String port, String name) throws IOException
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    socket = new Socket(host, Integer.parseInt(port));
                    //ttt.socket = socket;
                    System.out.println(ttt);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                }
                catch (IOException e)
                {
                    System.err.println("Cant process request");
                }
            }
        };
        thread.start();
    }

    @Override public void    start(Stage stage)
    {
        PrimaryStage = stage;
        PrimaryStage.setScene(LoginScene);
        PrimaryStage.show();

        //Setting an action for the Submit button
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Connect(ipAddress.getText(), portNumber.getText(), nameField.getText());
                    connected = true;
                    PrimaryStage.setScene(TicTacToeScene);
                    PrimaryStage.show();
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
        });
    }

    public String GetIpAddress()
    {
        return ipAddress.getText();
    }
    public String GetPort()
    {
        return portNumber.getText();
    }
    public String GetName()
    {
        return nameField.getText();
    }
}