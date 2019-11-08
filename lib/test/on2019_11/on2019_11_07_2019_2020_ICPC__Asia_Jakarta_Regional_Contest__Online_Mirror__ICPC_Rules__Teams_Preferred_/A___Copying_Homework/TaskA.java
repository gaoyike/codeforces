package on2019_11.on2019_11_07_2019_2020_ICPC__Asia_Jakarta_Regional_Contest__Online_Mirror__ICPC_Rules__Teams_Preferred_.A___Copying_Homework;



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
