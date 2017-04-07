package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by wout on 4/5/2017.
 */
public class Connect extends Thread {

    String host;
    String port;
    String name;

    Connect(String host, String port, String name)
    {
        this.host = host;
        this.port = port;
        this.name = name;
    }

    public void run()
    {
        System.out.println("New Thread");
        connect();
    }

    public void connect()
    {
        try {
            if(host == "")
                host = "localhost";
            if(port == "")
                port ="7790";

            String command = "login " + "wout";

            Socket socket = new Socket(host, Integer.parseInt(port));
            Scanner in = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (socket.isConnected()) {
                line = input.readLine(); //readline blocks
                System.out.println("Socket text: " + line);
            }


        }
        catch(Exception e)
        {
            System.out.println("Exception" + e);
        }
    }
}
