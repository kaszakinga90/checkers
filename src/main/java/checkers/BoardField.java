package checkers;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BoardField extends Rectangle {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static double xPoint;
    private static double yPoint;
    static final Color BLACK_COLOR = Color.valueOf("#080808");
    private static final Color WHITE_COLOR = Color.WHITE;
    private static Set<PositionPoints> fieldsForBlack = boardForFirstPlayer();
    private static Set<PositionPoints> fieldsForWhite = boardForSecondPlayer();
    private static List<PositionPoints> infoToField = fieldsToInfo();
    private static List<PositionPoints> blackQueens = groupBlackQueens();
    private static List<PositionPoints> whiteQueens = groupWhiteQueens();

    private static Set<PositionPoints> boardForFirstPlayer() {
        Set<PositionPoints> pp = new HashSet<>();
        pp.add(new PositionPoints(1, 0));
        pp.add(new PositionPoints(3, 0));
        pp.add(new PositionPoints(5, 0));
        pp.add(new PositionPoints(7, 0));
        pp.add(new PositionPoints(0, 1));
        pp.add(new PositionPoints(2, 1));
        pp.add(new PositionPoints(4, 1));
        pp.add(new PositionPoints(6, 1));
        pp.add(new PositionPoints(1, 2));
        pp.add(new PositionPoints(3, 2));
        pp.add(new PositionPoints(5, 2));
        pp.add(new PositionPoints(7, 2));
        return pp;
    }

    private static Set<PositionPoints> boardForSecondPlayer() {
        Set<PositionPoints> pp = new HashSet<>();
        pp.add(new PositionPoints(0, 5));
        pp.add(new PositionPoints(2, 5));
        pp.add(new PositionPoints(4, 5));
        pp.add(new PositionPoints(6, 5));
        pp.add(new PositionPoints(1, 6));
        pp.add(new PositionPoints(3, 6));
        pp.add(new PositionPoints(5, 6));
        pp.add(new PositionPoints(7, 6));
        pp.add(new PositionPoints(0, 7));
        pp.add(new PositionPoints(2, 7));
        pp.add(new PositionPoints(4, 7));
        pp.add(new PositionPoints(6, 7));
        return pp;
    }

    private static List<PositionPoints> fieldsToInfo() {
        return new ArrayList<>();
    }

    private static List<PositionPoints> groupBlackQueens() {
        return new ArrayList<>();
    }

    private static List<PositionPoints> groupWhiteQueens() {
        return new ArrayList<>();
    }

    private Actions actions;
    double x;
    double y;
    private Color defaultColor;
    private Color choosenColor;
    boolean blackVanished;
    boolean whiteVanished;
    private PositionPoints killWhitePoints;
    private PositionPoints killBlackPoints;
    private PositionPoints vanishChecker;
    private PositionPoints falseBlack;
    private PositionPoints falseWhite;

    private Image iWhite = new Image(getClass().getResource("/whitePane.png").toString());
    private Image iBlack = new Image(getClass().getResource("/blackchecker.png").toString());
    private Image iWhiteQueen = new Image(getClass().getResource("/whiteQueen.png").toString());
    private Image iBlackQueen = new Image(getClass().getResource("/blackQueen.png").toString());

    public BoardField() {

    }

    BoardField(double x, double y, Actions actions) {
        super(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        this.x = x;
        this.y = y;
        this.actions = actions;

        boolean actualField = false;
        if ((y + x) % 2 == 1) {
            setFill(BLACK_COLOR);
            actualField = true;
            this.choosenColor = BLACK_COLOR;
            this.defaultColor = BLACK_COLOR;
        } else {
            setFill(WHITE_COLOR);
            this.choosenColor = Color.WHITE;
            this.defaultColor = Color.WHITE;
        }

        if (actualField) {
            setOnMouseClicked(event -> actions.mouseAction(this));
        }

        this.actions.addField(new PositionPoints(x, y), this);

        if(fieldsForBlack.contains(new PositionPoints(x, y))) {
            setFill(new ImagePattern(iBlack));
            setBlackVanished(true);
        }

        if(fieldsForWhite.contains(new PositionPoints(x, y))) {
            setFill(new ImagePattern(iWhite));
            setWhiteVanished(true);
        }
    }


    void blackMoveVerify(double x, double y) {
        if (x + 1 < 8 && y + 1 <= 7) {
            infoToField.add(new PositionPoints(x + 1, y + 1));
            actions.availableMoves(infoToField);
        }

        if (x - 1 >= 0 && y + 1 <= 7) {
            infoToField.add(new PositionPoints(x - 1, y + 1));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && (x + 2 < 8)
                && (y + 2 <= 7)) {
            infoToField.add(new PositionPoints(x + 2, y + 2));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && (x - 2 >= 0)
                && (y + 2 <= 7)) {
            infoToField.add(new PositionPoints(x - 2, y + 2));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x + 1, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x, y + 4))
                && (x + 2 < 8)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {
            infoToField.add(new PositionPoints(x, y + 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x - 1, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x, y + 4))
                && (x - 2 >= 0)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {
            infoToField.add(new PositionPoints(x, y + 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x + 3, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x + 4, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x + 4, y + 4))
                && (x + 4 < 8)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {
            infoToField.add(new PositionPoints(x + 4, y + 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x - 3, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x - 4, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x - 4, y + 4))
                && (x - 4 >= 0)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {
            infoToField.add(new PositionPoints(x - 4, y + 4));
            actions.availableMoves(infoToField);
        }

        if ((!fieldsForBlack.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && (y + 1 <= 7)
                && (x + 1 < 8))) {

            xPoint = x;
            yPoint = y;

        } else if (!fieldsForBlack.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && (y + 1 <= 7)
                && (x - 1 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && (y + 2 <= 7)
                && (x + 2 < 8)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && (y + 2 <= 7)
                && (x - 2 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x + 1, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x, y + 4))
                && (x + 2 < 8)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x - 1, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x, y + 4))
                && (x - 2 >= 0)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x + 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x + 3, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x + 4, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x + 4, y + 4))
                && (x + 4 < 8)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForWhite.contains(new PositionPoints(x - 1, y + 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y + 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y + 2))
                && fieldsForWhite.contains(new PositionPoints(x - 3, y + 3))
                && !fieldsForWhite.contains(new PositionPoints(x - 4, y + 4))
                && !fieldsForBlack.contains(new PositionPoints(x - 4, y + 4))
                && (x - 4 >= 0)
                && (y + 2 <= 7)
                && (y + 4 <= 7)) {

            xPoint = x;
            yPoint = y;

        } else {
            if (fieldsForBlack.size() - blackQueens.size() < 3) {
                actions.whiteWonVerify();
            }
            System.out.println("\u001B[31m" + "Move not available" + "\u001B[0m");
        }
        actions.generateSizes(fieldsForBlack.size(), fieldsForWhite.size(),
                blackQueens.size(), whiteQueens.size());
    }

    void blackMove(double x, double y) {

        if ((y - yPoint == 1) && (x - xPoint == 1 || x - xPoint == -1)) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }
        } else if ((y - yPoint == 2) && (x - xPoint == 2) && (yPoint + 2 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint + 2))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint + 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint + 1, yPoint + 1));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }
        } else if ((y - yPoint == 2) && (x - xPoint == -2) && (yPoint + 2 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint + 2))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint - 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint - 1, yPoint + 1));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }
        } else if ((y - yPoint == 4) && (xPoint == x) && (yPoint + 4 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint + 2))
                && fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 3))
                && !fieldsForWhite.contains(new PositionPoints(xPoint, yPoint + 4))
                && !fieldsForBlack.contains(new PositionPoints(xPoint, yPoint + 4))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint + 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            if (fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 3))) {
                killWhitePoints = new PositionPoints(xPoint + 1, yPoint + 3);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint + 1, yPoint + 1));
            fieldsForWhite.remove(new PositionPoints(xPoint + 1, yPoint + 3));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }

        } else if ((y - yPoint == 4) && (xPoint == x) && (yPoint + 4 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint + 2))
                && fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 3))
                && !fieldsForWhite.contains(new PositionPoints(xPoint, yPoint + 4))
                && !fieldsForBlack.contains(new PositionPoints(xPoint, yPoint + 4))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint - 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            if (fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 3))) {
                killWhitePoints = new PositionPoints(xPoint - 1, yPoint + 3);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint - 1, yPoint + 1));
            fieldsForWhite.remove(new PositionPoints(xPoint - 1, yPoint + 3));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }

        } else if ((y - yPoint == 4) && (x - xPoint == 4) && (yPoint + 4 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint + 2))
                && fieldsForWhite.contains(new PositionPoints(xPoint + 3, yPoint + 3))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 4, yPoint + 4))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 4, yPoint + 4))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint + 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint + 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            if (fieldsForWhite.contains(new PositionPoints(xPoint + 3, yPoint + 3))) {
                killWhitePoints = new PositionPoints(xPoint + 3, yPoint + 3);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint + 1, yPoint + 1));
            fieldsForWhite.remove(new PositionPoints(xPoint + 3, yPoint + 3));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }

        } else if ((y - yPoint == 4) && (x - xPoint == -4) && (yPoint + 4 <= 7)
                && fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint + 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint + 2))
                && fieldsForWhite.contains(new PositionPoints(xPoint - 3, yPoint + 3))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 4, yPoint + 4))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 4, yPoint + 4))) {

            this.setBlackVanished(true);
            falseBlack = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForBlack(falseBlack);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForWhite.contains(new PositionPoints(xPoint - 1, yPoint + 1))) {
                killWhitePoints = new PositionPoints(xPoint - 1, yPoint + 1);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            if (fieldsForWhite.contains(new PositionPoints(xPoint - 3, yPoint + 3))) {
                killWhitePoints = new PositionPoints(xPoint - 3, yPoint + 3);
                this.actions.killWhitePlayer(killWhitePoints);
            }
            fieldsForWhite.remove(new PositionPoints(xPoint - 1, yPoint + 1));
            fieldsForWhite.remove(new PositionPoints(xPoint - 3, yPoint + 3));
            fieldsForBlack.remove(new PositionPoints(xPoint, yPoint));
            fieldsForBlack.add(new PositionPoints(x, y));
            if (y == 7) {
                setFill(new ImagePattern(iBlackQueen));
                blackQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iBlack));
            }

        } else {
            System.out.println("\u001B[31m" + "Move not available" + "\u001B[0m");
        }
        actions.noAvailableMoves(infoToField);
        infoToField.clear();
        actions.generateSizes(fieldsForBlack.size(), fieldsForWhite.size(),
                blackQueens.size(), whiteQueens.size());
    }

    void whiteMoveVerify(double x, double y) {
        if (x + 1 < 8 && y - 1 >= 0) {
            infoToField.add(new PositionPoints(x + 1, y - 1));
            actions.availableMoves(infoToField);
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            infoToField.add(new PositionPoints(x - 1, y - 1));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && (x + 2 < 8)
                && (y - 2 >= 0)) {
            infoToField.add(new PositionPoints(x + 2, y - 2));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && (x - 2 >= 0)
                && (y - 2 >= 0)) {
            infoToField.add(new PositionPoints(x - 2, y - 2));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x + 1, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x, y - 4))
                && (x + 2 < 8)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {
            infoToField.add(new PositionPoints(x, y - 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x - 1, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x, y - 4))
                && (x - 2 >= 0)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {
            infoToField.add(new PositionPoints(x, y - 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x + 3, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x + 4, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x + 4, y - 4))
                && (x + 4 < 8)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {
            infoToField.add(new PositionPoints(x + 4, y - 4));
            actions.availableMoves(infoToField);
        }

        if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x - 3, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x - 4, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x - 4, y - 4))
                && (x - 4 >= 0)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {
            infoToField.add(new PositionPoints(x - 4, y - 4));
            actions.availableMoves(infoToField);
        }

        if ((!fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForWhite.contains(new PositionPoints(x + 1, y - 1))
                && (y - 1 >= 0)
                && (x + 1 < 8))) {

            xPoint = x;
            yPoint = y;

        } else if (!fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForWhite.contains(new PositionPoints(x - 1, y - 1))
                && (y - 1 >= 0)
                && (x - 1 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && (y - 2 >= 0)
                && (x + 2 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && (y - 2 >= 0)
                && (x - 2 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x + 1, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x, y - 4))
                && (x + 2 < 8)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x - 1, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x, y - 4))
                && (x - 2 >= 0)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x + 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x + 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x + 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x + 3, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x + 4, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x + 4, y - 4))
                && (x + 4 < 8)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else if (fieldsForBlack.contains(new PositionPoints(x - 1, y - 1))
                && !fieldsForBlack.contains(new PositionPoints(x - 2, y - 2))
                && !fieldsForWhite.contains(new PositionPoints(x - 2, y - 2))
                && fieldsForBlack.contains(new PositionPoints(x - 3, y - 3))
                && !fieldsForBlack.contains(new PositionPoints(x - 4, y - 4))
                && !fieldsForWhite.contains(new PositionPoints(x - 4, y - 4))
                && (x - 4 >= 0)
                && (y - 2 >= 0)
                && (y - 4 >= 0)) {

            xPoint = x;
            yPoint = y;

        } else {
            if (fieldsForWhite.size() - whiteQueens.size() < 3) {
                actions.blackWonVerify();
            }
            System.out.println("\u001B[31m" + "Move not available" + "\u001B[0m");
        }
        actions.generateSizes(fieldsForBlack.size(), fieldsForWhite.size(),
                blackQueens.size(), whiteQueens.size());
    }

    void whiteMove(double x, double y) {

        if ((-y + yPoint == 1) && (-x + xPoint == 1 || -x + xPoint == -1)) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 2) && (-x + xPoint == 2)
                && fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint - 2))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint - 1, yPoint -1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint - 1, yPoint -1));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 2) && (-x + xPoint == -2)
                && fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint - 2))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint + 1, yPoint -1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint + 1, yPoint - 1));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 4) && (xPoint == x)
                && fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint - 2))
                && fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 3))
                && !fieldsForBlack.contains(new PositionPoints(xPoint, yPoint - 4))
                && !fieldsForWhite.contains(new PositionPoints(xPoint, yPoint - 4))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint + 1, yPoint - 1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            if (fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 3))) {
                killBlackPoints = new PositionPoints(xPoint + 1, yPoint - 3);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint + 1, yPoint - 1));
            fieldsForBlack.remove(new PositionPoints(xPoint + 1, yPoint - 3));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 4) && (xPoint == x)
                && fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint - 2))
                && fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 3))
                && !fieldsForBlack.contains(new PositionPoints(xPoint, yPoint - 4))
                && !fieldsForWhite.contains(new PositionPoints(xPoint, yPoint - 4))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint - 1, yPoint - 1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            if (fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 3))) {
                killBlackPoints = new PositionPoints(xPoint - 1, yPoint - 3);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint - 1, yPoint - 1));
            fieldsForBlack.remove(new PositionPoints(xPoint - 1, yPoint - 3));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 4) && (-x + xPoint == -4)
                && fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 2, yPoint - 2))
                && fieldsForBlack.contains(new PositionPoints(xPoint + 3, yPoint - 3))
                && !fieldsForBlack.contains(new PositionPoints(xPoint + 4, yPoint - 4))
                && !fieldsForWhite.contains(new PositionPoints(xPoint + 4, yPoint - 4))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint + 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint + 1, yPoint - 1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            if (fieldsForBlack.contains(new PositionPoints(xPoint + 3, yPoint - 3))) {
                killBlackPoints = new PositionPoints(xPoint + 3, yPoint - 3);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint + 1, yPoint - 1));
            fieldsForBlack.remove(new PositionPoints(xPoint + 3, yPoint - 3));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else if ((-y + yPoint == 4) && (-x + xPoint == 4)
                && fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 2, yPoint - 2))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 2, yPoint - 2))
                && fieldsForBlack.contains(new PositionPoints(xPoint - 3, yPoint - 3))
                && !fieldsForBlack.contains(new PositionPoints(xPoint - 4, yPoint - 4))
                && !fieldsForWhite.contains(new PositionPoints(xPoint - 4, yPoint - 4))) {

            this.setWhiteVanished(true);
            falseWhite = new PositionPoints(xPoint, yPoint);
            actions.makeFalseForWhite(falseWhite);
            vanishChecker = new PositionPoints(xPoint, yPoint);
            this.actions.vanishChecker(vanishChecker);
            if (fieldsForBlack.contains(new PositionPoints(xPoint - 1, yPoint - 1))) {
                killBlackPoints = new PositionPoints(xPoint - 1, yPoint - 1);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            if (fieldsForBlack.contains(new PositionPoints(xPoint - 3, yPoint - 3))) {
                killBlackPoints = new PositionPoints(xPoint - 3, yPoint - 3);
                this.actions.killBlackPlayer(killBlackPoints);
            }
            fieldsForBlack.remove(new PositionPoints(xPoint - 1, yPoint - 1));
            fieldsForBlack.remove(new PositionPoints(xPoint - 3, yPoint - 3));
            fieldsForWhite.remove(new PositionPoints(xPoint, yPoint));
            fieldsForWhite.add(new PositionPoints(x, y));
            if (y == 0) {
                setFill(new ImagePattern(iWhiteQueen));
                whiteQueens.add(new PositionPoints(x, y));
            } else {
                setFill(new ImagePattern(iWhite));
            }

        } else {
            System.out.println("\u001B[31m" + "Move not available!" + "\u001B[0m");
        }
        actions.noAvailableMoves(infoToField);
        infoToField.clear();
        actions.generateSizes(fieldsForBlack.size(), fieldsForWhite.size(),
                blackQueens.size(), whiteQueens.size());
    }

    public boolean ifVanishBlack() {
        return blackVanished;
    }

    private void setBlackVanished(boolean takenBlack) {
        blackVanished = takenBlack;
    }

    public boolean ifVanishWhite() {
        return whiteVanished;
    }

    private void setWhiteVanished(boolean takenWhite) {
        whiteVanished = takenWhite;
    }

    public static Set<PositionPoints> getFieldsForBlack() {
        return fieldsForBlack;
    }

    public static Set<PositionPoints> getFieldsForWhite() {
        return fieldsForWhite;
    }

    public static List<PositionPoints> getBlackQueens() {
        return blackQueens;
    }

    public static List<PositionPoints> getWhiteQueens() {
        return whiteQueens;
    }
}