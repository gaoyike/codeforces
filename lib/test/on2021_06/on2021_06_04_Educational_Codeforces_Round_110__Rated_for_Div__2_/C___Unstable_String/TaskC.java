package on2021_06.on2021_06_04_Educational_Codeforces_Round_110__Rated_for_Div__2_.C___Unstable_String;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readLine();
        StringBuffer sb = new StringBuffer(s);
        for (int i = 1; i < sb.length(); i+=2){
            if (sb.charAt(i) == '1')
                sb.setCharAt(i, '0');
            else if (sb.charAt(i) == '0')
                sb.setCharAt(i, '1');
        }
        long count=0;
        int[] cc = new int[255];
       int left = 0;
       int right = 0;
       while(right < sb.length()) {
           cc[sb.charAt(right++) - '0'] ++;
           if (cc[0]  != 0 && cc[1] != 0) { // if right pointer found first '1' or '0'
               while(cc[0] != 0 && cc[1] != 0){
                   cc[sb.charAt(left++) - '0']--;
               }
           }
           count += (right  - left);
       }
       out.printLine(count);
    }
}
