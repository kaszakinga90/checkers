package checkers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CheckersMain extends Application {

    private Image image = new Image(getClass().getResource("/background.jpg").toString());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Board board = new Board();
        board.startNewBoard();
        BackgroundSize backgroundSize = new BackgroundSize(1000, 1000,
                true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        grid.add(board.getpBoard(), 1, 0);
        grid.add(board.getpInfo(), 0, 0);

        Scene scene = new Scene(grid, 1100, 900);
        primaryStage.setTitle("New awesome game - checkers!");
        primaryStage.setHeight(880);
        primaryStage.setWidth(1050);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}