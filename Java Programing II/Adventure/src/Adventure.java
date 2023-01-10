///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Adventure program.
// Course:          CS 200, Fall, 2020
//
// Author:          Weiqian Zhi
// Email:           wzhi3@wisc.edu
// Lecturer's Name: Marc Renault
//
////////////////////////////////////////////////////////////////////////////////

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains a text based adventure game.
 *
 * @author Jim Williams
 * @author Weiqian Zhi
 */
public class Adventure {

    /**
     * This writes a map to a file showing the Config.NAME field
     * at each set of coordinates and indicates which location the player is at.
     * <p>
     * This is intended for the programmer to have a visualization of the map while they are
     * debugging the map and program. It is not intended for the player as it would give away
     * the experience of exploring the map. Therefore, there is flexibility with
     * how you display the information. When you submit, include an example output in the
     * comments at the bottom of Config.java for grading.
     *
     * @param map      The map, indexes are row, column, fields/attributes.
     * @param player   The players current location.
     * @param fileName The name of the file the map is written to.
     */
    public static void writeMap(String[][][] map, int[] player, String fileName) {

    }

    /**
     * This determines if there are integer coordinates preceding a / at the beginning
     * of a line. If so, the coordinates are returned as an array of int, with the
     * first integer at index ROW and the second integer at index COLUMN. If there is not
     * a set of coordinates, then null is returned.
     * <p>
     * Examples of coordinate strings and their results:
     * "" should return null
     * "0,4" should return an array with {0,4}
     * "8,6" should return an array with {8,6}
     * "public class..." should return null
     * "/*" should return null
     * <p>
     * The indexes into the fields array are Config.NAME, Config.ENTER_TEXT, Config.ENTER_RESULT,
     * and Config.NEARBY_TEXT.
     *
     * @param coordinateString Contains the portion of the line read from the .advcfg file before
     *                         the first /.
     * @return an int array containing the coordinates separated by a comma, or
     * null.
     */
    public static int[] parseCoordinates(String coordinateString) {
        int[] parseCoord = new int[2];
        String[] strArray = null;
        int firstIndex = -1;
        int secondIndex = -1;

        if (coordinateString.contains(",")) {
            strArray = coordinateString.split(",");
            try {
                firstIndex = Integer.parseInt(strArray[0]);
                secondIndex = Integer.parseInt(strArray[1]);

                parseCoord[0] = firstIndex;
                parseCoord[1] = secondIndex;
                return parseCoord;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * This returns an array of field/attribute information as an array from a line read
     * from the .advcfg file after the first /.
     * <p>
     * The fields are returned as an array of String with index 0 containing the value
     * in the first field following the coordinates on the input line.
     * <p>
     * Examples:
     * "size/This is the size of the map//" should return
     * {"size" , "This is the size of the amp" , "" , ""}
     * " //  /" should return {"","","",""}
     * "*" should return {"*"}
     * <p>
     * Hint: Use the String class split method to get an array and then
     * trim each of the fields.
     * fieldInformation.split("/",-1);  //the -1 means keep blank fields
     *
     * @param fieldInformation Contains field information read from a line in the Config.java file
     *                         after coordinates and the first /.
     * @return The fields in the form of a trimmed array of Strings.
     */
    public static String[] parseFields(String fieldInformation) {
        String[] parseField = null;
        int index = 0;

        for (String temp1 : fieldInformation.split("/", -1)) {
            index++;
        }
        parseField = new String[index];

        index = 0;
        for (String temp2 : fieldInformation.split("/", -1)) {
            parseField[index] = temp2.trim();
            index++;
        }

        return parseField;
    }

    /**
     * This reads the map configuration information from the specified file.
     * The map is returned and the playerStartingLocation array is updated
     * with the starting row and column of the player.
     * <p>
     * Algorithm:
     * Open the file
     * Read each line
     * if the line contains / then call parseCoordinates with the part of the line
     * before the /,
     * Otherwise go to the next input line.
     * If parseCoordinates returns null, then go to the next input line.
     * Otherwise, call parseFields with the part of the line after the / to obtain the
     * fields array
     * If these are the first coordinates read from the file, then they are the
     * number of rows and columns in the map.  Allocate the map array
     * with this number of rows and columns, leaving the 3rd dimension
     * (fields/attributes) null for the moment.
     * If these are Not the first coordinates read from the file, then they are
     * location coordinates and put the fields array at these coordinates
     * in the map. If the Config.NAME field contains Config.NAME_START then
     * update the playerStartingLocation to be this location.
     * return the map.
     *
     * @param fileName               The configuration file to read, typically ending with .advcfg.
     * @param playerStartingLocation Updated within this method to contain the starting row
     *                               and column of the player.
     * @return The map with the 3 dimensions being, row, column and then fields/attributes at that
     * row and column in the map.
     * @throws FileNotFoundException On error opening the file.
     */
    public static String[][][] loadAdventure(String fileName, int[] playerStartingLocation)
            throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);

        String[][][] map = null;
        boolean sizeOfMap = true;

        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            int firstFieldSeparator = line.indexOf("/");
            if (firstFieldSeparator < 0) {
                continue;
            }

            int[] coords = parseCoordinates(line.substring(0, firstFieldSeparator));
            if (coords == null) {
                continue;
            }

            String[] fields = parseFields(line.substring(firstFieldSeparator + 1));

            if (sizeOfMap) {
                map = new String[coords[Config.ROW]][coords[Config.COLUMN]][];
                sizeOfMap = false;
            } else {
                if (fields[Config.NAME].equalsIgnoreCase(Config.NAME_START)) {
                    playerStartingLocation[Config.ROW] = coords[Config.ROW];
                    playerStartingLocation[Config.COLUMN] = coords[Config.COLUMN];
                }
                map[coords[Config.ROW]][coords[Config.COLUMN]] = fields;
            }
        }
        return map;
    }

    /**
     * Given the map, the currentLocation of the player, and the direction
     * of movement, this returns a new array with the new location.
     * <p>
     * Note that the map wraps, such that moving to the right passed the maximum column result
     * in moving to the 0th column, similarly, for rows.
     *
     * @param map             The map indexes are row, column, fields/attributes.
     * @param currentLocation The current row and column of the player, must not change.
     * @param direction       The direction of movement Config.UP, Config.DOWN, Config.LEFT,
     *                        Config.RIGHT
     * @return The new row and column given the direction of movement.
     */
    public static int[] determineLocation(String[][][] map, int[] currentLocation, String direction) {
        int[] newLocation = new int[2];
        int Max_Row = map.length - 1;
        int Max_Col = map[0].length - 1;
        int row = currentLocation[Config.ROW];
        int column = currentLocation[Config.COLUMN];

        switch (direction) {
            case (Config.MOVE_UP):
                row--;
                if (row < 0) {
                    row = Max_Row;
                }
                newLocation[0] = row;
                newLocation[1] = column;
                break;

            case (Config.MOVE_DOWN):
                row++;
                if (row > Max_Row) {
                    row = 0;
                }
                newLocation[0] = row;
                newLocation[1] = column;
                break;

            case (Config.MOVE_RIGHT):
                column++;
                if (column > Max_Col) {
                    column = 0;
                }
                newLocation[0] = row;
                newLocation[1] = column;
                break;

            case (Config.MOVE_LEFT):
                column--;
                if (column < 0) {
                    column = Max_Col;
                }
                newLocation[0] = row;
                newLocation[1] = column;
                break;

            case (Config.initialPoint):
                newLocation[0] = 0;
                newLocation[1] = 0;
                break;
        }
        return newLocation;
    }

    /**
     * This randomly picks a location in the map that has null fields/attributes
     * and changes the row and column of the location parameter to be that new location.
     * The map is assumed to be rectangular and valid locations are those locations
     * that have null for the field/attributes values in the map.
     *
     * @param rand     A random number generator
     * @param map      The map, that we can assume is rectangular.
     * @param location Changed to be the coordinates of the new location.
     */
    public static void pickRandowsamLocation(String[][][] map, int[] location, Random rand) {
        ArrayList<int[]> availableLocations = new ArrayList<>();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == null) {
                    availableLocations.add(new int[]{row, column});
                }
            }
        }
        if (availableLocations.size() > 0) {
            //randomly pick an available location
            int[] newLocation = availableLocations.get(rand.nextInt(availableLocations.size()));
            //update location
            location[Config.ROW] = newLocation[Config.ROW];
            location[Config.COLUMN] = newLocation[Config.COLUMN];
        } else {
            System.out.println("Error, no new random location to move to found in the map.");
        }
    }

    /**
     * Check each of the 4 direct neighbors (up, down, left, right) and show their sounds
     * or smells. The neighbors are checked in a random order so that the order they are
     * shown doesn't provide a clue to the player.
     *
     * @param map      The map indexes are row, column, fields/attributes.
     * @param location The player's location.
     * @param rand     A random number generator.
     */
    public static void senseNearby(String[][][] map, int[] location, Random rand) {
        ArrayList<String> neighbors = new ArrayList<>(
                java.util.Arrays.asList(Config.MOVE_UP, Config.MOVE_DOWN, Config.MOVE_LEFT,
                        Config.MOVE_RIGHT));
        while (neighbors.size() > 0) {
            //randomly choose a neighbor to check and remove them from the list
            int index = rand.nextInt(neighbors.size());
            String direction = neighbors.get(index);
            neighbors.remove(index);

            //calculate a new location based on the current location and direction
            int[] tempLocation = determineLocation(map, location, direction);

            //obtain the fields from the current location in the map.
            String[] fields = map[tempLocation[Config.ROW]][tempLocation[Config.COLUMN]];
            if (fields != null && !fields[Config.NEARBY_TEXT].trim().isEmpty()) {
                System.out.println(fields[Config.NEARBY_TEXT]);
            }
        }
    }

    /**
     * The player enters the location in the map. Describe that location to the player.
     * <p>
     * Algorithm:
     * Get the fields for the specific player location.
     * If the fields are not null and the Config.ENTER_RESULT field is
     * Config.RESULT_RANDOM_MOVE then print out Config.ENTER_TEXT (describes what is
     * happening) to the player, and then call pickRandomLocation to get a new location
     * Get the fields for the new location.
     * If the fields are null, then print "open" and return false.
     * Otherwise, if Config.ENTER_RESULT field is Config.RESULT_WIN or Config.RESULT_LOSE
     * then print out Config.ENTER_TEXT and return true.
     * Otherwise print out
     * Config.ENTER_TEXT and return false.
     *
     * @param map      The map indexes are row, column, fields/attributes.
     * @param location The players location as row and column in the map
     * @param rand     A random number generator
     * @return true on game finished, false otherwise.
     */
    public static boolean enter(String[][][] map, int[] location, Random rand) {
        String[] fields = map[location[Config.ROW]][location[Config.COLUMN]];

        if (fields != null
                && fields[Config.ENTER_RESULT].equals(Config.RESULT_RANDOM_MOVE)) {

            System.out.println(fields[Config.ENTER_TEXT]);
            pickRandowsamLocation(map, location, rand);
            fields = map[location[Config.ROW]][location[Config.COLUMN]];
        }

        if (fields == null) {
            System.out.println("open");
            return false;
        } else if (fields[Config.ENTER_RESULT].equals(Config.RESULT_WIN)
                || fields[Config.ENTER_RESULT].equals(Config.RESULT_LOSE)) {

            System.out.println(fields[Config.ENTER_TEXT]);
            return true;
        } else if (fields[Config.ENTER_RESULT].equals(Config.RESULT_PLOT)) {
            System.out.print(fields[Config.ENTER_TEXT] + ", ");
            System.out.print(fields[Config.ENTER_RESULT] + ". ");
            System.out.println("He says: " + fields[Config.NEARBY_TEXT]);
            return false;
        } else {
            System.out.println(fields[Config.ENTER_TEXT]);
            return false;
        }

    }


    /**
     * This method plays a sound file.
     * <p>
     * Many sound files are available on the web or you can make your
     * own in the .wav format. Some are free for personal use.
     * http://www.animal-sounds.org/farm-animal-sounds.html
     * http://soundbible.com/
     * https://www.freesoundeffects.com/free-sounds/wind-sounds-10041/
     * <p>
     * The call:
     * playSound("sounds/wumpus.wav");
     * means there is a sounds folder within the IDE project folder.
     * Within that folder there is a sound file named wumpus.wav.
     * Play the "sounds/wumpus.wav" sound file.
     * <p>
     * Oracle's Java Tutorials can be helpful in learning much more about a topic:
     * https://docs.oracle.com/javase/tutorial/sound/index.html
     *
     * @param wavFileName Name of the .wav file to play.
     */
    public static void playSound(String wavFileName) {
        File file = new File(wavFileName);
        if (file.exists()) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This contains the starting configuration and the game loop.
     * Within the game loop the player is prompted and the results
     * are shown, until the game is finished.
     *
     * @param args Used for testing and debugging information.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String configurationFile = "universe.advcfg";
        String mapFilename = "map.txt";
        boolean seedInput = false;
        long seed = 0;
        boolean debug = false;

        //Developer & tester command-line options so not shown to a player.
        //You may add to these options for your own use but make sure these all work the same
        //as we will be using them when testing.
        //
        //Usage: java Adventure [-s seed] [-c configuration_filename] [-m map_filename] [-d]
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equalsIgnoreCase("-s")) {
                seed = Long.parseLong(args[i + 1]);
                seedInput = true;
                ++i; //additional increment since we are reading the seed
            } else if (args[i].equalsIgnoreCase("-c")) {
                configurationFile = args[i + 1];
                ++i; //additional increment since we are reading the filename
            } else if (args[i].equalsIgnoreCase("-m")) {
                mapFilename = args[i + 1];
                ++i; //additional increment since we are reading the filename
            } else if (args[i].equalsIgnoreCase("-d")) {
                debug = true;
            }
        }

        //if a seed is passed on the command line, use it for the random number generator.
        Random rand;
        if (seedInput) {
            rand = new Random(seed);
        } else {
            rand = new Random();
        }

        //load the map and the initial player location in the map
        int[] playerLocation = new int[]{0, 0};
        String[][][] map = null;
        try {
            map = loadAdventure(configurationFile, playerLocation);
            if (map == null) {
                return;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, unable to load file: " + configurationFile);
            return;
        }

        if (debug) {
            System.out.println("DEBUG: Drawing map in " + mapFilename);
            writeMap(map, playerLocation, mapFilename);
        }

        //start to play sound.
        playSound("sound/cartoon-birds-2.wav");

        //show current location and then loop until game is complete.
        boolean finished = enter(map, playerLocation, rand);

        while (!finished) {

            //prompt for player movement
            System.out.print("> ");
            String direction = input.nextLine().trim().toLowerCase();
            switch (direction) {
                case Config.MOVE_UP:
                case Config.MOVE_DOWN:
                case Config.MOVE_LEFT:
                case Config.MOVE_RIGHT:
                    playerLocation = determineLocation(map, playerLocation, direction);
                    finished = enter(map, playerLocation, rand);
                    break;
                case Config.NEARBY:
                    senseNearby(map, playerLocation, rand);
                    break;
                case Config.QUIT:
                    finished = true;
                    break;
                default:
                    System.out.println(Config.MOVE_UP + ")up " + Config.MOVE_DOWN + ")down "
                            + Config.MOVE_LEFT + ")left " + Config.MOVE_RIGHT + ")right "
                            + Config.NEARBY + ")nearby " + Config.QUIT + ")quit"
                            + Config.initialPoint + ")back to initial position");
                    break;
            }
            if (debug) {
                System.out.printf("DEBUG: player location %d,%d\n"
                        , playerLocation[Config.ROW], playerLocation[Config.COLUMN]);
                writeMap(map, playerLocation, mapFilename);
            }
        }
        System.out.println("Thanks for playing!");
    }
}
