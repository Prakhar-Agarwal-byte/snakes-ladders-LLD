package models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public void setupGame() {
        dice = new Dice(2);
        players = new LinkedList<>();
        inputBoardSize();
        inputSnakes();
        inputLadders();
        inputPlayers();
    }

    public void startGame() {
        while (players.size() > 1) {
            Player player = players.poll();
            int move = dice.roll();
            boolean hasWon = player.move(board, move);
            if (hasWon) {
                System.out.println(player.getName() + " won!");
            } else {
                players.add(player);
            }
        }
    }

    public void inputBoardSize() {
        int boardSize = scanner.nextInt();
        board = new Board(boardSize);
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
            players.add(player);
        }
    }
}
