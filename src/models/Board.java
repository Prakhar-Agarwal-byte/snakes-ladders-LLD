package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Cell> board;

    public Board(int size) {
        board = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            board.add(new Cell(i));
        }
    }

    public List<Cell> getBoard() {
        return board;
    }

    public void setBoard(List<Cell> board) {
        this.board = board;
    }
}
