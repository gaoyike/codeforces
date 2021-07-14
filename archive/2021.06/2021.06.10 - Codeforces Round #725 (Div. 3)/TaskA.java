package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int maxIndex = -1;
        int minIndex = -1;
        int n = in.readInt();
        int[]ary = in.readIntArray(n);
        int left = 0,right = 0,both = 0;
        for (int i = 0; i < n; i++) {
            if (ary[i] == 1)
                minIndex = i;
            else if (ary[i] == n)
                maxIndex = i;
        }
        boolean one = false;
        boolean two = false;
        for (int i = 0; i < n; i++) {
            if(ary[i] == 1)
                one = true;
            else  if (ary[i] == n)
                two = true;
            if (one && two){
                left = i + 1;
                break;
            }
        }
        one = false;
        two = false;
        for (int i = n - 1; i >= 0; i--) {
            if(ary[i] == 1)
                one = true;
            else  if (ary[i] == n)
                two = true;
            if (one && two){
                right = n - i;
                break;
            }
        }
        both = Math.min(minIndex,maxIndex) + 1 + n - Math.max(minIndex, maxIndex);
        out.printLine(Math.min(Math.min(left, right),both));
    }
}
