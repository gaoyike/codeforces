package readman;
import net.egork.generated.collections.*;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

import static net.egork.numbers.IntegerUtils.gcd;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for (int i = 0; i < k; i++) {
            int f = in.readInt();
            out.printLine(solve(in.readIntArray(f)));
        }
    }

    private int solve(int[] arr) {
        int count = 0;
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for(int a : arr){
            if(a % 2 == 0)
                l2.add(a);
            else
                l1.add(a);
        }
        Collections.sort(l1, Collections.reverseOrder());
        Collections.sort(l2, Collections.reverseOrder());
        List<Integer> list = new ArrayList<>();
        list.addAll(l2);
        list.addAll(l1);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (gcd(list.get(i), list.get(j) * 2) > 1)
                    count++;
            }
        }
        return count;
    }

}
