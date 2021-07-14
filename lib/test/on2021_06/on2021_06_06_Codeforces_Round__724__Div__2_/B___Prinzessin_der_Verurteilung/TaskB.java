package on2021_06.on2021_06_06_Codeforces_Round__724__Div__2_.B___Prinzessin_der_Verurteilung;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String s = in.readString();
         List<String> list = new ArrayList<>();
         list.add("");
        while (true) {
            int size = list.size();
            for (int i = 0; i < size; i++){
                if (list.size() > 1 && list.get(i) == "")
                    continue;
                for (int j = 0; j < 26; j++) {
                    String ss = list.get(i) + (char) ('a' + j);
                     if (!s.contains(ss)){
                        out.printLine(ss);
                        return;
                    }
                     list.add(ss);
                }
            }
        }
    }
}
