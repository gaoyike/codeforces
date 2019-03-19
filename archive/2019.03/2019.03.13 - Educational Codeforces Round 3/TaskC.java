package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] ary = in.readIntArray(n);
        long res = 0;
        Arrays.sort(ary);
        int sum = 0;
        for (int i:ary) {
            sum+=i;
        }
        for (int i = 0; i < n; i++) {
            int b = sum / (n - i);
            res += Math.abs(ary[i] - b);
            sum -= b;
        }
        out.print(res/2);
    }
}
