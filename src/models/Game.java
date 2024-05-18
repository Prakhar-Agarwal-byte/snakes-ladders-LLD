package models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public void setupGame() {
        /* Take input from user
        players = new LinkedList<>();
        inputBoardSize();
        inputNumDice();
        inputSnakes();
        inputLadders();
        inputPlayers();
         */
        // Generate complete game automatically
        players = new LinkedList<>();
        players.add(new Player("Prakhar"));
        players.add(new Player("Prachi"));
        players.add(new Player("Pratik"));
        players.add(new Player("Preeti"));
        board = new Board(100);
        dice = new Dice(2);
        int numSnakes = 10;
        int numLadders = 10;
        Random random = new Random();
        int snakes = 0;
        while (snakes < numSnakes) {
            int head = random.nextInt(board.getBoard().size());
            if (head == board.getBoard().size() - 1) continue;
            int tail = random.nextInt(board.getBoard().size());
            if (tail >= head) continue;
            Cell headCell = board.getBoard().get(head);
            if (headCell.getSnake() != null) continue;
            Cell tailCell = board.getBoard().get(tail);
            Snake snake = new Snake(headCell, tailCell);
            headCell.setSnake(snake);
            snakes++;
        }
        int ladders = 0;
        while (ladders < numLadders) {
            int start = random.nextInt(board.getBoard().size());
            if (start == 0) continue;
            int end = random.nextInt(board.getBoard().size());
            if (start >= end) continue;
            Cell startCell = board.getBoard().get(start);
            if (startCell.getLadder() != null) continue;
            Cell endCell = board.getBoard().get(end);
            if (endCell.getSnake() != null && endCell.getSnake().getTail() == startCell) continue;
            Ladder ladder = new Ladder(startCell, endCell);
            startCell.setLadder(ladder);
            ladders++;
        }
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

    private void inputNumDice() {
        int numDice = scanner.nextInt();
        dice = new Dice(numDice);
    }

    private void inputBoardSize() {
        int boardSize = scanner.nextInt();
        board = new Board(boardSize);
    }

    private void inputSnakes() {
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

    private void inputLadders() {
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
            Player player = new Player(playerName);
            players.add(player);
        }
    }
}
