package on2019_03.on2019_03_13_Educational_Codeforces_Round_3.B___The_Best_Gift;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] ary = in.readIntArray(n);
        int[] count = new int[m];
        long res = 0;
        for (int i = 0; i < ary.length; i++) {
            count[ary[i]-1]++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                res += count[i]*count[j];
            }
        }
        out.print(res);
    }
}
