package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
    List<Integer> numbers;

    public Dice(int max) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            numbers.add(i);
        }
        this.numbers = numbers;
    }

    public Integer roll() {
        Random random = new Random();
        int idx = random.nextInt(numbers.size());
        return numbers.get(idx);
    }
}
