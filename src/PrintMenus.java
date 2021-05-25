public class PrintMenus {
//all the extended menus are presented below in a neat manner so main app is not over-cluttered
    public void printMainMenu()
    {
        System.out.println();
        System.out.println("***GOMORRA PROJECTS PRESENTS***");
        System.out.println("********JAVA WORDS GAME********");
        System.out.println();
        System.out.println("Enter one of the following commands:");
        System.out.println("quit - quits the game,");
        System.out.println("play - play the game,");
        System.out.println("instructions - displays how you should play this game;");
        System.out.println();
    }

    public void printSubMenu()
    {
        System.out.println();
        System.out.println("Enter one of the following commands:");
        System.out.println("quit - quits the game,");
        System.out.println("play - play the game,");
        System.out.println("instructions - displays how you should play this game;");
        System.out.println();
    }

    public void printInstructions()
    {
        System.out.println("1. The game is designed for two players only.");
        System.out.println("2. Each player has a N number of lives as defined by int playersLives; this can be changed.");
        System.out.println("3. Player that starts given round is provided with a single letter which will be the first one in the word provided.");
        System.out.println("4. Following player will then create new word with the usage of the last two characters from previous player's word.");
        System.out.println("5. Should player not be able to provide a valid word (or any word), he/she can simply press \"-\"; this will end up round.");
        System.out.println("6. First word provided is worth only 1 point each; however, combination of any neighbourhooding even and odd word where both are longer than 7 letters is worth 5 points; otherwise both are worth 1 point.");
        System.out.println("7. Game lasts as long as either one player has more than zero lives");
        System.out.println("8. If a player looses life to incorrect word/lack of word; the other player starts the following round. Same applies to games - if player looses game and players decide to play another, it is the winner that will start.");
        System.out.println("9. Game lasts as long as either one player has more than zero lives.");
        System.out.println("10. Players have an option to exit game earlier by entering \"yes\" into command line.");
    }
}
