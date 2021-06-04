package on2021_04.on2021_04_25_GCJ2021.BrokenClock;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrokenClock {
    static final long MOD = 360 * 12 * 100000_00000L;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for (int i = 1; i <= k; i++) {
            solve(in, out);
        }
    }
    public  void solve(InputReader in, OutputWriter out) {
        long v[] = new long[3];
        for (int i = 0; i < 3; i++) {
            v[i] = in.readLong();
        }
        for (int i = 0; i < 6; i++) {
            long x = (v[1] - v[0] + MOD) % MOD,
                    y = (v[2] - v[0] + MOD) % MOD;
            x = ((x + ((x * 4) % 11) * MOD) / 11) % MOD;
            y = ((y + ((y * 493) % 719) * MOD) / 719) % MOD;
            if (x == y) {
                long n = x % 1000000000;
                x /= 1000000000;
                long s = x % 60;
                x /= 60;
                long m = x % 60;
                x /= 60;
                out.printLine(x + " " + m + " " + s + " " + n);
                return;
            }
            if (i % 2 == 0) {
                long t = v[1];
                v[1] = v[2];
                v[2] = t;
            } else {
                long t = v[0];
                v[0] = v[1];
                v[1] = t;
            }
        }
        throw new AssertionError();
    }
}
