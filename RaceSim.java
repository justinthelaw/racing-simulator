
/**
 * The RaceSim class simulates a race between a tortoise and a hare based on a
 * pre-determined action probability matrix.
 *
 * Pre-Conditions: Positions, provided as user input, is a value of greater than
 * or equal to 50 (defaults to 50 on incorrect input) to define the end position
 * of the race
 *
 * Post-Conditions: After kicking off the race with a console message, the
 * current position of the hare and tortoise is displayed at each program step
 * until one or both reach(es) the end position (Positions) of the race, where a
 * message will be displayed to console identifying the winner or lack thereof
 *
 * Reference: Refer to the README.md file for project description and solution
 * discussion
 *
 * Class: EN.605.201.82.SU22
 *
 * @author: Justin Law
 *
 */

import java.util.Scanner;
import java.util.Map;

public class RaceSim {

   // instantiate Map and HashTable utilities to generate key-value pairings
   // human-readable parameter definition based on possible actions-results
   // where (for next 2 objects) structure is: [ACTION], [POSITIONS]
   private static final Map<String, Integer> TORTOISE_ACTION_MAP = Map.of
   (
      "Fast plod", 3,
      "Slow plod", 1,
      "Slip", -6
   );
   private static final Map<String, Integer> HARE_ACTION_MAP = Map.of
   (
      "Big hop", 9,
      "Small hop", 1,
      "Big slip", -12,
      "Small slip", -2,
      "Fall asleep", 0
   );
   // where (for next 2 objects) structure is: [CHANCE], [ACTION]
   // where (for next 2 objects) CHANCE is: percentage of total 100
   private static final Map<String, Integer> TORTOISE_PROBABILITY_MAP = Map.of
   (
       "Fast plod", 50,
       "Slow plod", 30,
       "Slip", 20
   );
   private static final Map<String, Integer> HARE_PROBABILITY_MAP = Map.of
   (
       "Big hop", 20,
       "Small hop", 30,
       "Big slip", 10,
       "Small slip", 20,
       "Fall asleep", 20
   );
   // initialize messages to be used based on program's results/timing
   private static final String START_MESSAGE = "AND THEY'RE OFF!!";
   private static final String TIE_MESSAGE = "IT'S A TIE!!";
   private static final String TORTOISE_WIN_MESSAGE = "TORTOISE WINS!!";
   private static final String HARE_WIN_MESSAGE = "HARE WINS!!";

   public static void main(String[] args) {
      int finishPosition = getFinishPosition();
      System.out.println(Math.round(Math.random()*100));
      System.out.println(finishPosition);
   } // end main method

   private static int getFinishPosition() {
      Boolean correctInput = false;
      int finishPosition = 50;
      while (!correctInput)
      {
         Scanner inputPosition = new Scanner(System.in);
         System.out.print("Enter a Finishing Position (>49): ");
         if (inputPosition.hasNextInt() && inputPosition.nextInt() > 49)
         {
            System.out.println("Please enter an integer greater than 49!");
         }
         else
         {
            finishPosition = inputPosition.nextInt();
            correctInput = true;
            inputPosition.close();
         }
      }
      return finishPosition;
   } // end getFinishPosition method

} // end raceSim class