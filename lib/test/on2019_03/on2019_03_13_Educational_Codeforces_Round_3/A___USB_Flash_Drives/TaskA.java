package on2019_03.on2019_03_13_Educational_Codeforces_Round_3.A___USB_Flash_Drives;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int res = 0;
        int[] ary = in.readIntArray(n);
        Arrays.sort(ary);
        for(int i = ary.length - 1; i>=0; i--) {
            if (m > 0 && ary[i] <= m){
                m -= ary[i];
                res++;
            }
            else if (m > 0 && ary[i] > m) {
                m -= ary[i];
                res++;
            }
        }
        out.print(res);
    }
}
