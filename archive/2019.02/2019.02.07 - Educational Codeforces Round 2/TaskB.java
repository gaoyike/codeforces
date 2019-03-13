package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;


public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int[] ary_a = in.readIntArray(a);
        int[] ary_b = in.readIntArray(b);
        shuffle(ary_a);
        Arrays.sort(ary_a);
        for (int i = 0; i < ary_b.length; i++) {
            out.print(count(ary_a, ary_b[i]) + " ");
        }
    }

    /**
     * test20卡中了sort的最慢时间, 需要先shuffle再sort
     * @param a
     */
    private static void shuffle(int[] a) {
        int n = a.length, tmp;
        for (int i = 0; i < n; ++i) {
            int r = i + (int) (Math.random() * (n - i));
            tmp = a[i];
            a[i] = a[r];
            a[r] = tmp;
        }
    }
    private static int count(int[] ary, int n) {
        int start = 0;
        int end = ary.length;
        while (end > start) {
            int mid = start + (end - start) / 2;
            if (n >= ary[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

}
