import java.util.NoSuchElementException;

public class Demo {
    public static void main(String[] args) {
        MovieTree bst = new MovieTree();
        System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
        System.out.println(bst);
        bst.addMovie(new Movie(2018, 6.5, "Airplanes"));
        bst.addMovie(new Movie(1988, 9.5, "Best"));
        System.out.println("==============================================================");
        System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
        System.out.println(bst);
        bst.addMovie(new Movie(2018, 8.5, "Cats"));
        bst.addMovie(new Movie(2018, 6.0, "Yes"));
        bst.addMovie(new Movie(2017, 5.5, "Dogs"));
        bst.addMovie(new Movie(2018, 7.5, "Earth"));
        bst.addMovie(new Movie(2018, 6.0, "Flights"));
        bst.addMovie(new Movie(2015, 8.5, "Grand Parents"));
        System.out.println("==============================================================");
        System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
        System.out.println(bst);
        try {
            System.out.println("This catalog contains (2018, 7.5, Earth): " + bst.contains(2018, 7.5, "Earth"));
            System.out.println("This catalog contains (2016, 8.4, Flowers): " + bst.contains(2018, 7.5, "Flowers"));
            System.out.println();
            System.out.println("Best movie:  " + bst.getBestMovie());
            System.out.println();
            System.out.println("Lookup query: search for the movies of 2018 rated 6.5 and higher");
            System.out.println(bst.lookup(2018, 6.5));
            System.out.println();
            System.out.println("Lookup query: search for the movies of 2018 with rated 8.0 and higher");
            System.out.println(bst.lookup(2018, 8.0));
            System.out.println();
            System.out.println("Lookup query: search for the movies of 2015 with rated 9.0 and higher");
            System.out.println(bst.lookup(2015, 9.0));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
