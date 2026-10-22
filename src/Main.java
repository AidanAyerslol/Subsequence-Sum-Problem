import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class Main {

    public static void main(String[] args) throws IOException {
        String filename1 = "input-5.1.txt";
        String filename2 = "input-5.2.txt";
        String filename3 = "input-5.3.txt";
        String filename4 = "input-5.4.txt";
        increasingSequenceSum(filename1);
        increasingSequenceSum(filename2);
        increasingSequenceSum(filename3);
        increasingSequenceSum(filename4);

    }

    public static void increasingSequenceSum(String filename) throws IOException {
        //initialize a bunch of stuff we will need
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int length = scanner.nextInt();
        int[] array = new int[length];
        int[] highestSum = new int[length];
        int[] lastIndex = new int[length];
        int maxSumIndex = 0;

        //fill the arrays with their initial values
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }
        //the highest Sum for the first index is just the value of the first index
        highestSum[0] = array[0];
        lastIndex[0] = -1;

        //for every other index (i) after 1:
        for (int i = 1; i < length; i++) {
            int maxSum = 0;
            //check every index (j) before i
            //NOTE: There are two nested for loops here, which is O(n^2). This is the fastest growing block in the program
            for (int j = 0; j < i; j++) {
                //if the increasing subsequence rule is followed, consider the highest sum of the previous index (j)
                if (array[i] >= array[j]) {
                    //if its highest sum is the highest so far, record it as a maximum and record the index so we can reproduce the sequence
                    if (highestSum[j]+array[i] > maxSum){
                        maxSum = highestSum[j]+array[i];
                        lastIndex[i] = j;
                    }
                    //set the highest sum (for an array ending at index i) to the maximum we recorded
                    highestSum[i] = maxSum;
                }
            }
        }

        //iterate through the list and find the global max for the highest Sum in the whole sequence
        int maxSum = 0;
        for (int i = 0; i < length; i++) {
            if (highestSum[i] > maxSum) {
                maxSum = highestSum[i];
                maxSumIndex = i;
            }
        }

        //this is where we begin printing the results
        System.out.println("\n" + "Highest Sum of an Increasing Subsequence for "+ filename + " is " +maxSum);
        System.out.println("The Optimal Subsequence is: ");


        int sequenceLength = 0;
        int k = maxSumIndex;
        //this while loop finds the length of the subsequence we need to reproduce
        while (k > -1){
            sequenceLength++;
            k = lastIndex[k];
        }
        int[] fullsequence = new int[sequenceLength];
        //this for loop recreates the subsequence, albeit in backwards order
        for (int i = 0; i < sequenceLength; i++) {
            fullsequence[i] = array[maxSumIndex];
            maxSumIndex = lastIndex[maxSumIndex];
        }

        //this for loop prints the subsequence, this time in the correct order
        for (int i = sequenceLength - 1; i > -1; i--){
            System.out.print(fullsequence[i]+", ");
        }

    }
}





