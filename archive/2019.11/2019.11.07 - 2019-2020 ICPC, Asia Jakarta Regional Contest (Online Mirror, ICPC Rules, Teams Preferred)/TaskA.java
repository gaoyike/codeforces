package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] ary = in.readIntArray(n);
        int c = ary[0];
        for(int i = 1; i < n; i++) {
            int t = ary[i-1];
            ary[i-1] = ary[i];
            ary[i] = t;
        }
        ary[n-1] = c;
        out.printLine(ary);
    }
}
