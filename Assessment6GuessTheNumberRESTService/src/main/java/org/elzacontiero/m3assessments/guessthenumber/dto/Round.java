package org.elzacontiero.m3assessments.guessthenumber.dto;

import java.sql.Timestamp;

public class Round {
    private long id;
    private long game_id;
    private String attempt;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This round's result:
     */
    private String result;
    private Timestamp tstamp;


    public Round() {}

    public Round(long id, long game_id, String attempt, Timestamp tstamp) {
        this.id = id;
        this.game_id = game_id;
        this.attempt = attempt;
        this.tstamp = tstamp;
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
