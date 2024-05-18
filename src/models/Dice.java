package models;

import java.util.Random;

public class Dice {
    int numDice;

    public Dice(int numDice) {
        this.numDice = numDice;
    }

    public Integer roll() {
        Random random = new Random();
        int move = 0;
        for (int i = 0; i < numDice; i++) {
            move += random.nextInt(6) + 1;
        }
        if (move == 6) {
            for (int i = 0; i < numDice; i++) {
                move += random.nextInt(6) + 1;
            }
        }
        if (move == 12) {
            for (int i = 0; i < numDice; i++) {
                move += random.nextInt(6) + 1;
            }
        }
        if (move == 18) {
            return 0;
        }
        return move;
    }
}
