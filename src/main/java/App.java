
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static String winLose;
    private static String finalWord;

    private static Scene scene;
    /**
     * Returns the current scene
     * @return the scene
     */
    protected static Scene getScene(){
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("wordle"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    protected static void setWinLose(String winLoseIn, String finalWordIn) {
        winLose = winLoseIn;
        finalWord = finalWordIn;
    }

    protected static String getWinLose() {
        return winLose;
    }

    protected static String getFinalWord() {
        return finalWord;
    }

    public static void main(String[] args) {
        launch();
    }

}