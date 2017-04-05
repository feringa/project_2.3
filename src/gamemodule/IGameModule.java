package gamemodule;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Component;

public interface IGameModule {
    void start() throws IllegalStateException;

    String getPlayerToMove() throws IllegalStateException;

    void doPlayerMove(String var1, String var2) throws IllegalStateException;

    String getTurnMessage() throws IllegalStateException;

    String getMoveDetails() throws IllegalStateException;

    int getMatchStatus();

    int getPlayerResult(String var1) throws IllegalStateException;

    int getPlayerScore(String var1) throws IllegalStateException;

    String getMatchResultComment() throws IllegalStateException;

    Component getView();
}
