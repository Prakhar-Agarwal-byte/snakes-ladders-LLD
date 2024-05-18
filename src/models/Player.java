package models;

public class Player {
    private String name;
    private Cell position;
    private boolean isWon;

    public Player(String name, Cell position) {
        this.name = name;
        this.position = position;
        this.isWon = false;
    }

    public void move(Board board, int positions) {
        int oldPosition = this.position.getPosition();
        int landingPosition = oldPosition + positions;
        if (landingPosition >= board.getBoard().size()) return;
        Cell landingCell = board.getBoard().get(landingPosition);
        landingCell = getFinalCell(landingCell);
        this.position.getPlayers().remove(this);
        landingCell.getPlayers().add(this);
        this.position = landingCell;
        System.out.println(this.name + " rolled a " + positions + " and moved from " + oldPosition + " to " + landingCell.getPosition());
    }

    private Cell getFinalCell(Cell cell) {
        if (cell.getSnake() != null) {
            return getFinalCell(cell.getSnake().getTail());
        }
        if (cell.getLadder() != null) {
            return getFinalCell(cell.getLadder().getEnd());
        }
        return cell;
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

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
