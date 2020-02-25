package checkers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

class Board {

    private Pane pBoard = new Pane();
    private Pane pInfo = new Pane();
    private Actions actions = new Actions(this);
    private Image iWhite = new Image(getClass().getResource("/whitePane.png").toString());
    private Image iBlack = new Image(getClass().getResource("/blackchecker.png").toString());
    private Image iPrize = new Image(getClass().getResource("/prize.jpg").toString());
    private Rectangle rBlack = new Rectangle(-35, 0, 50, 50);
    private Rectangle rWhite = new Rectangle(-35, 750, 50, 50);
    private Rectangle rWon = new Rectangle(25, 350, 100,100);
    private Rectangle rPrize = new Rectangle(50, 375, 50, 50);
    private List<Integer> blackWon = new ArrayList<>();
    private List<Integer> whiteWon = new ArrayList<>();
    private int defBlack = 0;
    private int defWhite = 0;

    void startNewBoard() {
        Rectangle frame = new Rectangle(-1, -1, 802, 802);
        pBoard.getChildren().add(frame);
        rWhite.setFill(new ImagePattern(iWhite));
        rBlack.setFill(Color.TRANSPARENT);
        rWon.setFill(Color.TRANSPARENT);
        rPrize.setFill(Color.TRANSPARENT);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                BoardField boardField = new BoardField(y, x, actions);
                pBoard.getChildren().add(boardField);
            }
        }
    }

    void setInfo(boolean info) {
        if (info) {
            rBlack.setFill(new ImagePattern(iBlack));
            rWhite.setFill(Color.TRANSPARENT);
        } else {
            rWhite.setFill(new ImagePattern(iWhite));
            rBlack.setFill(Color.TRANSPARENT);
        }
    }

    void setWonBlack(boolean wonBlack) {
        System.out.println("Black won!");
        if (wonBlack) {
            blackWon.add(defBlack);
            defBlack += 1;
            rWon.setFill(Color.valueOf("#921919"));
            rPrize.setFill(new ImagePattern(iPrize));
        }
    }

    void setWonWhite(boolean wonWhite) {
        System.out.println("White won!");
        if (wonWhite) {
            whiteWon.add(defWhite);
            defWhite += 1;
            rWon.setFill(Color.valueOf("7FACAA"));
            rPrize.setFill(new ImagePattern(iPrize));
        }
    }

    Pane getpBoard() {
        return pBoard;
    }

    Pane getpInfo() {
        pInfo.getChildren().addAll(rBlack, rWhite, rWon, rPrize);
        return pInfo;
    }
}