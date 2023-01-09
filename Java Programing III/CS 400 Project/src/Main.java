// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InitializeDatabase.readFileIntoDatabase();
        Scanner scnr = new Scanner(System.in);
        GamesConsole console = new GamesConsole();
        int userCommand;
        String nameSearch;

        System.out.println("*** Welcome to PC Video Games Center ***");
        System.out.print("^ We gather many games in recent years from the PC platform."
                + "\n^ We will help you to find the games you are interested in and try to max your expectation!"
                + "\n>>> So let's get stared!\n");

        console.printMenu();

        do {

            try {// check if the command is valid or not
                userCommand = scnr.nextInt();
            } catch (InputMismatchException e) {
                scnr.nextLine();
                userCommand = -1;
            }

            if (userCommand < 0 || userCommand > 5) {
                System.out.println("> Please enter valid number!");
                continue;
            }

            switch (userCommand) {
                case (1):// List all games
                    System.out.println("> You choose: list all the game. (order: high rating to low rating)"
                            + "\n*** Game List ***");

                    if (!console.findAllGames()) {
                        System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                        break;
                    }

                    System.out.println("> Do you want to see detailed information about a game? (You can look up a "
                            + "game that is now displayed in the Game List)");
                    console.printChoice();

                    // Ensure that valid user input is recognized and valid
                    try {
                        userCommand = scnr.nextInt();
                        do {
                            if (userCommand == 1 || userCommand == 0) {
                                break;
                            }
                            scnr.nextLine();
                            System.out.println("> Please enter valid number:");
                            userCommand = scnr.nextInt();
                        } while (userCommand != 1 && userCommand != 0);
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                    }

                    if (userCommand == 1) {
                        scnr.nextLine();
                        System.out.println("> Enter its game name: ");
                        nameSearch = scnr.nextLine();

                        while (userCommand != 0) {
                            boolean condition = console.findSpecifiedName(nameSearch.toUpperCase());
                            if (condition) {
                                break;
                            } // found it! return to main menu
                            else {
                                while (userCommand != 0) {
                                    System.out.println("1 - Trying to enter a game name again \n0 - Back to main menu");
                                    System.out.println("> Choose an option (also accept 01 refer to Try again, etc):");
                                    try {
                                        userCommand = scnr.nextInt();
                                    } catch (InputMismatchException e1) {
                                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                                        if (userCommand == 0 || userCommand == 1) {
                                            break;
                                        }
                                    }
                                    if (userCommand == 1) {
                                        break;
                                    }
                                }
                                if (userCommand == 0) {
                                    break;
                                }
                                System.out.println("> Enter its game name: ");
                                scnr.nextLine();
                                nameSearch = scnr.nextLine();
                            }
                        }
                    }

                    System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                    userCommand = -1;// let system continue running(b.c. =0 lead to loop break; line174)
                    break;

                case (2):
                    System.out.println("> You choose: list games with a given rating number " +
                            "(This will list all the games you have requested for the specified rating)" +
                            "\nPlease enter you rating (1 - 10) [also accept 01 refer to 1, etc] : ");

                    // Ensure that valid user input is recognized and valid
                    try {
                        userCommand = scnr.nextInt();
                        do {
                            if (userCommand >= 1 && userCommand <= 10) {
                                break;
                            }
                            scnr.nextLine();
                            System.out.println("> Please enter valid number:");
                            userCommand = scnr.nextInt();
                        } while (userCommand != 1 && userCommand != 10);
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 1, 10, scnr);
                    }

                    // go to the main menu
                    if (!console.findSpecifiedRating(userCommand)) {
                        System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                        break;
                    }

                    System.out.println("> Do you want to see detailed information about a game? (You can look up a "
                            + "game that is now displayed in the Game List, or you can try to search for a "
                            + "game that are not in this Game List currently)");
                    console.printChoice();

                    try {
                        userCommand = scnr.nextInt();
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                    }

                    if (userCommand == 1) {
                        scnr.nextLine();
                        System.out.println("> Enter its game name: ");
                        nameSearch = scnr.nextLine();

                        while (userCommand != 0) {
                            boolean condition = console.findSpecifiedName(nameSearch.toUpperCase());
                            if (condition) {
                                break;
                            } // found it! return to main menu
                            else {
                                while (userCommand != 0) {
                                    System.out.println("1 - Trying to enter a game name again \n0 - Back to main menu");
                                    System.out.println("> Choose an option (also accept 01 refer to Try again, etc) :");
                                    try {
                                        userCommand = scnr.nextInt();
                                    } catch (InputMismatchException e1) {
                                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                                        if (userCommand == 0 || userCommand == 1) {
                                            break;
                                        }
                                    }
                                    if (userCommand == 1) {
                                        break;
                                    }
                                }
                                if (userCommand == 0) {
                                    break;
                                }
                                System.out.println("> Enter its game name: ");
                                scnr.nextLine();
                                nameSearch = scnr.nextLine();
                            }
                        }
                    }

                    System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                    userCommand = -1;// let system continue running(b.c. =0 lead to loop break; line174)
                    break;

                case (3):
                    System.out.println("> You choose: list games with a given tag (This will list all the " +
                            "games you have requested for the specified tag)");
                    System.out.println("> Please choose one tag and enter its serial number (1 - 4) [also accept 01 refer to ACTION, etc] :");
                    console.printTags();

                    // Ensure that valid user input is recognized and valid
                    try {
                        userCommand = scnr.nextInt();
                        do {
                            if (userCommand >= 1 && userCommand <= 4) {
                                break;
                            }
                            scnr.nextLine();
                            System.out.println("> Please enter valid number:");
                            userCommand = scnr.nextInt();
                        } while (userCommand != 1 && userCommand != 4);
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 1, 4, scnr);
                    }

                    // go to the main menu
                    if (!console.findSpecifiedTag(userCommand)) {
                        System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                        break;
                    }

                    System.out.println("> Do you want to see detailed information about a game? (You can look up a "
                            + "game that is now displayed in the Game List, or you can try to search for a "
                            + "game that are not in this Game List currently)");
                    console.printChoice();
                    try {
                        userCommand = scnr.nextInt();
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                    }

                    if (userCommand == 1) {
                        scnr.nextLine();
                        System.out.println("> Enter its game name: ");
                        nameSearch = scnr.nextLine();

                        while (userCommand != 0) {
                            boolean condition = console.findSpecifiedName(nameSearch.toUpperCase());
                            if (condition) {
                                break;
                            } // found it! return to main menu
                            else {
                                while (userCommand != 0) {
                                    System.out.println("1 - Trying to enter a game name again \n0 - Back to main menu");
                                    System.out.println("> Choose an option (also accept 01 refer to Try again, etc) :");
                                    try {
                                        userCommand = scnr.nextInt();
                                    } catch (InputMismatchException e1) {
                                        userCommand = repeatCheckInputValidity(userCommand, 0, 1, scnr);
                                        if (userCommand == 0 || userCommand == 1) {
                                            break;
                                        }
                                    }
                                    if (userCommand == 1) {
                                        break;
                                    }
                                }
                                if (userCommand == 0) {
                                    break;
                                }
                                System.out.println("> Enter its game name: ");
                                scnr.nextLine();
                                nameSearch = scnr.nextLine();
                            }
                        }
                    }

                    System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                    userCommand = -1;// let system continue running(b.c. =0 lead to loop break; line174)
                    break;

                case (4):
                    System.out.println("> You choose: Multi-Criteria Search (This will list all the games base " +
                            "on your search criteria)"
                            + "\n> Please enter you rating (1 - 10) [also accept 01 refer to 1, etc]: ");

                    // Ensure that valid user input is recognized and valid
                    try {
                        userCommand = scnr.nextInt();
                        do {
                            if (userCommand >= 1 && userCommand <= 10) {
                                break;
                            }
                            scnr.nextLine();
                            System.out.println("> Please enter valid number:");
                            userCommand = scnr.nextInt();
                        } while (userCommand != 1 && userCommand != 10);
                    } catch (InputMismatchException e) {
                        userCommand = repeatCheckInputValidity(userCommand, 1, 10, scnr);
                    }

                    System.out.print("Please choose a tag (ACTION, ADVENTURE, SIMULATION, STRATEGY) "
                            + "\n> type it in words: ");
                    scnr.nextLine();
                    System.out.println();
                    nameSearch = scnr.nextLine().toUpperCase();
                    do {
                        if (nameSearch.equals("ACTION") || nameSearch.equals("ADVENTURE")
                                || nameSearch.equals("SIMULATION") || nameSearch.equals("STRATEGY")) {
                            break;
                        }
                        System.out.println("> Please enter valid number:");
                        nameSearch = scnr.nextLine().toUpperCase();
                    } while (true);

                    // go to the main menu
                    if (!console.multiCriteriaSearch(userCommand, nameSearch.toUpperCase())) {
                        System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                        break;
                    }

                    System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                    break;

                case (5):
                    System.out.println("> You choose: Search for game (This will list the game you request for " +
                            "the specified name)" + "\n> type it in words:");

                    scnr.nextLine();
                    nameSearch = scnr.nextLine();

                    try {
                        console.findSpecifiedName(nameSearch.toUpperCase());
                    } catch (NoSuchElementException e) {
                        System.out.println("> The Game List may not have the game or Please be ensure you enter " +
                                "a valid game name");
                        System.out.println("\n> Let's go back to the main menu, see what else I can do for you!");
                    }
                    break;
            }

            if (userCommand == 0) {
                break;
            } // end the program

            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            console.printMenu();
        } while (userCommand != 0);

        System.out.println("> This program will shut down after 3 millionth seconds." + "\n... 3" + "\n.. 2"
                + "\n. 1" + "\nSuccessfully Terminated" + "\n" + "\n> Thanks for using this program. " +
                "Hope to see you again!");
    }

    public static int repeatCheckInputValidity(int userCommand, int min, int max, Scanner scnr) {
        while (userCommand != min || userCommand != max) {
            System.out.println("> Please enter valid number:");
            scnr.nextLine();
            try {
                userCommand = scnr.nextInt();
                if (userCommand >= min && userCommand <= max) {
                    break;
                }
            } catch (InputMismatchException e1) {
                continue;
            }
        }
        return userCommand;
    }


}
