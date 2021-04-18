package game;

import java.util.Random;
import java.util.Scanner;

/**
 * class of player in Dice Game
 */
public class Player {

    protected Random random;
    public String name;
    public long totalScore = 0;

    public Player (String name, Random random) {
        this.name = name;
        this.random = random;
    }

    /**
     * let the current player to roll the dice
     * @param limitScore is the maximum score, which player can get in one move
     * @return the score, which player has got in this move
     */
    public int doMove(int limitScore){
        int randSeed = 0;

        do {
            randSeed = getRandomSeed();
        } while ((randSeed < 0) || (randSeed > 100));

        int score = (random.nextInt(limitScore) + randSeed) % limitScore;

        this.totalScore += score;
        System.out.println("You have got " + score + "!\n" +
                "Your current score is " + totalScore);

        return score;
    }

    /**
     * simulating rolling the dice
     * @return the result number of this simulation
     */
    protected int getRandomSeed() {
        Scanner in = new Scanner(System.in);
        System.out.print(name + ", enter any integer number in range from 0 to 100: ");
        int res = in.nextInt();
        in.nextLine();
        in = null;
        return res;
    }
}