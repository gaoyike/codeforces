package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        for(int i = 0; i < n; i++) {
            int m = in.readInt();
            int even = 0;
            for(int j = 0; j < m; j++) {
                int k = in.readInt();
                if(k % 2 == 0) {
                    even++;
                }
            }
            if(even == m) {
                out.printLine("NO");
            }
            else if(m % 2 == 0 && even == 0) {
                out.printLine("NO");
            }
            else {
                out.printLine("YES");
            }

        }
    }
}
