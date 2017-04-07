package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wout on 4/5/2017.
 */
public class CommandHandler extends server{
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;

    CommandHandler(PrintWriter out, BufferedReader in, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;
    }

    public void DoCommand(String command, String arg)
    {
        out.println(command + arg);
    }

    public void HandleIncommingCommands(String command)
    {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("echo: " + in.readLine());
                if(command.contains("SVR GAME YOURTURN"))
                {
                    ttt.myTurn = true;
                }
            }
        }
        catch(IOException io)
        {
            System.err.println(io);
        }
    }

    public void Logout()
    {
        try
        {
            DoCommand("logout", GetName());
            socket.close();
        }
        catch(IOException io)
        {
            System.err.println(io);
        }
    }
}
