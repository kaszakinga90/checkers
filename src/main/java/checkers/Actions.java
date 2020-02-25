package checkers;

import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Actions {

    private Map<PositionPoints, BoardField> mapOfGame = new HashMap<>();
    private boolean ifChoosenWhite = true;
    private boolean ifChoosenField = false;
    private boolean whiteMayWon = false;
    private boolean blackMayWon = false;
    private int blackFieldSize = 12;
    private int whiteFieldSize = 12;
    private int blackQueenFieldSize;
    private int whiteQueenFieldSize;
    private Board board;

    Actions(Board board) {
        this.board = board;
    }

    void addField(PositionPoints positionPoints, BoardField boardField) {
        mapOfGame.put(positionPoints, boardField);
    }

    void mouseAction(BoardField boardField) {
        if (!ifChoosenWhite) {
            if (boardField.blackVanished) {
                boardField.blackMoveVerify(boardField.x, boardField.y);
                ifChoosenField = true;
                board.setWonWhite(whiteMayWon);
                board.setWonBlack(blackMayWon);
            } else if (ifChoosenField) {
                boardField.blackMove(boardField.x, boardField.y);
                ifChoosenWhite = true;
                ifChoosenField = false;
                board.setInfo(false);
            }
        } else {
            if (boardField.whiteVanished) {
                boardField.whiteMoveVerify(boardField.x, boardField.y);
                ifChoosenField = true;
                board.setWonWhite(whiteMayWon);
                board.setWonBlack(blackMayWon);
            } else if (ifChoosenField) {
                boardField.whiteMove(boardField.x, boardField.y);
                ifChoosenWhite = false;
                ifChoosenField = false;
                board.setInfo(true);
            }
        }
        if (BoardField.getFieldsForBlack().size() - BoardField.getBlackQueens().size() == 0) {
            board.setWonWhite(true);
        }
        if (BoardField.getFieldsForWhite().size() - BoardField.getWhiteQueens().size() == 0) {
            board.setWonBlack(true);
        }
    }

    void makeFalseForBlack(PositionPoints positionPoints) {
        BoardField makeBlackFalse = mapOfGame.get(positionPoints);
        makeBlackFalse.blackVanished = false;
    }

    void makeFalseForWhite(PositionPoints positionPoints) {
        BoardField makeWhiteFalse = mapOfGame.get(positionPoints);
        makeWhiteFalse.whiteVanished = false;
    }

    void vanishChecker(PositionPoints positionPoints) {
        BoardField checkerToVanish = mapOfGame.get(positionPoints);
        checkerToVanish.setFill(BoardField.BLACK_COLOR);
    }

    void killWhitePlayer(PositionPoints positionPoints) {
        BoardField wantKillWhite = mapOfGame.get(positionPoints);
        wantKillWhite.whiteVanished = false;
        wantKillWhite.setFill(BoardField.BLACK_COLOR);
    }

    void killBlackPlayer(PositionPoints positionPoints) {
        BoardField wantKillBlack = mapOfGame.get(positionPoints);
        wantKillBlack.blackVanished = false;
        wantKillBlack.setFill(BoardField.BLACK_COLOR);
    }

    void availableMoves(List<PositionPoints> aMoves) {
        for (PositionPoints available : aMoves) {
            BoardField boardField = mapOfGame.get(available);
            if (!boardField.whiteVanished && !boardField.blackVanished) {
                boardField.setFill(Color.valueOf("#e3e3e3"));
            }
        }
    }

    void noAvailableMoves(List<PositionPoints> aMoves) {
        for (PositionPoints available: aMoves) {
            BoardField boardField = mapOfGame.get(available);
            if (!boardField.blackVanished && !boardField.whiteVanished) {
                boardField.setFill(BoardField.BLACK_COLOR);
            }
        }
    }

    void generateSizes(int blackPlr, int whitePlr, int blackQueenPlr, int whiteQueenPlr) {
        whiteFieldSize = whitePlr;
        blackFieldSize = blackPlr;
        blackQueenFieldSize = blackQueenPlr;
        whiteQueenFieldSize = whiteQueenPlr;
    }

    void whiteWonVerify() {
        if ((blackFieldSize - blackQueenFieldSize < whiteFieldSize - whiteQueenFieldSize)
                && (whiteQueenFieldSize > blackQueenFieldSize)) {
            whiteMayWon = true;
        }
    }

    void blackWonVerify() {
        if ((whiteFieldSize - whiteQueenFieldSize < blackFieldSize - blackQueenFieldSize)
                && (blackQueenFieldSize > whiteQueenFieldSize)) {
            blackMayWon = true;
        }
    }
}