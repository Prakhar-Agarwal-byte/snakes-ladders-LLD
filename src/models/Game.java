package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Board board;
    private List<Player> players;
    private List<Dice> dices;

    public void setupGame() {
        dices = new ArrayList<>();
        Dice dice1 = new Dice(6);
//        Dice dice2 = new Dice(6);
        dices.add(dice1);
//        dices.add(dice2);
        players = new ArrayList<>();
        inputBoardSize();
        inputSnakes();
        inputLadders();
        inputPlayers();
    }

    public void startGame() {
        while (players.size() > 1) {
            for (Player player : players) {
                if (!player.isWon()) {
                    int move = rollDice(0);
                    player.move(board, move);
                    checkGameState();
                }
            }
            players = players.stream().filter(p -> !p.isWon()).collect(Collectors.toList());
        }
    }

    private int rollDice(int times) {
        if (times >= 3) {
            return 0;
        }
        int totalMoves = 0;
        for (Dice dice: dices) {
            totalMoves += dice.roll();
        }
        if (totalMoves == 6) {
            int ans = rollDice(times+1);
            if (ans == 0) return 0;
            return totalMoves + rollDice(times+1);
        }
        return totalMoves;
    }

    private void checkGameState() {
        Cell finishingCell = board.getBoard().get(board.getBoard().size()-1);
        if (!finishingCell.getPlayers().isEmpty()) {
            System.out.println(finishingCell.getPlayers().get(0).getName() + " has won the game!");
            Player finishedPlayer = finishingCell.getPlayers().get(0);
            finishingCell.getPlayers().remove(finishedPlayer);
            finishedPlayer.setWon(true);
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
            startCell.getPlayers().add(player);
            players.add(player);
        }
    }
}
