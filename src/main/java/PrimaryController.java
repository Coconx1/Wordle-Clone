import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PrimaryController {

    // Sets the FX id in the "endScreen.FXML" visable 
    @FXML
    Text winLoseLabel;
    @FXML
    Text finalWord;
    @FXML
    Button replayBtn;

    /** 
     * Initializes the screen saying if the user has won or lost depending on the game outcome
     */
    @FXML
    private void initialize() {
        if (App.getWinLose().equals("win")) {
            // Gets the win or lose outcome from the method checking if its "win"
            winLoseLabel.setText("You Won!");
            winLoseLabel.setFill(Color.GREEN);
            // Changes color and text on the screen
        }
        else {
            // if the outcome is not "win"
            winLoseLabel.setText("You Lose!");
            winLoseLabel.setFill(Color.RED);
            // Changes color and text on the screen
        }
        finalWord.setText("The word was: " + App.getFinalWord().toUpperCase());
        finalWord.setFill(Color.PURPLE);
        // Gets the random word printing on screen and changing color

    }

    /** 
     * Sets the root of App to the wordle.FXML to go back to the main game
     */
    @FXML
    private void replay() throws IOException {
        App.setRoot("wordle");
        // Sets the root back to the wordle game
    }
}
