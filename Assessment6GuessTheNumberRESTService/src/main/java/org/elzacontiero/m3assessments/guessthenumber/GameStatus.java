package org.elzacontiero.m3assessments.guessthenumber;

public enum GameStatus {
    IN_PROGRESS(1),
    FINISHED(2);

    public final int status;

    GameStatus(int status) { this.status = status; }
}
