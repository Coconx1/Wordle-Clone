import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;


/** 
 * Contains the main logic for the wordle game
 * 
 * @author Colby Bennett
 * @version 1
 * 
 */
public class WordleController {
    @FXML
    Label title;
    // Gets the FX id of the title from FXML

    @FXML
    HBox guess1, guess2, guess3, guess4, guess5, guess6;
    // Gets the FX id of the HBox's from FXML

    Random rand = new Random();
    List<String> words = Arrays.asList("crane", "plant", "sneak", "spill", "shake", "shine", "crate", "about", "grate", "pzazz", "cycle", "shame", "thorn", "chase", "abide", "adult", "dream", "avoid", "where", "begin", "black", "brown", "after", "blood", "beach");   
    // Creates a bank of words
    int randIndex = rand.nextInt(words.size());
    // Takes a random number from 0 to the list size
    String guessWord = words.get(randIndex);
    // Gets a word from the list at the random index
    List<Character> letters = new ArrayList<>();
    // Creates List to hold the letters in the randWord
    int guessNum = 0;
    // Counter for the current guess
    HBox guessBox;
    // Createsvariable to hold a HBox

    /** 
     * Runs while game is creating.
     * Purpose is to to add letters to a list
     */
    @FXML
    private void initialize() {
        for (int i = 0; i < 5; i++) {
            // For loop to run 5 times
            letters.add(i, guessWord.charAt(i));
            // Adds the letter of the random word at the loop index
        }
        System.out.println(guessWord);
        // Uncomment to see correct word
    }

    /** 
     * When a key is typed guessLetter runs in purpose to move to the next textfield
     * @param kEvent ; key pressed
     */
    @FXML
    private void guessLetter(KeyEvent kEvent) {
        if (guessNum == 0) {
            guessBox = guess1;
        }
        else if (guessNum == 1) {
            guessBox = guess2;
        }
        else if (guessNum == 2) {
            guessBox = guess3;
        }
        else if (guessNum == 3) {
            guessBox = guess4;
        }
        else if (guessNum == 4) {
            guessBox = guess5;
        }
        else {
            guessBox = guess6;
        }
        // Sets what number guess based on the global counter

        boolean on = false;
        List<String> textBoxes = new ArrayList<String>();
        int count = 0;

        if (kEvent.getCode() != KeyCode.BACK_SPACE) {
            // Checks for if the keyboard inputted backspace
            for (Node child: guessBox.getChildren()) {
                // Runs through all the textfields for the given HBox
                TextField temp = (TextField)child;
                // Sets a temporary variable holding the textfield
                textBoxes.add(count, temp.getText());
                // Adds the text inside to a list

                if (on == true) {
                    // Runs when there is a letter in the textfiela
                    temp.requestFocus();
                    temp.setEditable(true);
                    // Changes textfield and sets it editable
                    on = false;
                    // Prevents if statement to run again
                }
                if ((textBoxes.get(count).length()) > 0 && count != 4) {
                    // Checks to see if any of teh first 4 textfields have a letter in them
                    on = true;
                    // If so it allows the prevoius if statement to run
                    temp.setEditable(false);
                    // Makes current textfield uneditable
                }
                count++;
                // increases count for the current textfield
            }
        }
    }

    /** 
     * runs when a key is released. Used to delete the letter and move to previous textfield
     * @param kEvent ; gets the key pressed 
     */
    @FXML
    private void delete(KeyEvent kEvent) {
        List<String> textBoxes = new ArrayList<String>();
        // Creates list to hold new letters in the textfields
        int count = -1;
        int highIndex = 0;
        
        Node finalBox = guessBox.getChildren().get(4);
        // Gets the last textfield
        TextField tempBox = (TextField)finalBox;
        // Sets this as a temporary variable

        if (kEvent.getCode() == KeyCode.BACK_SPACE && tempBox.getText().length() < 1) {
            // Checks if backspace is pressed and the temporary variable has less than one letter
            for (Node child: guessBox.getChildren()) {
                // Runs through all the textfields for the given HBox
                count++;
                // Increases count
                TextField temp = (TextField)child;
                // Sets a temporary varable as the textfield
                textBoxes.add(count, temp.getText());
                // Adds this the the list
                if (textBoxes.get(count).isEmpty()) {
                    // Checks to see if the text field is empty
                    highIndex = count;
                    // Takes the index of the prior box
                    break;
                    // Dnds for loop
                }   
            }

            if (highIndex > 0) {
                // Makes sure its not the first textfield
                TextField preBox = (TextField)guessBox.getChildren().get(highIndex - 1);
                // Sets the prior box as a temporary variable
                preBox.clear();
                preBox.setEditable(true);
                preBox.requestFocus();
                // Deletes the items inside setting editable and changing focus

            }
        }   
    }

    /** 
     * runs when a enter is pressed at the final textfield. Used to change HBox and compare the guess to word.
     */
    @FXML
    public void guessWord() throws IOException {
        List<Character> textBoxes = new ArrayList<Character>();
        int var = 0;
        int count = 0;
        // Creates list and 2 count variables
    
        for (Node oldChild: guessBox.getChildren()) {
            // Runs through all the textfields for the given HBox
            TextField oldTemp = (TextField)oldChild;
            // Sets a temporary variable as the textfield
            textBoxes.add(var, oldTemp.getText().charAt(0));
            // Adds the letter to the list ; There will only ever be 1 letter in box so .charAt(0) gives the letter
            if (var == 4) {
                // When counter is at 4
                oldTemp.setEditable(false);
                // Sets the final textfield uneditable
            }
            var++;
            // Increases count
        }

        int counter = 0;
        // Creates count variable 
        for (Node child: guessBox.getChildren()) {
            // Runs through all the textfields for the given HBox
            TextField temp = (TextField)child;
            // Sets a temporary variable as the textfield
            for (int i = 0; i < 5; i++) {
                // For loop to run 5 times
                if (temp.getText().charAt(0) == letters.get(i)) {
                    // Checks if the letter in both lists match
                    if (i != counter) {
                        // Checks if they match but not at the same index
                        temp.setStyle("-fx-background-color: yellow;");
                        // Sets textfield yellow
                    }
                    else {
                        // Checks if they match and at the same index
                        temp.setStyle("-fx-background-color: green;");
                        // Sets textfield green
                        count++;
                        // Increases count to see how many correct boxes user has
                        break;
                    }
                }      
            } 
            counter++;
            // Mathes counter with the correct textfield
        }
        if (count == 5) {
            // Checks if all five textboxes are correct
            App.setWinLose("win", guessWord);
            // Runs the method in App.java under the parameters of "win", and the random word
            App.setRoot("endScreen");
            // Sets the root as the end screen
        }

        guessNum++;
        // Increases counter to cehck which HBox textfields are edited on
        if (guessNum == 0) {
            guessBox = guess1;
        }
        else if (guessNum == 1) {
            guessBox = guess2;
        }
        else if (guessNum == 2) {
            guessBox = guess3;
        }
        else if (guessNum == 3) {
            guessBox = guess4;
        }
        else if (guessNum == 4) {
            guessBox = guess5;
        }
        else if (guessNum == 5) {
            guessBox = guess6;
        }
        // Sets what number HBox guess based on the global counter
        else {
            // Checks if user fails to get the word after 6 guesses
            App.setWinLose("lose", guessWord);
            // Runs the method in App.java under the parameters of "lose", and the random word
            App.setRoot("endScreen");
            // Sets the root as the end screen
        }

        for (Node child: guessBox.getChildren()) {
            // Runs through all the textfields for the given HBox
            TextField temp = (TextField)child;
            // Sets a temporary variable as the textfield
            temp.setEditable(true);
            temp.requestFocus();
            // Sets this textfield editable and gives it focus
            break;
        }
    }
}

