package on2021_06.on2021_06_06_Codeforces_Round__724__Div__2_.A___Omkar_and_Bad_Story;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] ary = in.readIntArray(n);
        Set<Integer> set = new HashSet<>();
        List<Integer> list  = new ArrayList<Integer>();
        for(int a : ary) {
            set.add(a);
            list.add(a);
        }
        boolean add = true;
        while (set.size() <= 300 && add){
             add = false;
            for(int i = 0; i < list.size(); i++){
                for (int j = i + 1; j < list.size(); j++) {
                    int nn = Math.abs(list.get(i) - list.get(j));
                        if (!set.contains(nn)){
                            set.add(nn);
                            list.add(nn);
                            add = true;
                            break;
                        }
                }
            }
        }
        if (set.size() > 300){
            out.printLine("NO");
        }else{
            out.printLine("YES");
            out.printLine(list.size());
            for(int l : list){
                out.print(l +" ");
            }
            out.printLine();
        }
    }
}
