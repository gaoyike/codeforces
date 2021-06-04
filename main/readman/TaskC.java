package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for (int i = 0; i < k; i++) {
            solve(in.readLine());
        }
    }
    private int solve(String s) {

    }
    private boolean test(String s) {
        StringBuffer sb = new StringBuffer(s);
        boolean found = false;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '?') {
                found = true;
            }else{
                if (found){
                    int j = i - 1;
                    char c;
                    if (sb.charAt(i) == '1')
                        c = '0';
                    else
                        c = '1';
                    while (j >= 0 && sb.charAt(j) == '?'){
                        sb.setCharAt(j, c);
                        if (c == '1')
                            c = '0';
                        else
                            c= '1';
                        j--;
                    }
                    int k = i +1;
                    char d;
                    if (sb.charAt(i) == '1')
                        d = '0';
                    else
                        d = '1';
                    while (k <= sb.length() - 1 && sb.charAt(k) == '?'){
                        sb.setCharAt(k, d);
                        if (d == '1')
                            d = '0';
                        else
                            d= '1';
                    }
                    found = false;
                }
            }
        }
        char e = '0';
        if (found) {

        }
    }
}
