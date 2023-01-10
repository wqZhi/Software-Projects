//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Matching Game
// Course:   CS 300 Fall 2020
//
// Author:   Weiqian Zhi
// Email:    wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class contain a matching game program.
 */
public class MemoryGame {
    // Congratulations message
    private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
    // Cards not matched message
    private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
    // Cards matched message
    private final static String MATCHED = "CARDS MATCHED! Good Job!";
    // 2D-array which stores cards coordinates on the window display
    private final static float[][] CARDS_COORDINATES =
        new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170}, {170, 324}, {324, 324},
            {478, 324}, {632, 324}, {170, 478}, {324, 478}, {478, 478}, {632, 478}};
    // Array that stores the card images filenames
    private final static String[] CARD_IMAGES_NAMES =
        new String[] {"ball.png", "redFlower.png", "yellowFlower.png", "apple.png", "peach.png",
            "shark.png"};

    private static PApplet processing; // PApplet object that represents the graphic display window
    private static Card[] cards; // one dimensional array of cards
    private static PImage[] images; // array of images of the different cards
    private static Card selectedCard1; // First selected card
    private static Card selectedCard2; // Second selected card
    private static boolean winner; // boolean evaluated true if the game is won, and false otherwise
    private static int matchedCardsCount; // number of cards matched so far in one session of the game
    private static String message; // Displayed message to the display window

    /**
     * This main method implement a start application.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Utility.startApplication();
    }

    /**
     * Defines the initial environment properties of this game as the program starts
     */
    public static void setup(PApplet processing) {
        MemoryGame.processing = processing;
        images = new PImage[CARD_IMAGES_NAMES.length];

        for (int i = 0; i < images.length; i++) {
            images[i] = processing.loadImage("images"
                + File.separator + CARD_IMAGES_NAMES[i]);
        }

        startNewGame();
    }

    /**
     * Initializes the Game
     */
    public static void startNewGame() {
        selectedCard1 = null;
        selectedCard2 = null;
        matchedCardsCount = 0;
        winner = false;
        message = "";

        cards = new Card[CARDS_COORDINATES.length];
        int[] mixedUp = Utility.shuffleCards(cards.length);

        for (int i = 0; i < CARDS_COORDINATES.length; i++) {
            cards[i] = new Card(images[mixedUp[i]],
                CARDS_COORDINATES[i][0], CARDS_COORDINATES[i][1]);
        }

    }

    /**
     * Callback method called each time the user presses a key
     */
    public static void keyPressed(){
        if (processing.key == 'N' || processing.key == 'n'){
            startNewGame();
        }
    }

    /**
     * Callback method draws continuously this application window display
     */
    public static void draw() {
        processing.background(245, 255, 250);

        for (int i = 0; i < cards.length; i++) {
            cards[i].draw();
        }

        displayMessage(message);
    }

    /**
     * Displays a given message to the display window
     *
     * @param message to be displayed to the display window
     */
    public static void displayMessage(String message) {
        processing.fill(0);
        processing.textSize(20);
        processing.text(message, processing.width / 2, 50);
        processing.textSize(12);
    }

    /**
     * Checks whether the mouse is over a given Card
     *
     * @return true if the mouse is over the storage list, false otherwise
     */
    public static boolean isMouseOver(Card card) {
        float halfWidth = card.getWidth() / 2;
        float halfHeight = card.getHeight() / 2;
        float mouseXToCardX = Math.abs(processing.mouseX - card.getX());
        float mouseYToCardY = Math.abs(processing.mouseY - card.getY());

        if ((mouseXToCardX <= halfWidth) && (mouseYToCardY <= halfHeight)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Callback method called each time the user presses the mouse
     */
    public static void mousePressed() {

        if (matchedCardsCount != images.length) {
            message = "";
            if (selectedCard1 != null && selectedCard2 != null) {
                if (selectedCard1.isMatched() && selectedCard2.isMatched()) {
                    selectedCard1.deselect();
                    selectedCard2.deselect();
                }
                else {
                    selectedCard1.deselect();
                    selectedCard1.setVisible(false);
                    selectedCard2.deselect();
                    selectedCard2.setVisible(false);
                }
                selectedCard1 = null;
                selectedCard2 = null;
            }

            for (int i = 0; i < cards.length; i++) {
                if (cards[i].isMatched() || selectedCard1 == cards[i]) {
                    continue;
                }

                if (isMouseOver(cards[i])) {
                    if (selectedCard1 == null) {
                        selectedCard1 = cards[i];
                        selectedCard1.select();
                        selectedCard1.setVisible(true);
                        break;
                    }
                    else {
                        selectedCard2 = cards[i];
                        selectedCard2.select();
                        selectedCard2.setVisible(true);
                        break;
                    }
                }
            }

            if (selectedCard1 != null && selectedCard2 != null) {
                if (matchingCards(selectedCard1, selectedCard2)) {
                    message = MATCHED;
                    matchedCardsCount++;

                    if (matchedCardsCount == images.length) {
                        winner = true;
                        message = CONGRA_MSG;
                        return;
                    }
                }
                else {
                    message = NOT_MATCHED;
                }
            }

        }

    }

    /**
     * Checks whether two cards match or not
     *
     * @param card1 reference to the first card
     * @param card2 reference to the second card
     * @return true if card1 and card2 image references are the same, false otherwise
     */
    public static boolean matchingCards(Card card1, Card card2) {
        if (card1.getImage().equals(card2.getImage())) {
            card1.setMatched(true);
            card2.setMatched(true);
            return true;
        }
        else {
            return false;
        }
    }
}
