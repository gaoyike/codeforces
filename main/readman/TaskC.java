package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int l = in.readInt();
        int r = in.readInt();
        int[] ary = in.readIntArray(n);
        List<Integer> list = new ArrayList<>();
        for (int a : ary)
            list.add(a);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            ary[i] = list.get(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            int low = lowerBound(ary, l - ary[i]);
            int up = upperBound(ary,r  - ary[i]);
            res -= low;
            res += up;
            if (l <= 2 * ary[i] && 2 * ary[i] <= r)
                res--;
        }
        out.printLine(res / 2);
    }
    int lowerBound(int a[],  int x) {
        int l= -1,r= a.length;
        while(l+1<r) {
            int m=(l+r)>>>1;
            if(a[m]>=x) r=m;
            else l=m;
        }
        return r;
    }
    int upperBound(int a[],  int x) {
        int l= -1,r= a.length;
        while(l+1<r) {
            int m=(l+r)>>>1;
            if(a[m]<=x) l=m;
            else r=m;
        }
        return l+1;
    }

}
