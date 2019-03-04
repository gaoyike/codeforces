package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] count = new int[26];
        /**
         * counting the  occurences
         */
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        int i = 0;
        int j  = 25;
        while (i <= j) {
            while (i < count.length && count[i] % 2 == 0) { // find the left side first odd
                i ++;
            }
            while (j >=0 && count[j] % 2 == 0) { // find the right side first odd
                j --;
            }
            // move right to first (because it is asked for the lexicographically (alphabetically) smallest palindrome)
            if (i < count.length) {
                count[i++] ++;
            }
            if (j >=0) {
                count[j--]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int odd_Index = -1;
        for (int k = count.length - 1; k >=0 ; k--) {
            if (count[k] % 2 != 0) { // we know there is no more than 1 odd number in our count array
                odd_Index = k;
                count[k] --;
            }
            for (int l = 0; l < count[k]; l++) {
                if (l % 2 == 0) {
                    sb.append((char)('a' + k));
                }
                else {
                    sb.insert(0,(char)('a' + k));
                }
            }
        }
        if (odd_Index != -1) {
            sb.insert(sb.length()/2, (char)('a' + odd_Index)); // put the only odd number (if it has) in the middle
        }
        out.print(sb.toString());
    }
}
