package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] ary = in.readIntArray(n);
        Arrays.sort(ary);
        int sum = 0;
        for (int a : ary)
            sum += a;
        if (sum % n != 0) {
            out.printLine(-1);
            return;
        }
        int count = 0;
        int t= 0;
        for (int i = 0; i < ary.length; i++) {
            ary[i] -= sum / n;
            if (ary[i] < 0)
                t += ary[i] ;
        }
        for (int i = ary.length - 1; i >= 0; i--) {
            if (t >= 0)
                break;
                t += ary[i];
                count ++;
        }
        out.printLine(count);
    }
}
