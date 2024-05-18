package models;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int position;
    private Snake snake;
    private Ladder ladder;
    private List<Player> players;

    public Cell(int position) {
        this.position = position;
        this.snake = null;
        this.ladder = null;
        this.players = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
