package gamemodule;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import gamemodule.IGameModule;
//import nl.hanze.t23i.gamemodule.extern.IGameModule;

public abstract class AbstractGameModule implements IGameModule {
    public static final int MATCH_INITIALIZED = -1;
    public static final int MATCH_STARTED = 0;
    public static final int MATCH_FINISHED = 1;
    public static final int PLAYER_LOSS = -1;
    public static final int PLAYER_DRAW = 0;
    public static final int PLAYER_WIN = 1;
    public static final String GAME_TYPE = "";
    protected String playerOne;
    protected String playerTwo;
    protected int matchStatus;

    public AbstractGameModule(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.matchStatus = -1;
    }

    public void doPlayerMove(String player, String move) throws IllegalStateException {
        if(this.matchStatus != 0) {
            throw new IllegalStateException("Illegal match state");
        }
    }

    public int getPlayerScore(String player) throws IllegalStateException {
        if(this.matchStatus != 1) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return 0;
        }
    }

    public String getMatchResultComment() throws IllegalStateException {
        if(this.matchStatus != 1) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return null;
        }
    }

    public int getMatchStatus() {
        return this.matchStatus;
    }

    public String getMoveDetails() throws IllegalStateException {
        if(this.matchStatus == -1) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return null;
        }
    }

    public String getPlayerToMove() throws IllegalStateException {
        if(this.matchStatus != 0) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return null;
        }
    }

    public int getPlayerResult(String player) throws IllegalStateException {
        if(this.matchStatus != 1) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return 0;
        }
    }

    public String getTurnMessage() throws IllegalStateException {
        if(this.matchStatus != 0) {
            throw new IllegalStateException("Illegal match state");
        } else {
            return null;
        }
    }

    public void start() throws IllegalStateException {
        if(this.matchStatus != -1) {
            throw new IllegalStateException("Illegal match state");
        } else {
            this.matchStatus = 0;
        }
    }
}
