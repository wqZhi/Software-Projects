///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           A text based adventure program.
// Course:          CS 200, Fall, 2020
//
// Author:          Weiqian Zhi
// Email:           wzhi3@wisc.edu
// Lecturer's Name: Marc Renault
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This file contains constants for the text Adventure project. Also at the bottom of this file
 * are READ ME Notes for the Grader.
 *
 * @author Jim Williams
 * @author Weiqian Zhi
 */
public class Config {

    /**
     * Indexes into coordinate arrays specifying a location in the map.
     */
    public static final int ROW = 0;
    public static final int COLUMN = 1;

    /**
     * Indexes into the fields/attributes array for each map location (3rd dimension of
     * the map array)
     */
    public static final int NAME = 0;
    public static final int ENTER_TEXT = 1;
    public static final int ENTER_RESULT = 2;
    public static final int NEARBY_TEXT = 3;
    //add additional indexes here for additional field information added to each configuration
    //line.

    /**
     * Values within the location fields/attributes that are used for game logic other than
     * simply printing out.
     */
    //In name field, designates the starting location of the player
    public static final String NAME_START = "start";

    //The result of the player entering the field.
    public static final String RESULT_RANDOM_MOVE = "randomMove";
    public static final String RESULT_WIN = "win";
    public static final String RESULT_LOSE = "lose";
    public static final String RESULT_PLOT = "NPC";

    /**
     * Values used by the player to navigate through the map.
     */
    public static final String MOVE_UP = "w";
    public static final String MOVE_DOWN = "s";
    public static final String MOVE_LEFT = "a";
    public static final String MOVE_RIGHT = "d";
    public static final String NEARBY = "n";
    public static final String QUIT = "q";
    public static final String initialPoint = "return";
    //add additional game control characters here, throwing, shooting, etc.

}

/*
Your READ ME notes to the Grader
Describe your enhancements to the program, map, etc. here.  You may include a link to a
YouTube video of a maximum of 2 minutes demonstrating your program.

0) Overview of your changes or a YouTube video link (max 2 minutes)
        In this program, I completed four methods includes parseCoordinates, parseFields,
        determineLocation, enter. I also add a sound file within the project.

1) Configuration Example: Contents of your own configuration (.advcfg) file
    Coordinates/Name/Enter Text/Enter Result/Nearby Text/
    10,10/size/This is the size of the map///
    5,5/Thanos/You've been killed by the Thanos./lose/die without a full body/
    4,4/Thanos/You've been killed by the Thanos./lose/die without a full body/
    4,3/Thanos/You've been killed by the Thanos./lose/die without a full body/
    3,4/wormhole/You are in wormhole and dropped you somewhere.../randomMove/you feel helpless/
    5,6/Infinity Gems/You've found the Infinity Gems/win/
    0,0/start/Welcome to the Universe! Find the Infinity Gems without getting killed by the Thanos. May you return safely!///
    6,0/wormhole/You are in wormhole and dropped you somewhere.../randomMove/you feel helpless/
    7,1/wormhole/You are in wormhole and dropped you somewhere.../randomMove/you feel helpless/
    4,2/wormhole/You are in wormhole and dropped you somewhere.../randomMove/you feel helpless/
    4,4/Anonymous/You meet a person/NPC/May the Force be with you. Keep going./

2) Configuration Description: Describe unique elements in your configuration file
    I add one more String in the result of the player entering the field, which is
            String RESULT_PLOT = "NPC".
    NPC will show up in some position, this person will say something to encourage to move on.

3) writeMap Output: Example output of the writeMap method for your configuration file.


4) Choose at least 1 of the following ways you extended the game and describe them:
  a) Tools: added your own tools/weapons and use of them in the game
  b) Audio: playing of audio files when sensing the neighbors, rather than simply text
  c) Test Cases: adding test cases to TestAdventure that thoroughly test the methods you wrote.
  d) Your Own: describe any other changes or additions we should consider for credit.

  I choose b.
  Add sound file: I create a sound folder within my project. Then, I download
                  a sound named Cartoon Birds 2 Sound from
                  https://docs.oracle.com/javase/tutorial/sound/index.html as .wav
                  and put this sound file in sound folder.









 */
