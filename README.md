# Tortoise vs. Hare Racing Simulator

This is project #1 of JHU's Programming in Java class (module 03 - 05), in partial fulfillment of the M.S. in Computer Science program. This project involves writing a program to simulate a tortoise and hare race.

## Program Specification:

The contenders will each race along a horizontal course that contains at least 50 positions. You may add more if you wish. The race begins with each contender at position 1. The contender that first reaches or passes the last position of the course is the winner of the race.

The following table indicates the types of moves that each contender can make.

### Contender Type of Move Percentage of Time Result of Move

For each move for each contender, generate a random integer, n, in the range 1 ≤ n ≤ 10.

| Contender | Type of Move | Percentage of Time | X ≤ n ≤ 10 | Result of Move  |
| :-------- | :----------- | :----------------- | :--------- | --------------- |
| Tortoise  | Fast plod    | 50%                | X = 1-5    | 3 squares right |
|           | Slow plod    | 20%                | X = 6-8    | 1 squares right |
|           | Slip         | 20%                | X = 9-10   | 6 squares left  |
|           |              |                    |            |                 |
| Hare      | Big hop      | 20%                | X = 1-2    | 9 squares right |
|           | Small hop    | 30%                | X = 3-5    | 1 squares right |
|           | Big slip     | 10%                | X = 6      | 12 squares left |
|           | Small slip   | 20%                | X = 7-8    | 2 squares left  |
|           | Fall asleep  | 20%                | X = 9-10   | 0 squares       |

Each contender starts at position 1. When a contender slips, they can’t slip any further left than Position 1. You will use a random number generator to simulate the percentages of each type of move indicated in the table. To generate random numbers, you can research the built-in Java random number method that is part of the Math class.

There are a number of ways to design this program. One way would be to have a looping construct be the overall controller of things. Each iteration would adjust the contender positions, and the loop would terminate when one of the contenders reaches the last square of the race course. You will decide on an approach as part of your design step.

You must keep track of each contender’s position and display it each time positions change. Show the letter “T” in the position of the tortoise, and the letter “H” in the position of the Hare. Upon moving, it is possible for the contenders to land on the same square. When this happens, the tortoise bites the hare, and your program should display “OUCH!!” beginning at that square. All output positions other than the “T”, the “H”, and the “OUCH!!” should be blank.

If the tortoise wins, display “TORTOISE WINS!!”. If the hare wins, display “HARE WINS!!”. If the race is a tie, display “IT’S A TIE!!”. At the beginning of the race, display “AND THEY’RE OFF!!”.

## Discussion Questions

See the project-01.md for full solution development details and thought-process.

- General program design. How is the program organized?
- What major data structures were used?
- What alternative approaches were considered and why were they rejected?
- What did you learn from doing this project and what would you do differently?
