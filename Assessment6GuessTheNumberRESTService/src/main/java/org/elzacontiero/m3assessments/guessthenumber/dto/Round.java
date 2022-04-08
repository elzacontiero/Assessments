package org.elzacontiero.m3assessments.guessthenumber.dto;

import java.sql.Timestamp;

/**
 * Class Round models one attempt to a game.
 */
public class Round {
    /** Unique identifier */
    private long id;
    /** reference to a game ID */
    private long game_id;
    /** This user's attempt/round */
    private String attempt;
    /** This round's result */
    private String result;
    /** Time where this round was attempted. */
    private Timestamp tstamp;

    /** Default constructor  */
    public Round() {}

    /** Constructor passing all the relevant data */
    public Round(long id, long game_id, String attempt, Timestamp tstamp) {
        this.id = id;
        this.game_id = game_id;
        this.attempt = attempt;
        this.tstamp = tstamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public Timestamp getTstamp() {
        return tstamp;
    }

    public void setTstamp(Timestamp tstamp) {
        this.tstamp = tstamp;
    }
}
