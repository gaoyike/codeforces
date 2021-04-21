package readman;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

public class ReversortEngineering {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for(int i = 1 ; i <= k; i++){
            int n = in.readInt();
            int c = in.readInt();
            int[] r = ReverEng(n, c);
            if(r == null)
                out.printLine("Case #" + i +": IMPOSSIBLE");
            else {
                out.print("Case #" + i + ": ");
                out.printLine(ReverEng(n,c));
            }
        }
    }
    private int[] ReverEng(int n, int c){
        if (!check(n, c))
            return null;
        int[] arr = new int[n];
        for(int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        for (int i = n - 2; i >= 0 ; i--) {
            for (int len = n - i; len >= 1; len--) {
                if (check(i, n, c - len)) {
                    rev(arr, i, i + len - 1);
                    c -= len;
                    break;
                }
            }
        }
        return arr;
    }

    // n + (n - 1) + (n - 2)+ ... + 2
    private boolean check (int n, int c) {
        return n - 1 <= c && c <= (n) * (n + 1) / 2 - 1;
    }
    // n + (n - 1) + ....+(n - p + 1)
    private boolean check (int p, int n, int c) {
        return p <= c && c <= p * (2 *n - (p - 1)) / 2;
    }

    private void rev(int[] arr, int i, int j){
        while (i < j){
            swap(arr, i++, j--);
        }
    }
    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
