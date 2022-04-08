package org.elzacontiero.m3assessments.guessthenumber.dto;

import org.springframework.stereotype.Component;


/**
 * A Game should have an answer and a status (in progress or finished).
 * While the game is in progress, users should not be able to see the answer.
 * The answer will be a 4-digit number with no duplicate digits.
 */
public class Game {

   /** Unique identifier for this Game.  */
   private long id;
   /** This game's answer */
   private String answer;
   /** Status of this game: INPROGRESS or FINISHED */
   private String status;

   /** The default constructor  */
   public Game() {}

   /** Create Game passing answer and status on the constructor. */
   public Game(String answer, String status) {
      this.answer = answer;
      this.status = status;
   }

   /** Create a Game object passing ID, answer and status. */
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

   /** For pretty formatting, override the default toString() method. */
   @Override
   public String toString() {
      return String.format("Game {id=%d, answer=%s, status=%s}", id, answer, status);
   }
}
