package org.elzacontiero.m3assessments.guessthenumber.dto;

import java.sql.Timestamp;

public class Round {
    private int id;
    private int game_id;
    private String attempt;
    private Timestamp tstamp;

    public Round() {}

    public Round(int id, int game_id, String attempt, Timestamp tstamp) {
        this.id = id;
        this.game_id = game_id;
        this.attempt = attempt;
        this.tstamp = tstamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
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
