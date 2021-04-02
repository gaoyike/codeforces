package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

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
        int ans = 0;
        int i = 0, j = 0;
        char[] chars = s.toCharArray();
        while (i < s.length() && j < s.length()) {
            char c = chars[j];
            if (c == '?') {
                j++;
            } else {
                if (i == j) {
                    i++;
                    j++;
                } else {
                    Character start = null, end = null;
                    if (i > 0) {
                        start = chars[i - 1];
                    }
                    end = chars[j];
                    if (start == null) {
                        start = end;
                    }
                    char replace;
                    if (start.equals(end)) {
                        replace = start;
                    } else if (x > y) {
                        if ('J' == start) {
                            replace = 'C';
                        } else {
                            replace = 'J';
                        }
                    } else {
                        if ('C' == start) {
                            replace = 'C';
                        } else {
                            replace = 'J';
                        }
                    }
                    for (int k = i; k < j; k++) {
                        chars[k] = replace;
                    }
                    j++;
                    i = j;
                }
            }
        }
        ans = getSum(chars, i, x, y);
        return ans;
    }

    private static int getSum(char[] chars, int i, int x, int y) {
        int ans = 0;
        for (int k = 1; k < i; k++) {
            if (chars[k] != chars[k - 1]) {
                if (chars[k] == 'J') {
                    ans += x;
                } else {
                    ans += y;
                }
            }
        }
        return ans;
    }
}
