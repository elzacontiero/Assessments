package org.elzacontiero.m3assessments.guessthenumber.dto;

import org.springframework.stereotype.Component;


/**
 * A Game should have an answer and a status (in progress or finished).
 * While the game is in progress, users should not be able to see the answer.
 * The answer will be a 4-digit number with no duplicate digits.
 */
//@Component
public class Game {

   private long id;
   private String answer;
   private String status;

   public Game() {}

   public Game(String answer, String status) {
      this.answer = answer;
      this.status = status;
   }

   public Game(int id, String answer, String status) {
      this.id = id;
      this.answer = answer;
      this.status = status;
   }

   public long getId() { return id; }
   public String getAnswer() { return answer; }
   public String getStatus() { return status; }

   public void setId(long id) {
      this.id = id;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String toString() {
      return String.format("Game {id=%d, answer=%s, status=%s}", id, answer, status);
   }
}
