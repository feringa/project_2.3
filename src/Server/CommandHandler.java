package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import TicTacToe.*;
import javafx.application.Platform;
import main.Main;
/**
 * Created by wout on 4/5/2017.
 */
public class CommandHandler extends Main{
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;

    //public Main main;
    private Server server;

    public CommandHandler() {
        server = new Server();
    }

    public void DoCommand(String command)
    {
        out.println(command);
    }

    public void HandleIncommingCommands(String command)
    {
        System.out.println("Handle Incomming Commands : " + command);
        //New Match started
        if(command != null) {
            ArrayList<String> commandSplit = new ArrayList<String>();
            //split the command into single words
            for (String str : command.split(" ")) {
                str = str.replaceAll("[,{}:\"]", "");
                commandSplit.add(str);
            }

            if (command.contains("OK"))
                connected = true;
            else if (command.contains("SVR GAME MATCH")) {
                CheckTurn(commandSplit);
                CheckGameType(commandSplit);
            }
            else if (command.contains("SVR GAME YOURTURN")) {
                System.out.println("TTT : " + ttt);
                myTurn = true;
            }
            else if(command.contains("SVR GAME MOVE"))
                CheckMove(commandSplit);
        }
    }

    private void CheckMove(ArrayList<String> commandSplit)
    {
        int playerDidMove = commandSplit.indexOf("PLAYER");
        String playerDidMoveStr = commandSplit.get(playerDidMove + 1);
        if(playerDidMoveStr.equals(GetName()))
        {
            int tileNumber = Integer.getInteger(commandSplit.get(commandSplit.indexOf("MOVE") + 1 ));
            //ttt.findTile(tileNumber);
            //System.out.println(ttt.findTile(tileNumber));
        }
    }

    private void CheckTurn(ArrayList<String> commandSplit)
    {
        int playerToMoveIndex = commandSplit.indexOf("PLAYERTOMOVE");
        String playerToMoveStr = commandSplit.get(playerToMoveIndex + 1);
        if (playerToMoveStr == main.GetName()) {
            System.out.println("i start");
            ttt.myTurn = true;
        }
        int opponentIndex = commandSplit.indexOf("OPPONENT");
        String opponentStr = commandSplit.get(opponentIndex + 1);
        main.SetOpponentName(opponentStr);
    }

    private void CheckGameType(ArrayList<String> commandSplit)
    {
        int gameTypeIndex = commandSplit.indexOf("GAMETYPE");
        String gameTypeStr = commandSplit.get(gameTypeIndex + 1);
        if (gameTypeStr.equals("Tic-tac-toe")) {
            main.SetGameTye(gameTypeStr);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    main.SetTicTacToeScene();
                }
            });
        } else if (gameTypeStr.equals("othello")) {
            // make othello scene
        }
    }

    public void Logout()
    {
        try
        {
            //DoCommand("logout", GetName());
            socket.close();
        }
        catch(IOException io)
        {
            System.err.println(io);
        }
    }
}
