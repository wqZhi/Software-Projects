// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

/*** JUnit imports ***/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*** JUnit imports end ***/

public class UnitTests {
    protected static GamesConsole console = null;

    //BeforeEach annotation makes a method invoked automatically before each test
    @BeforeEach
    public void initializeDatabase() {
        InitializeDatabase.readFileIntoDatabase();
        console = new GamesConsole();
    }

    //Test annotation allows JUnit to recognize its following method as a test method
    @Test
    public void testGameExist() {
        //Test three hashtable can store the value and get the value correctly.
        String expectName = "CALL OF DUTY: ADVANCED WARFARE";
        String expectTag = "ACTION";
        int expectRating = 8;
        double expectPrice = 49.99;
        String expectDescription = "Call of Duty: Advanced Warfare envisions the powerful battlegrounds of "
                + "the future, where both technology and tactic have evolved to usher in a new era of combat for the "
                + "franchise.";

        //rating table
        Game ratingHashtable = InitializeDatabase.name.get("CALL OF DUTY: ADVANCED WARFARE");
        assertEquals(expectName, ratingHashtable.getName());
        assertEquals(expectTag, ratingHashtable.getTag());
        assertEquals(expectRating, ratingHashtable.getRating());
        assertEquals(expectPrice, ratingHashtable.getPrice());
        assertEquals(expectDescription, ratingHashtable.getDescription());

        //name table
        Game nameHashtable = InitializeDatabase.name.get("CALL OF DUTY: ADVANCED WARFARE");
        assertEquals(expectName, nameHashtable.getName());
        assertEquals(expectTag, nameHashtable.getTag());
        assertEquals(expectRating, nameHashtable.getRating());
        assertEquals(expectPrice, nameHashtable.getPrice());
        assertEquals(expectDescription, nameHashtable.getDescription());

        //tag table
        Game tagHashtable = InitializeDatabase.name.get("CALL OF DUTY: ADVANCED WARFARE");
        assertEquals(expectName, tagHashtable.getName());
        assertEquals(expectTag, tagHashtable.getTag());
        assertEquals(expectRating, tagHashtable.getRating());
        assertEquals(expectPrice, tagHashtable.getPrice());
        assertEquals(expectDescription, tagHashtable.getDescription());

        InitializeDatabase.clean();
    }

    @Test
    public void testSorting() {
        //Test the sorting is working correct (rating from high to low)
        int tag = 1; //1 - ACTION
        ArrayList<Bucket> bucketList = console.findSpecifiedTagHelper(tag);
        ArrayList<Game> temp = console.sort(bucketList);

        int[] resultRatingSort = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            resultRatingSort[i] = temp.get(i).getRating();
        }

        String expectRatingSort = "[10, 9, 8, 6, 4, 2]";
        assertEquals(Arrays.toString(resultRatingSort), expectRatingSort);

        InitializeDatabase.clean();
    }

    @Test
    public void testFindSpecifiedRatingGames() {
        //Test this game list program can find the games with a given rating correctly
        ArrayList<Bucket> bucketList = console.findSpecifiedRatingHelper(5);
        int[] gameList = new int[bucketList.size()];
        for (int i = 0; i < bucketList.size(); i++) {
            gameList[i] = (Integer) (bucketList.get(i).key);
        }

        int resultNumberGames = 0;
        for (int i = 0; i < gameList.length; i++) {
            if (gameList[i] == 5) {
                resultNumberGames++;
            }
        }

        int expectNumberGames = 1;
        assertEquals(resultNumberGames, expectNumberGames);

        InitializeDatabase.clean();
    }

    @Test
    public void testFindSpecifiedName() {
        //Test this game list program can find the game with a given name correctly
        Game resultGame = console.findSpecifiedNameHelper("AGE OF EMPIRES II: DEFINITIVE EDITION");

        String expectName = "AGE OF EMPIRES II: DEFINITIVE EDITION";
        String expectTag = "STRATEGY";
        int expectRating = 10;
        double expectPrice = 19.99;
        String expectDescription = "Age of Empires II: Definitive Edition celebrates the 20th anniversary of one of " +
                "the most popular strategy games ever with stunning graphic enhancements, a new and fully remastered soundtrack, and brand-new content, The Last Khans.";

        assertEquals(expectName, resultGame.getName());
        assertEquals(expectTag, resultGame.getTag());
        assertEquals(expectRating, resultGame.getRating());
        assertEquals(expectPrice, resultGame.getPrice());
        assertEquals(expectDescription, resultGame.getDescription());

        InitializeDatabase.clean();
    }

    @Test
    public void testMultiCriteriaSearch() {
        //Test the system's multi-criteria search works correctly.
        ArrayList<Game> gameMatch = console.multiCriteriaSearchHelper(9, "ACTION");

        int expectedMatchedGame = 1;
        String expectName = "DEAD BY DAYLIGHT";
        String expectTag = "ACTION";
        int expectRating = 9;
        double expectPrice = 19.99;
        String expectDescription = "Dead by Daylight is an action-survival multiplayer game in which a killer hunts"
                + " for survivors in a deadly game of cat and mouse.";

        assertEquals(gameMatch.size(), expectedMatchedGame);

        Game resultGame = gameMatch.get(0);

        assertEquals(expectName, resultGame.getName());
        assertEquals(expectTag, resultGame.getTag());
        assertEquals(expectRating, resultGame.getRating());
        assertEquals(expectPrice, resultGame.getPrice());
        assertEquals(expectDescription, resultGame.getDescription());

        InitializeDatabase.clean();
    }
}
