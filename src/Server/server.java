package Server;

import TicTacToe.TicTacToe;
import javafx.application.Platform;
import javafx.concurrent.Task;
import main.Main;

import java.io.*;
import java.net.Socket;

public class Server {

    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;

    public CommandHandler ch;
    public Thread thread;
    Main main;

    public void Connect(String host, String port, Main m) throws IOException {
        main = new Main();
        Thread thread = new Thread() {
            public void run() {
                try {
                    socket = new Socket(host, Integer.parseInt(port));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    ch.out = out;
                    ch.in = in;
                    ch.socket = socket;
                    ch.main = m;
                    main.connected = true;
                    Login("client");
                    while(socket.isConnected())
                        ch.HandleIncommingCommands(in.readLine());
                }
                catch (IOException e) {
                    System.err.println("Cant process request");
                }
            }
        };
        thread.start();
        this.thread = thread;
        main.out = out;
        main.in = in;
        main.socket = socket;
    }

    public void Login(String name)
    {
        try {
            out.println("login " + name);
            System.out.println(in.readLine());
        }
        catch(Exception e)
        {

        }
    }
}