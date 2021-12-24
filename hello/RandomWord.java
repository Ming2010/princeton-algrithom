/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String output = "";
        double i = 0;
        while (!StdIn.isEmpty()){
            String curWord = StdIn.readString();
            // update counter
            i += 1;
            // generate a random number
            if (StdRandom.bernoulli(1/i)){
                output = curWord;
            }
        }
        StdOut.println(output);

    }
}
