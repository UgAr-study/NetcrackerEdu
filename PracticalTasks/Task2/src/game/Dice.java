package game;

import java.util.Random;
import java.util.Scanner;

/**
 * the main class, which maintain the game
 */
public class Dice implements Game {

    // input stream
    private Scanner in = new Scanner(System.in);
    // random generator to simulate the role of dice
    private Random random = new Random();
    // id of the player, who starts the round
    private int idOfFirst = 0;
    // the maximum score which player can get in round
    private int limitScore = 0;
    // the number of rounds in this game
    private final int N_ROUNDS = 2;
    // players of the game
    private Player[] players;

    /**
     * starts the interactive game
     */
    @Override
    public void startGame () {

        int nPlayers = 0;
        int nDice = 0;

        do {
            System.out.print("Enter the number of players: ");
            nPlayers = in.nextInt();

            System.out.print("Enter the number of dice: ");
            nDice = in.nextInt();

            in.nextLine();

        } while ((nPlayers < 1) || (nDice < 1));

        limitScore = 6 * nDice;

        players = new Player[nPlayers];

        initPlayers();

        for (int i = 1; i <= N_ROUNDS; ++i) {

            System.out.println("\n==========================================\n");
            System.out.println("Attempt № " + i + "\n");
            playRound();
        }
    }

    /**
     * initializes the players (names and statuses)
     */
    private void initPlayers () {

        for (int i = 0; i < players.length - 1; ++i) {

            System.out.print("Please, enter the name of player № " + i + ": ");
            String name = in.nextLine();

            players[i] = new Player(name, random);
        }

        players[players.length - 1] = new Computer("Computer", random);
    }

    /**
     * congratulate the winner of the game
     */
    @Override
    public void prizeTheWinner() {
        int winnerId = findMaxTotalScoreId();
        System.out.println(players[winnerId].name + ", you are winner!\n" +
                "Your score is " + players[winnerId].totalScore);
    }

    /**
     * finish game and free resources
     */
    @Override
    public void endGame() {
        in = null;
        random = null;
        players = null;
        System.out.println("Game over!");
    }

    /**
     * play one round, i.e. let every player roll the dice
     */
    private void playRound () {

        // identifier of the winner of this round, which will start the next round
        int idOfWinner = idOfFirst;

        // max score in this round (winner's score)
        int max = 0;

        // bound for iterations (in order to do all staff in only one for)
        int bound = players.length + 1;

        for (int i = idOfFirst; i < bound; ++i) {

            /*
            if we had reached the end, we must start from beginning till
            the id, which was the first in this round
             */
            if (i == players.length) {
                i = -1;
                bound = idOfFirst;
                continue;
            }

            int score = players[i].doMove(limitScore);
            if (score > max) {
                max = score;
                idOfWinner = i;
            }

            System.out.println("\n");
        }

        idOfFirst = idOfWinner;
    }

    /**
     * find the id of player, who has the highest score
     * @return the id of player with highest score
     */
    private int findMaxTotalScoreId() {

        int id = 0;
        long max = players[0].totalScore;

        for (int i = 1; i < players.length; ++i) {

            if (players[i].totalScore > max) {
                max = players[i].totalScore;
                id = i;
            }
        }

        return id;
    }
}

/**
 * class of Computer-player in Dice Game
 */
class Computer extends Player {

    public Computer(String name, Random random) {
        super(name, random);
    }

    /**
     * simulating rolling the dice like the {@code Player},
     * but we don't need to ask the computer to enter the number
     * @return the result number of this simulation
     */
    @Override
    protected int getRandomSeed() {
        int res = random.nextInt(100);
        System.out.println(name + ", enter any integer number in range from 0 to 100: " + res);
        return res;
    }
}
