package models;

public class Player {
    private String name;
    private Cell position;

    public Player(String name) {
        this.name = name;
        this.position = new Cell(0);
    }
    // move player by positions on board
    public boolean move(Board board, int positions) {
        int oldPosition = this.position.getPosition();
        int landingPosition = oldPosition + positions;
        if (landingPosition >= board.getBoard().size()) {
            System.out.println(this.name + " rolled a " + positions + " and moved from " + oldPosition + " to " + landingPosition);
            return true;
        } else {
            Cell landingCell = board.getBoard().get(landingPosition);
            landingCell = getFinalCell(landingCell);
            this.position = landingCell;
            System.out.println(this.name + " rolled a " + positions + " and moved from " + oldPosition + " to " + landingCell.getPosition());
            if (landingCell.getPosition() == board.getBoard().size()-1) return true;
            return false;
        }
    }
    // recursively get the final cell
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
}
