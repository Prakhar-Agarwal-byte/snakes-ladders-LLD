package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Board board;
    private List<Player> players;
    private Dice dice;
    private boolean isActive;

    public void setupGame() {
        board = new Board(100);;
        dice = new Dice(6);
        isActive = true;
        players = new ArrayList<>();
        inputSnakes();
        inputLadders();
        inputPlayers();
    }

    public void startGame() {
        while (isActive) {
            for (Player player : players) {
                if (isActive) {
                    int num = dice.roll();
                    player.move(board, num);
                    check();
                }
            }
        }
    }

    public void check() {
        Cell finishingCell = board.getBoard().get(100);
        if (!finishingCell.getPlayers().isEmpty()) {
            System.out.println(finishingCell.getPlayers().get(0).getName() + " has won the game!");
            isActive = false;
        }
    }

    public void inputSnakes() {
        int numSnakes = scanner.nextInt();
        for (int i = 0; i < numSnakes; i++) {
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            Cell headCell = board.getBoard().get(head);
            Cell tailCell = board.getBoard().get(tail);
            Snake snake = new Snake(headCell, tailCell);
            headCell.setSnake(snake);
        }
    }

    public void inputLadders() {
        int numLadders = scanner.nextInt();
        for (int i = 0; i < numLadders; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            Cell startCell = board.getBoard().get(start);
            Cell endCell = board.getBoard().get(end);
            Ladder ladder = new Ladder(startCell, endCell);
            startCell.setLadder(ladder);
        }
    }

    private void inputPlayers() {
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numPlayers; i++) {
            String playerName = scanner.nextLine();
            Cell startCell = board.getBoard().get(0);
            Player player = new Player(playerName, startCell);
            startCell.getPlayers().add(player);
            players.add(player);
        }
    }
}
