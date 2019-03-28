package readman;

import net.egork.collections.Pair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt(); // numbers of days
        int m = in.readInt(); // total number of gadgets
        int k = in.readInt(); // required number of gadgets
        long s = in.readLong(); // number of $

        int[] dollar = in.readIntArray(n);
        int[] pound = in.readIntArray(n);

        Pair<Integer, Integer>[] res = new Pair[k];
        Item[] items = new Item[m];
        for (int i = 0; i < m; i++) {
            items[i] = new Item();
            items[i].type = in.readInt();
            items[i].cost = in.readInt();
            items[i].pos = i;
        }
        int start = 0;
        int end = n-1;
        int minDay = -1;
        while (end >= start) {
            int mid =  start + (end -start) / 2;
            if (canDo(mid, dollar, m, k, s, pound, items, res)) {
                minDay = mid;
                end = mid - 1;
            }
            else {
                start = mid +1;
            }
        }
        if (minDay == -1) {
            out.printLine("-1");
        }
        else {
            out.printLine(minDay+1);
            for (int i = 0; i < k; i++) {
                out.printLine((res[i].first+1)+" "+(res[i].second+1));
            }
        }
    }

    private boolean canDo(int cur, int[] dollar, int m, int k, long s, int[] pound, Item[] items,Pair<Integer, Integer>[] res) {
        int dayD = -1;
        int dayP = -1;
        int minD = Integer.MAX_VALUE;
        int minP = Integer.MAX_VALUE;

        for (int i = 0; i <= cur; i++) {
            if (dollar[i] < minD) {
                minD = dollar[i];
                dayD = i;
            }
            if (pound[i] < minP) {
                minP = pound[i];
                dayP = i;
            }
        }

        for (int i = 0; i < m; i++) {
            if (items[i].type == 1){
                items[i].need = BigDecimal.valueOf(minD).multiply(BigDecimal.valueOf(items[i].cost));
            }
            else {
                items[i].need = BigDecimal.valueOf(minP).multiply(BigDecimal.valueOf(items[i].cost));
            }
        }
        Arrays.sort(items); // sort by the required burles
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < k; i++) {
           sum = sum.add(items[i].need);
        }
        if (sum.compareTo(new BigDecimal(s)) <= 0) {
            for (int i = 0; i < k; i++) {
                if (items[i].type == 1) {
                    res[i] = Pair.makePair(items[i].pos,dayD);
                }
                else {
                    res[i] = Pair.makePair(items[i].pos,dayP);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    class Item implements Comparable<Item> {
        int type;
        int cost;
        int pos;
        BigDecimal need;

        @Override
        public int compareTo(Item o) {
            return need.compareTo(o.need);
        }
    }
}
