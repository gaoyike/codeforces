package on2021_03.on2021_03_27_GCJ2021.MoonsandUmbrellas1;





import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MoonsandUmbrellas {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for(int i = 1 ; i <= k; i++){
            int x = in.readInt();
            int y = in.readInt();
            String s = in.readString();
            out.printLine("Case #" + i +": " + moonsAndUmbrellas(x,y,s));
        }
    }
    private static int moonsAndUmbrellas(int x, int y, String s) {
        Integer[][] dp = new Integer[s.length()+1][2];
        for (int i = 0; i < dp.length; i++) {
           Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        return Math.min(find(1, 0, dp, s,x,y), find(0, 0, dp, s,x,y));
    }
    //place 0 == 'C', 1 == 'J'
    private static int find(int placed, int index, Integer[][] dp, String s, int X, int Y){
        if (dp[index][placed] != Integer.MAX_VALUE / 2){
            return dp[index][placed];
        }
        if (s.charAt(index) == 'C' && placed == 1 ||
                s.charAt(index) == 'J' && placed == 0
        ){
            dp[index][placed] = Integer.MAX_VALUE / 2;
        return dp[index][placed];
        }
        if (index == s.length() - 1)
            return 0;
        int cur = Integer.MAX_VALUE / 2;
        cur = Math.min(cur , (placed == 1 ? Y:0) + find(0, index+ 1, dp, s, X,Y));
        cur = Math.min(cur , (placed == 0 ? X:0) + find(1, index+ 1, dp, s, X,Y));
        dp[index][placed] = cur;
        return dp[index][placed];
    }

}
