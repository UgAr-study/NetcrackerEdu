package zonk;

import java.util.Random;
import java.util.Scanner;

public class ZonkGame {

    private static final Scanner in = new Scanner(System.in);
    private static Random random = new Random();
    /**
     * starts the interactive game
     */
    public static void startGame () {

        System.out.print("Enter the number of players: ");
        int nPlayers = in.nextInt();

        System.out.print("Enter the number of dices: ");
        int nDices = in.nextInt();

        in.nextLine();

        if ((nPlayers < 1) || (nDices < 1)) {
            System.out.println("number of players and number of dices must not be less then one\n" +
                    "Cannot continue the game. Please, restart the game with an appropriate parameters");
            return;
        }

        Player[] players = new Player[nPlayers];

        initPlayers(players);

        int idOfFirstPlayer = 0;
        for (int i = 1; i <= 3; ++i) {
            System.out.println("\n==========================================\n");
            System.out.println("Attempt № " + i + "\n");
            idOfFirstPlayer = playRound(players, nDices, idOfFirstPlayer);
        }

        congratulateWinner(players);
        System.out.println("Game over!");
    }

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

    private static int playRound (Player[] players, int nDices, int idOfFirst) {

        int maxScore = 6 * nDices;
        int idOfWinner = idOfFirst;
        int max = 0;
        int bound = players.length + 1;

        for (int i = idOfFirst; i < bound; ++i) {

            if (i == players.length) {
                i = -1;
                bound = idOfFirst;
                continue;
            }

            int score = doPlayerMove(players[i], maxScore);
            if (score > max) {
                max = score;
                idOfWinner = i;
            }

            System.out.println("\n");
        }

        return idOfWinner;
    }

    private static int doPlayerMove (Player player, int maxScore) {

        if (!player.isReal) {
            return doComputerMove(player, maxScore);
        }

        int randSeed = 0;
        start:
        {
            System.out.print(player.name + ", enter any integer number in range from 0 to 100: ");
            randSeed = in.nextInt();
            in.nextLine();

            if ((randSeed < 0) || (randSeed > 100)) {
                System.out.println("Wrong number! Try again.");
                break start;
            }
        }

        int score = (random.nextInt(maxScore) + randSeed) % maxScore;

        player.score += score;
        System.out.println("You have got " + score + "!\n" +
                "Your current score is " + player.score);

        return score;
    }

    private static int doComputerMove (Player player, int maxScore) {

        int randSeed = random.nextInt(100);
        System.out.println(player.name + ", enter any integer number in range from 0 to 100: " + randSeed);

        int score = (random.nextInt(maxScore) + randSeed) % maxScore;

        player.score += score;
        System.out.println("You have got " + score + "!\n" +
                "Your current score is " + player.score);

        return score;
    }

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

    private static void congratulateWinner (Player[] players) {

        int id = findMaxScoreId(players);
        System.out.println(players[id].name + ", you are winner!\n" +
                "Your score is " + players[id].score);
    }
}

class Player {
    String name;
    long score;
    boolean isReal;
}
