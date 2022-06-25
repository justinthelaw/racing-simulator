
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
 * until one or both reach(es) the end position of the race, where a
 * message will be displayed to console identifying the winner or a tie
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

// a class that simulates a race between racers
// with live results printed to console
public class RaceSim {

   // instantiate Map and HashTable utilities to generate key-value pairings
   // human-readable parameter definition based on possible actions-results
   // where (for next 2 objects) structure is: [ACTION], [POSITIONS]
   private static final Map<String, Integer> TORTOISE_ACTION_MAP = Map.of
   (
      "FAST_PLOD", 3,
      "SLOW_PLOD", 1,
      "SLIP", -6
   );
   private static final Map<String, Integer> HARE_ACTION_MAP = Map.of
   (
      "BIG_HOP", 9,
      "SMALL_HOP", 1,
      "BIG_SLIP", -12,
      "SMALL_SLIP", -2,
      "FALL_ASLEEP", 0
   );

   // define the max req'd roll for each action happening
   // min req'd roll is bounded by the previous action's max
   // max roll is always 10/10
   // e.g. slowPlod requires a roll of >5 and <=8
   // tortoise chances
   private static final int FAST_PLOD = 5;
   private static final int SLOW_PLOD = 8;
   private static final int SLIP = 10;
   // hare chances
   private static final int BIG_HOP = 2;
   private static final int SMALL_HOP = 5;
   private static final int BIG_SLIP = 6; // exception to above rule, must =6
   private static final int SMALL_SLIP = 8;
   private static final int FALL_ASLEEP = 10;

   // initialize messages to be used based on program's results/timing
   private static final String START_MESSAGE = "\nAND THEY'RE OFF!!";
   private static final String TIE_MESSAGE = "\nIT'S A TIE!!";
   private static final String TORTOISE_WIN_MESSAGE = "\nTORTOISE WINS!!";
   private static final String HARE_WIN_MESSAGE = "\nHARE WINS!!";
   private static final String BITE_MESSAGE = "\tOUCH!!";
   private static final String ERROR_MESSAGE = "\nSOMETHING WENT WRONG!!\n";

   // initialize identifying ymbols for each racer
   // to be printed to console and use in roll position switch statement
   private static final String hareSymbol = "H";
   private static final String tortoiseSymbol = "T";


   /**
    * main method of RaceSim class
    * @param args
    */
   public static void main(String[] args) {
      // establish a finish position for the race
      int finishPosition = getFinishPosition();
      System.out.println(START_MESSAGE);
      // write FINISH LINE (centered) to console above finish line symbols
      int finishLinePosition = finishPosition - 5;
      for (int i = 0; i <= (finishLinePosition); i++) {
         if (i != finishLinePosition)
         {
            System.out.print(" ");
         }
         else
         {
            System.out.print("FINISH LINE\n");
         }
      }
      raceSimulator(finishPosition);
   } // end main method


   /**
    * method obtains the finish position from user input
    * disallows incorrect inputs (non-integer and <50 positions)
    * @return int
    */
   private static int getFinishPosition() {
      // initialize Scanner for getting user input
      Scanner input = new Scanner(System.in);
      System.out.print("\nEnter a finish position greater than 49: ");
      // initialize finishPosition to store integer inputs
      int finishPosition = input.nextInt();
      if (finishPosition < 50)
      { // ensure input is at least 50, else recursively ask until correct
         System.out.println("Please enter an integer greater than 49!\n");
         finishPosition = getFinishPosition();
      }
      input.close(); // close Scanner for resource control
      return finishPosition; // return finish position
   } // end getFinishPosition method


   /**
    * simulates the race by rolling racer position changes and
    * deciding which racer wins based on who is at/past the finish line
    * @param finishPosition is the finish line location
    */
   private static void raceSimulator(int finishPosition) {
      // initialize positions of racers
      int tortoisePosition = 0;
      int harePosition = 0;

      // initialize amount of "steps" (simulated seconds)
      int simulatedSeconds = 0;

      // print starting positions of racers
      printPosition(finishPosition, tortoisePosition, tortoiseSymbol);
      System.out.println();
      printPosition(finishPosition, harePosition, hareSymbol);
      System.out.println();

      do
      { // continue the race until a racer passes the finish line
         // roll and increment the position of each racer
         tortoisePosition += rollPosition(tortoisePosition, tortoiseSymbol);
         harePosition += rollPosition(harePosition, hareSymbol);
         // add a step every iteration
         simulatedSeconds++;
         // ensure the racers remain at finish line if they go past finish
         if (harePosition > finishPosition)
         {
            harePosition = finishPosition;
         }
         if (tortoisePosition > finishPosition)
         {
            tortoisePosition = finishPosition;
         }
         // print separation line between each step of the race
         for (int i = 0; i <= finishPosition; i++) {
            System.out.print("-");
         }
         System.out.println();
         // print positions using T and H symbols
         printPosition(finishPosition, tortoisePosition, tortoiseSymbol);
         System.out.println();
         printPosition(finishPosition, harePosition, hareSymbol);
         // check to see if the tortoise can bite the hare (after start)
         checkBite(harePosition, tortoisePosition);
      }
      while ((tortoisePosition < finishPosition) && (harePosition < finishPosition));

      // check to see which racer won and print finish message accordingly
      if ((tortoisePosition >= finishPosition) && (harePosition >= finishPosition))
      {
         System.out.println(TIE_MESSAGE);
      }
      else if (harePosition >= finishPosition)
      {
         System.out.println(HARE_WIN_MESSAGE);
      }
      else
      {
         System.out.println(TORTOISE_WIN_MESSAGE);
      }
      // print amount of "steps" (simulated seconds) the race took
      System.out.println("Race took " + simulatedSeconds + " simulated seconds to finish! There were " + finishPosition + " positions to get to the finish line.");
   } // end raceSimulator method


   /**
    * prints the position of the racer to the console
    * @param finishPosition is the finish line location
    * @param racerPosition is the current racer's location
    * @param racerSymbol is the racer (tortoise "T" or hare "H")
    */
   private static void printPosition(int finishPosition, int racerPosition, String racerSymbol) {
      for (int i = 0; i <= finishPosition; i++)
      { // print a space, the racer symbol, or the finish line
         if (i == racerPosition)
         {
            System.out.print(racerSymbol);
         }
         else if (i != finishPosition)
         {
            System.out.print(" ");
         }
         else
         { // finish line symbol
            System.out.print("|");
         }
      }
   } // end printPosition method


   /**
    * checks to see if one racer (tortoise) bites another (hare)
    * @param harePosition is the hare's current location
    * @param tortoisePosition is the tortoise's current location
    */
   private static void checkBite(int harePosition, int tortoisePosition) {

      if (harePosition == tortoisePosition)
      { // print bite message if racer positions are equal
         System.out.println(BITE_MESSAGE);
      }
      else
      { // otherwise, move to next line
         System.out.println();
      }

   } // end checkBite method


   /**
    * rolls the increment to be added to the racer's current position
    * @param position is the current position of the racer
    * @param symbol is the symbol of the racer
    * @return int
    */
   private static int rollPosition(int position, String symbol) {
      int increment = 0;
      int roll = (int) Math.round(Math.random()*10);

      switch(symbol)
      { // check symbol before using roll vs chances, action decisions
         case tortoiseSymbol:
            if (roll <= FAST_PLOD)
            {
               increment = TORTOISE_ACTION_MAP.get("FAST_PLOD");
            }
            else if ((roll > FAST_PLOD) && (roll <= SLOW_PLOD))
            {
               increment = TORTOISE_ACTION_MAP.get("SLOW_PLOD");
            }
            else if ((roll > SLOW_PLOD) && (roll <= SLIP))
            {
               increment = TORTOISE_ACTION_MAP.get("SLIP");
            }
            else
            {
               System.out.println(ERROR_MESSAGE);
            }
            break;
         case hareSymbol:
            if (roll <= BIG_HOP)
            {
               increment = HARE_ACTION_MAP.get("BIG_HOP");
            }
            else if ((roll > BIG_HOP) && (roll <= SMALL_HOP))
            {
               increment = HARE_ACTION_MAP.get("SMALL_HOP");
            }
            else if (roll == BIG_SLIP)
            {
               increment = HARE_ACTION_MAP.get("BIG_SLIP");
            }
            else if ((roll > BIG_SLIP) && (roll <= SMALL_SLIP))
            {
               increment = HARE_ACTION_MAP.get("SMALL_SLIP");
            }
            else if ((roll > SMALL_SLIP) && (roll <= FALL_ASLEEP))
            {
               increment = HARE_ACTION_MAP.get("FALL_ASLEEP");
            }
            else
            {
               System.out.println(ERROR_MESSAGE);
            }
            break;
         default:
          System.out.println(ERROR_MESSAGE);
      }

      if ((position + increment) < 0)
      { // ensure that position doesn't go negative (before start)
         increment = 0;
      }

      return increment;
   } // end rollPosition method

} // end raceSim class