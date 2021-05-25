import java.util.*;
//class responsible for players points; arrays are fed when both checks pass
public class GameArrays {

    private int[] wordsPlayerOneArray = new int[10];
    private int pointsPlayerOne = 0;

    private int[] wordsPlayerTwoArray = new int[10];
    private int pointsPlayerTwo = 0;

    public int getPointsPlayerOne() {
        return pointsPlayerOne;
    }

    public int getPointsPlayerTwo() {
        return pointsPlayerTwo;
    }

    public void feedArrayOne(String wordVerifiedOne, int index)
    {
        int wordLength = wordVerifiedOne.length();

        for (int i = index; i < wordsPlayerOneArray.length; i++)
        {
            wordsPlayerOneArray[i] = wordLength;
            break;
        }
    }

    public void feedArrayTwo(String wordVerifiedTwo, int index)
    {
        int wordLength = wordVerifiedTwo.length();//no words are fed into array, only numbers

        for (int i = index; i < wordsPlayerTwoArray.length; i++)
        {
            wordsPlayerTwoArray[i] = wordLength;
            break;
        }
    }

    public void calculatePointsPlayerOne()//points assignements based on criteria provided
    {
        for (int i = 0; i < wordsPlayerOneArray.length; i++)
        {
            if (wordsPlayerOneArray[i] == 0 && wordsPlayerOneArray[i + 1] == 0)
            {
                break;
            }
            else if (wordsPlayerOneArray[i] != 0 && wordsPlayerOneArray[i + 1] == 0)
            {
                this.pointsPlayerOne = pointsPlayerOne + 0;
            }
            else if (wordsPlayerOneArray[i] == 0 && wordsPlayerOneArray[i + 1] != 0)
            {
                this.pointsPlayerOne = pointsPlayerOne + 1;
            }
            else if (wordsPlayerOneArray[i] > 7 && wordsPlayerOneArray[i + 1] > 7)
            {
                this.pointsPlayerOne = pointsPlayerOne + 5;
            }
            else
            {
                this.pointsPlayerOne = pointsPlayerOne + 1;
            }
        }
    }

    public void calculatePointsPlayerTwo()
    {
        for (int i = 0; i < wordsPlayerTwoArray.length; i++)
        {
            if (wordsPlayerTwoArray[i] == 0 && wordsPlayerTwoArray[i + 1] == 0)
            {
                break;
            }
            else if (wordsPlayerTwoArray[i] != 0 && wordsPlayerTwoArray[i + 1] == 0)
            {
                this.pointsPlayerTwo = pointsPlayerTwo + 0;
            }
            else if (wordsPlayerTwoArray[i] == 0 && wordsPlayerTwoArray[i + 1] != 0)
            {
                this.pointsPlayerTwo = pointsPlayerTwo + 1;
            }
            else if (wordsPlayerTwoArray[i] > 7 && wordsPlayerTwoArray[i + 1] > 7)
            {
                this.pointsPlayerTwo = pointsPlayerTwo + 5;
            }

            else
            {
                this.pointsPlayerTwo = pointsPlayerTwo + 1;
            }
        }
    }

    public void resetWordsPlayerOneArray()
    {
        Arrays.fill(wordsPlayerOneArray, 0);
    }

    public void resetWordsPlayerTwoArray()
    {
        Arrays.fill(wordsPlayerTwoArray, 0);
    }

    public void printArray()
    {
        for (int l : wordsPlayerOneArray)
        {
            System.out.println(l);
        }
    }

    public void printArrayTwo()//testing of whether array is corrected fed numbers
    {
        for (int l : wordsPlayerTwoArray)
        {
            System.out.println(l);
        }
    }
}
