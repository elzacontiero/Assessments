package org.elzacontiero.m3assessments.guessthenumber.dto;

import org.elzacontiero.m3assessments.guessthenumber.GameStatus;

/**
 * A Game should have an answer and a status (in progress or finished).
 * While the game is in progress, users should not be able to see the answer.
 * The answer will be a 4-digit number with no duplicate digits.
 */
public class Game {
   private int id;
   private String answer;
   private GameStatus status;

   public Game(String answer, GameStatus status) {
      this.answer = answer;
      this.status = status;
   }

   public Game(int id, String answer, GameStatus status) {
      this.id = id;
      this.answer = answer;
      this.status = status;
   }

   public int getId() { return id; }
   public String getAnswer() { return answer; }
   public GameStatus getStatus() { return status; }

   public void setId(int id) {
      this.id = id;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }

   public void setStatus(GameStatus status) {
      this.status = status;
   }
}
