package game;

import java.util.Random;
import java.util.Scanner;

/**
 * the main class, which maintain the game
 */
public class Dice {

    // input stream
    private static final Scanner in = new Scanner(System.in);
    // random generator to simulate the role of dice
    private static Random random = new Random();
    // id of the player, who starts the round
    private static int idOfFirst = 0;
    // the maximum score which player can get in round
    private static int maxScore = 0;
    // the number of rounds in this game
    private static final int N_ROUNDS = 7;

    /**
     * starts the interactive game
     */
    public static void startGame () {

        int nPlayers = 0;
        int nDice = 0;

        do {
            System.out.print("Enter the number of players: ");
            nPlayers = in.nextInt();

            System.out.print("Enter the number of dice: ");
            nDice = in.nextInt();

            in.nextLine();

        } while ((nPlayers < 1) || (nDice < 1));

        maxScore = 6 * nDice;

        Player[] players = new Player[nPlayers];

        initPlayers(players);

        for (int i = 1; i <= N_ROUNDS; ++i) {

            System.out.println("\n==========================================\n");
            System.out.println("Attempt № " + i + "\n");
            playRound(players);
        }

        congratulateWinner(players);

        System.out.println("Game over!");
    }

    /**
     * initializes the players (names and statuses)
     * @param players an array of players
     */
    private static void initPlayers (Player[] players) {

        for (int i = 0; i < players.length - 1; ++i) {

            System.out.print("Please, enter the name of player № " + i + ": ");
            String name = in.nextLine();

            players[i] = new Player();
            players[i].name = name;
            players[i].isReal = true;
        }

        players[players.length - 1] = new Player();
        players[players.length - 1].name = "Computer";
        players[players.length - 1].isReal = false;
    }

    /**
     * play one round, i.e. let every player roll the dice
     * @param players array of players
     */
    private static void playRound (Player[] players) {

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

            int score = doPlayerMove(players[i]);
            if (score > max) {
                max = score;
                idOfWinner = i;
            }

            System.out.println("\n");
        }

        idOfFirst = idOfWinner;
    }

    /**
     * let the current player to roll the dice
     * @param player player, whose turn is to roll the dice
     * @return the score, which player has got in this round
     */
    private static int doPlayerMove (Player player) {

        if (!player.isReal) {
            return doComputerMove(player);
        }

        int randSeed = 0;
        do {
            System.out.print(player.name + ", enter any integer number in range from 0 to 100: ");
            randSeed = in.nextInt();
            in.nextLine();
        } while ((randSeed < 0) || (randSeed > 100));

        int score = (random.nextInt(maxScore) + randSeed) % maxScore;

        player.score += score;
        System.out.println("You have got " + score + "!\n" +
                "Your current score is " + player.score);

        return score;
    }

    /**
     * let the computer to roll the dice
     * it differs from player's move, because
     * the computer doesn't need to be asked
     * to input the random number
     * @param player computer
     * @return the score, which computer has got in this round
     */
    private static int doComputerMove (Player player) {

        int randSeed = random.nextInt(100);
        System.out.println(player.name + ", enter any integer number in range from 0 to 100: " + randSeed);

        int score = (random.nextInt(maxScore) + randSeed) % maxScore;

        player.score += score;
        System.out.println("You have got " + score + "!\n" +
                "Your current score is " + player.score);

        return score;
    }

    /**
     * find the id of player, who has the highest score
     * @param players array of players
     * @return the id of player with highest score
     */
    private static int findMaxScoreId (Player[] players) {

        int id = 0;
        long max = players[0].score;

        for (int i = 1; i < players.length; ++i) {

            if (players[i].score > max) {
                max = players[i].score;
                id = i;
            }
        }

        return id;
    }

    /**
     * congratulate the player, who has the highest score in this game
     * @param players array of players
     */
    private static void congratulateWinner (Player[] players) {

        int id = findMaxScoreId(players);
        System.out.println(players[id].name + ", you are winner!\n" +
                "Your score is " + players[id].score);
    }
}

/**
 * class of player of Zonk Game
 */
class Player {
    String name;
    long score;
    boolean isReal;
}
