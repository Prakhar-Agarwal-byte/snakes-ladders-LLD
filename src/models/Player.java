package models;

public class Player {
    private String name;
    private Cell position;

    public Player(String name, Cell position) {
        this.name = name;
        this.position = position;
    }

    public void move(Board board, int positions) {
        int oldPosition = this.position.getPosition();
        int landingPosition = oldPosition + positions;
        if (landingPosition >= board.getBoard().size()) return;
        Cell landingCell = board.getBoard().get(landingPosition);
        this.position.getPlayers().remove(this);
        Snake landingCellSnake = landingCell.getSnake();
        Ladder landingCellLadder = landingCell.getLadder();
        if (landingCellSnake != null) {
            landingCell = landingCellSnake.getTail();
        }
        if (landingCellLadder != null) {
            landingCell = landingCellLadder.getEnd();
        }
        landingCell.getPlayers().add(this);
        this.position.setPosition(landingPosition);
        System.out.println(this.name + " rolled a " + positions + " and moved from " + oldPosition + " to " + landingCell.getPosition());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
