import java.util.Random;
//underlying logic of the game provided below; multitude of Booleans since many conditions are only "yes" or "no"
public class GameLogic {
    private boolean playerOneStarts = true;

    private int playerOneWordCounter = 0; // if = 0, first character rule if > 0, last two characteres
    private int playerTwoWordCounter = 0;
    private int logicCounter = 0;

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int randomAlphabetIndex;
    private char randomAlphabetChar;

    private String playerOneWord;
    private String playerOnePrefix;
    private String playerOneSuffix;

    private String playerTwoWord;
    private String playerTwoPrefix;
    private String playerTwoSuffix;

    private boolean firstValidationTermination;

    private int livesOfPlayerOne;
    private int livesOfPlayerTwo;
    private boolean enoughLives = true;
    private boolean playerOneWon;


    public int getLivesOfPlayerOne()
    {
        return livesOfPlayerOne;
    }

    public int getLivesOfPlayerTwo()
    {
        return livesOfPlayerTwo;
    }


    public void setEnoughLives(boolean enoughLives) {
        this.enoughLives = enoughLives;
    }

    public boolean doesPlayerOneStart() {
        return playerOneStarts;
    }

    public void setPlayerOneStarts(boolean trueFalse)
    {
        this.playerOneStarts = trueFalse;
    }

    public int getPlayerOneWordCounter() {
        return playerOneWordCounter;
    }

    public int getPlayerTwoWordCounter() {
        return playerTwoWordCounter;
    }

    public char getRandomAlphabetChar() {
        return randomAlphabetChar;
    }

    public String getPlayerTwoSuffix() {
        return playerTwoSuffix;
    }

    public boolean isFirstValidationTermination()
    {
        return firstValidationTermination;
    }

    public void reduceLivesOfPlayerOne()
    {
        this.livesOfPlayerOne--;
    }

    public void reduceLivesOfPlayerTwo()
    {
        this.livesOfPlayerTwo--;
    }

    public String getPlayerOneSuffix() {
        return playerOneSuffix;
    }

    public boolean isPlayerOneWon() {
        return playerOneWon;
    }


//------------------------------

    public void determineEnoughLives()//if either one life drops to zero, enoughLives becomes false and breaks the cycle; option to play another game will be displayed
    {
        if (livesOfPlayerOne != 0 && livesOfPlayerTwo != 0)
        {
            enoughLives = true;
        }
        else
        {
            enoughLives = false;
        }
    }

    public void determineGameWinner()
    {
        if (livesOfPlayerTwo == 0)
        {
            this.playerOneWon = true;
            this.playerOneStarts = true;
        }
        else
        {
            this.playerOneWon = false;
            this.playerOneStarts = false;
        }
    }

    public boolean getEnoughLives()
    {
        return enoughLives;
    }


    

    public void updatePlayerOneWordCounter()
    {
        this.playerOneWordCounter++;
    }

    public void updatePlayerTwoWordCounter()
    {
        this.playerTwoWordCounter++;
    }

    public void resetCounters()
    {
        this.logicCounter = 0;
        this.playerOneWordCounter = 0;
        this.playerTwoWordCounter = 0;
    }

    public void setRandomAlphabetIndex()//random number generated within alphabet range (26 letters)
    {
        Random random = new Random();
        this.randomAlphabetIndex = random.nextInt(25);
    }

    public void setRandomAlphabetChar()//above used to determine character one will start game with
    {
        this.randomAlphabetChar = alphabet.charAt(randomAlphabetIndex);
    }

    public void setPlayerOneWord(String playerOneWord)
    {
        this.playerOneWord = playerOneWord;
    }

    public void setPlayerOnePrefix(String playerOneWord)//prefixes needed establishing to compare them against suffixes of previou's player word
    {
        this.playerOnePrefix = ("" + playerOneWord.charAt(0) + playerOneWord.charAt(1));
    }

    public void setPlayerOneSuffix(String playerOneWord)//suffixes needed as prefixes for subsequent player's word
    {
        this.playerOneSuffix = ("" + playerOneWord.charAt(playerOneWord.length() - 2) + playerOneWord.charAt(playerOneWord.length() - 1));
    }


    public void setPlayerTwoWord(String playerTwoWord)
    {
        this.playerTwoWord = playerTwoWord;
    }

    public void setPlayerTwoPrefix(String playerTwoWord)//prefixes needed establishing to compare them against suffixes of previou's player word
    {
        this.playerTwoPrefix = ("" + playerTwoWord.charAt(0) + playerTwoWord.charAt(1));
    }

    public void setPlayerTwoSuffix(String playerTwoWord)//suffixes needed as prefixes for subsequent player's word
    {
        this.playerTwoSuffix = ("" + playerTwoWord.charAt(playerTwoWord.length() - 2) + playerTwoWord.charAt(playerTwoWord.length() - 1));
    }

    public void validatePlayerOneWord() //this check relates to first Boolean validator
    {
        if (logicCounter == 0)
        {
            if (playerOneWord.charAt(0) == randomAlphabetChar && playerOneWord.length() >= 3) {
                this.firstValidationTermination = true;
                this.logicCounter = 1;

            } else {
                this.firstValidationTermination = false;
            }
        }

        else
        {
            char playerAnswer = playerOneWord.charAt(playerOneWord.length() - 1);

            if (!playerOnePrefix.equals(playerTwoSuffix) || playerOneWord.length() < 3 || playerAnswer == '-')
            {
                this.firstValidationTermination = false;
            }
        }
    }

    public void validatePlayerTwoWord() //this check relates to first Boolean validator
    {
        if (logicCounter == 0)
        {
            if (playerTwoWord.charAt(0) == randomAlphabetChar && playerTwoWord.length() >= 3) {
                this.firstValidationTermination = true;
                this.logicCounter = 1;

            } else {
                this.firstValidationTermination = false;
            }
        }
        else
        {
            char playerAnswer = playerTwoWord.charAt(playerTwoWord.length() - 1);

            if (!playerTwoPrefix.equals(playerOneSuffix) || playerTwoWord.length() < 3 || playerAnswer == '-') {

                this.firstValidationTermination = false;
            }
        }
    }

    public void setBothPlayersLives(int lives)
    {
        this.livesOfPlayerOne = lives;
        this.livesOfPlayerTwo = lives;
    }


}
