import processing.core.PImage;

import java.util.Random;

public class Animal implements GUIListener{
    private static Random randGen = new Random(); // Generator of random numbers
    protected static CarrotPatch processing; // PApplet object representing the display window
    protected PImage image; // image of this
    protected String label; // represents the name/identifier of this animal
    private int x; // x-position of this animal in the display window
    private int y; // y-position of this animal in the display window
    private boolean isDragging; // indicates whether the animal is being dragged or not
    private int oldMouseX; // old mouse x-position
    private int oldMouseY; // old mouse-y position

    /**
     * Creates a new Animal object positioned at a given position of the display window
     *
     * @param processing PApplet object that represents the display window
     * @param x x-position of the image of this animal in the display window
     * @param y y-position of the image of this animal in the display window
     * @param imageFileName filename of the animal image
     */
    public Animal(int x, int y, String imageFileName) {
        // Set Animal drawing parameters
        this.x = x; // sets the x-position of this animal
        this.y = y; // sets the y-position of this animal
        this.image = processing.loadImage(imageFileName);
        isDragging = false; // initially the animal is not dragging
    }

    /**
     * Creates a new Animal object positioned at a random position of the display window
     *
     * @param processing PApplet object that represents the display window
     * @param imageFileName filename of the animal image
     */
    public Animal(String imageFileName) {
        this(randGen.nextInt(processing.width), Math.max(randGen.nextInt(processing.height), 200),
                imageFileName);
    }


    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {

    }

    @Override
    public void mouseReleased() {

    }

    @Override
    public boolean isMouseOver() {
        return false;
    }
}
