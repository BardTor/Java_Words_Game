import java.util.*;
public class JavaWordsGame {
//---------------Comments are not applied to repeating sections----------------
    public static void main (String [] args) {

        //instances of instantiable classes - initialization and creation - are listed below
        PrintMenus printMenus = new PrintMenus();
        GameLogic gameLogic = new GameLogic();
        LimitedVocabulary limitedVocabulary = new LimitedVocabulary();
        GameArrays gameArrays = new GameArrays();
        Scanner scannerOne = new Scanner(System.in);
        Scanner scannerTwo = new Scanner(System.in);

        printMenus.printMainMenu();
        Scanner scan = new Scanner(System.in);
        String command = scan.next();

        //Below int - playersLives - determines number of lives
        int playersLives = 3;
        gameLogic.setBothPlayersLives(playersLives);


        String stringPlayerOne = "abc";
        String stringPlayerTwo = "xyz";


        while (true) {//unless player enters quit, input can either be instructions or play, or else player will be urged to re-enter command

            if (command.equals("quit"))//break the while loop; program terminates
            {
                break;
            }
            else if (command.equals("instructions"))//instructions displayed on demand only
            {
                printMenus.printInstructions();
            }

            else if (command.equals("play")) {
                while (gameLogic.getEnoughLives()) {//if live of any player drops to zero, this while loop breaks
                    System.out.println("Lives of player one: " + gameLogic.getLivesOfPlayerOne());
                    System.out.println("Lives of player two: " + gameLogic.getLivesOfPlayerTwo());
                    boolean playerOneStarts = gameLogic.doesPlayerOneStart();//boolean determines whether player one starts or not

                    if (playerOneStarts) {//if false, player two starts; flag will be determined by who lost the last round/lives
                        System.out.println("Player one starts this round!");
                        while (gameLogic.getEnoughLives()) {//if live of any player drops to zero, this while loop breaks
//--------------------------------------------------------------------------------
                            if (gameLogic.getPlayerOneWordCounter() == 0) {//this check makes sure that char "if" is followed
                                gameLogic.setRandomAlphabetIndex();//random determines number between 0 and 25
                                gameLogic.setRandomAlphabetChar();//character from alphabet String is decided on the basis of above number
                                System.out.println("Player one must enter word starting with \"" + gameLogic.getRandomAlphabetChar() + "\" character.");//information on what input is requested
                                stringPlayerOne = "" + scannerOne.next().toLowerCase();

                                if (stringPlayerOne.length() == 1) {//if players resignes from even first word, this check will keep program running
                                    stringPlayerOne = gameLogic.getRandomAlphabetChar() + stringPlayerOne;
                                }

                                System.out.println(stringPlayerOne);
                                gameLogic.setPlayerOneWord(stringPlayerOne);
                                gameLogic.setPlayerOnePrefix(stringPlayerOne);
                                gameLogic.setPlayerOneSuffix(stringPlayerOne);
                                gameLogic.validatePlayerOneWord();
                                limitedVocabulary.determineWordExists(stringPlayerOne);
                                gameLogic.updatePlayerOneWordCounter();

                            } else {
                                System.out.println("Player one word must start with \"" + gameLogic.getPlayerTwoSuffix() + "\" prefix.");
                                stringPlayerOne = scannerOne.next().toLowerCase();

                                if (stringPlayerOne.length() == 1) {
                                    stringPlayerOne = gameLogic.getPlayerTwoSuffix() + stringPlayerOne;
                                }

                                System.out.println(stringPlayerOne);
                                gameLogic.setPlayerOneWord(stringPlayerOne);
                                gameLogic.setPlayerOnePrefix(stringPlayerOne);
                                gameLogic.setPlayerOneSuffix(stringPlayerOne);
                                gameLogic.validatePlayerOneWord();
                                limitedVocabulary.determineWordExists(stringPlayerOne);//checking word against limited dictionary
                                gameLogic.updatePlayerOneWordCounter();//word counter needed to feed locations within arrays
                            }

                            boolean firstCheckOne = gameLogic.isFirstValidationTermination();//first Boolean check, word too short, player entered "-"
                            boolean secondCheckOne = limitedVocabulary.isSecondValidationTermination();//second Boolean check against limited dictionary
                            //System.out.println(firstCheckOne + " " + secondCheckOne);

                            if (!firstCheckOne || !secondCheckOne) {//if either equals false, player loses life
                                System.out.println("Player one provided an incorrect word or was unable to provide one!");
                                gameLogic.setPlayerOneStarts(false);//because player one lost, flag is set to false
                                gameLogic.resetCounters();//used to reset word counters so arrays could be re-fed again
                                gameArrays.calculatePointsPlayerOne();
                                gameArrays.calculatePointsPlayerTwo();
                                gameArrays.resetWordsPlayerOneArray();//reset of any data inside array once round is over;
                                gameArrays.resetWordsPlayerTwoArray();
                                System.out.println("Points accumulated so far by player one: " + gameArrays.getPointsPlayerOne());
                                System.out.println("Points accumulated so far by player two: " + gameArrays.getPointsPlayerTwo());
                                gameLogic.reduceLivesOfPlayerOne();
                                System.out.println("Player one lives left: " + gameLogic.getLivesOfPlayerOne());
                                gameLogic.determineEnoughLives();
                                break;
                            } else {
                                System.out.println("Word - " + stringPlayerOne + " - provided by player one is correct!");
                                gameArrays.feedArrayOne(stringPlayerOne, gameLogic.getPlayerOneWordCounter());//array feed
                                //gameArrays.printArray();
                            }

                            System.out.println("Player two word must start with \"" + gameLogic.getPlayerOneSuffix() + "\" prefix.");//player 2 follows with his game
                            stringPlayerTwo = scannerTwo.next().toLowerCase();

                            if (stringPlayerTwo.length() == 1) {
                                stringPlayerTwo = gameLogic.getPlayerOneSuffix() + stringPlayerTwo;
                            }

                            System.out.println(stringPlayerTwo);
                            gameLogic.setPlayerTwoWord(stringPlayerTwo);
                            gameLogic.setPlayerTwoPrefix(stringPlayerTwo);
                            gameLogic.setPlayerTwoSuffix(stringPlayerTwo);
                            gameLogic.validatePlayerTwoWord();
                            limitedVocabulary.determineWordExists(stringPlayerTwo);
                            gameLogic.updatePlayerTwoWordCounter();
                            //System.out.println("Number of words entered by p2: " + gameLogic.getPlayerTwoWordCounter());

                            boolean firstCheckTwo = gameLogic.isFirstValidationTermination();
                            boolean secondCheckTwo = limitedVocabulary.isSecondValidationTermination();
                            //System.out.println(gameLogic.isFirstValidationTermination() + " " + limitedVocabulary.isSecondValidationTermination());

                            if (!firstCheckTwo || !secondCheckTwo) {
                                System.out.println("Player two provided an incorrect word or was unable to provide one!");
                                gameLogic.setPlayerOneStarts(true);
                                gameLogic.resetCounters();
                                gameArrays.calculatePointsPlayerTwo();
                                gameArrays.calculatePointsPlayerOne();
                                gameArrays.resetWordsPlayerTwoArray();
                                gameArrays.resetWordsPlayerOneArray();
                                System.out.println("Points accumulated so far by player one: " + gameArrays.getPointsPlayerOne());
                                System.out.println("Points accumulated so far by player two: " + gameArrays.getPointsPlayerTwo());
                                gameLogic.reduceLivesOfPlayerTwo();
                                System.out.println("Player two lives left: " + gameLogic.getLivesOfPlayerTwo());
                                gameLogic.determineEnoughLives();
                                break;
                            }
                            else {
                                System.out.println("Word - " + stringPlayerTwo + " - provided by player two is correct!");
                                gameArrays.feedArrayTwo(stringPlayerTwo, gameLogic.getPlayerTwoWordCounter());
                                //gameArrays.printArrayTwo();
                            }
                        }
                    }

                    else {
                        System.out.println("Player two starts this round");
                        while (gameLogic.getEnoughLives()) {
                            if (gameLogic.getPlayerTwoWordCounter() == 0) {
                                gameLogic.setRandomAlphabetIndex();
                                gameLogic.setRandomAlphabetChar();
                                System.out.println("Player two must enter word starting with \"" + gameLogic.getRandomAlphabetChar() + "\" character.");
                                stringPlayerTwo = scannerTwo.next().toLowerCase();

                                if (stringPlayerTwo.length() == 1) {
                                    stringPlayerTwo = gameLogic.getRandomAlphabetChar() + stringPlayerTwo;
                                }
                                System.out.println(stringPlayerTwo);
                                gameLogic.setPlayerTwoWord(stringPlayerTwo);
                                gameLogic.setPlayerTwoPrefix(stringPlayerTwo);
                                gameLogic.setPlayerTwoSuffix(stringPlayerTwo);
                                gameLogic.validatePlayerTwoWord();
                                limitedVocabulary.determineWordExists(stringPlayerTwo);
                                gameLogic.updatePlayerTwoWordCounter();
                                //System.out.println("Words entered by p2 (r1): " + gameLogic.getPlayerTwoWordCounter());
                            } else {
                                System.out.println("Player two word must start with \"" + gameLogic.getPlayerOneSuffix() + "\" prefix.");
                                stringPlayerTwo = scannerTwo.next().toLowerCase();

                                if (stringPlayerTwo.length() == 1) {
                                    stringPlayerTwo = gameLogic.getPlayerOneSuffix() + stringPlayerTwo;
                                }
                                System.out.println(stringPlayerTwo);
                                gameLogic.setPlayerTwoWord(stringPlayerTwo);
                                gameLogic.setPlayerTwoPrefix(stringPlayerTwo);
                                gameLogic.setPlayerTwoSuffix(stringPlayerTwo);
                                gameLogic.validatePlayerTwoWord();
                                limitedVocabulary.determineWordExists(stringPlayerTwo);
                                gameLogic.updatePlayerTwoWordCounter();
                                //System.out.println("Words entered by p2: " + gameLogic.getPlayerTwoWordCounter());
                            }

                            boolean firstCheckTwo = gameLogic.isFirstValidationTermination();
                            boolean secondCheckTwo = limitedVocabulary.isSecondValidationTermination();
                            //System.out.println(gameLogic.isFirstValidationTermination() + " " + limitedVocabulary.isSecondValidationTermination());

                            if (!firstCheckTwo || !secondCheckTwo) {
                                System.out.println("Player two provided an incorrect word or was unable to provide one!");
                                gameLogic.setPlayerOneStarts(true);
                                gameLogic.resetCounters();
                                gameArrays.calculatePointsPlayerTwo();
                                gameArrays.calculatePointsPlayerOne();
                                gameArrays.resetWordsPlayerTwoArray();
                                gameArrays.resetWordsPlayerOneArray();
                                System.out.println("Points accumulated so far by player one: " + gameArrays.getPointsPlayerOne());
                                System.out.println("Points accumulated so far by player two: " + gameArrays.getPointsPlayerTwo());
                                gameLogic.reduceLivesOfPlayerTwo();
                                System.out.println("Player two lives left: " + gameLogic.getLivesOfPlayerTwo());
                                gameLogic.determineEnoughLives();
                                break;
                            } else {
                                System.out.println("Word - " + stringPlayerTwo + " - provided by player two is correct!");
                                gameArrays.feedArrayTwo(stringPlayerTwo, gameLogic.getPlayerTwoWordCounter());
                                //gameArrays.printArrayTwo();
                            }

                            System.out.println("Player one word must start with \"" + gameLogic.getPlayerTwoSuffix() + "\" prefix.");
                            stringPlayerOne = scannerOne.next().toLowerCase();

                            if (stringPlayerOne.length() == 1) {
                                stringPlayerOne = gameLogic.getPlayerTwoSuffix() + stringPlayerOne;
                            }

                            System.out.println(stringPlayerOne);
                            gameLogic.setPlayerOneWord(stringPlayerOne);
                            gameLogic.setPlayerOnePrefix(stringPlayerOne);
                            gameLogic.setPlayerOneSuffix(stringPlayerOne);
                            gameLogic.validatePlayerOneWord();
                            limitedVocabulary.determineWordExists(stringPlayerOne);
                            gameLogic.updatePlayerOneWordCounter();
                            //System.out.println("Words entered by p1: " + gameLogic.getPlayerOneWordCounter());

                            boolean firstCheckOne = gameLogic.isFirstValidationTermination();
                            boolean secondCheckOne = limitedVocabulary.isSecondValidationTermination();
                            //System.out.println(gameLogic.isFirstValidationTermination() + " " + limitedVocabulary.isSecondValidationTermination());

                            if (!firstCheckOne || !secondCheckOne) {
                                System.out.println("Player one provided an incorrect word or was unable to provide one!");
                                gameLogic.setPlayerOneStarts(false);
                                gameLogic.resetCounters();
                                gameArrays.calculatePointsPlayerOne();
                                gameArrays.calculatePointsPlayerTwo();
                                gameArrays.resetWordsPlayerOneArray();
                                gameArrays.resetWordsPlayerTwoArray();
                                System.out.println("Points accumulated so far by player one: " + gameArrays.getPointsPlayerOne());
                                System.out.println("Points accumulated so far by player two: " + gameArrays.getPointsPlayerTwo());
                                gameLogic.reduceLivesOfPlayerOne();
                                System.out.println("Player one lives left: " + gameLogic.getLivesOfPlayerOne());
                                gameLogic.determineEnoughLives();
                                break;
                            } else {
                                System.out.println("Word - " + stringPlayerOne + " - provided by player one is correct!");
                                gameArrays.feedArrayOne(stringPlayerOne, gameLogic.getPlayerOneWordCounter());
                                //gameArrays.printArray();
                            }

                        }

                    }

                    if (gameLogic.getLivesOfPlayerOne() == 0 || gameLogic.getLivesOfPlayerTwo() == 0) {
                        gameLogic.determineGameWinner();

                        if (gameLogic.isPlayerOneWon()) {
                            System.out.println("Player one won this game!");
                        } else {
                            System.out.println("Player two won this game!");
                        }
                        System.out.println("Total points by player one: " + gameArrays.getPointsPlayerOne());//accumulation of all points by players
                        System.out.println("Total points by player two: " + gameArrays.getPointsPlayerTwo());

                        System.out.println("Would you like to play another game? Every input but \"yes\" will terminate the game!");
                        command = scan.next().toLowerCase();
                        if (command.equals("yes")) {
                            gameLogic.setBothPlayersLives(playersLives);//players lives are set back to default value
                            gameLogic.setEnoughLives(true);
                        } else {
                            break;
                        }
                    }
                }
            }
            else//neither play, nor instructions, nor quit entered
            {
                System.out.println();
                System.out.println("Incorrect command!");
            }
            printMenus.printSubMenu();
            command = scan.next();
            if (command.equals("play")) {//game can be played again from this level
                gameLogic.setBothPlayersLives(playersLives);
                gameLogic.setEnoughLives(true);
            }
        }
    }
}




















