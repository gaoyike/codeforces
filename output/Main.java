import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author readman
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        MoonsandUmbrellas solver = new MoonsandUmbrellas();
        solver.solve(1, in, out);
        out.close();
    }

    static class MoonsandUmbrellas {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int k = in.readInt();
            for (int i = 1; i <= k; i++) {
                int x = in.readInt();
                int y = in.readInt();
                String s = in.readString();
                out.printLine("Case #" + i + ": " + moonsAndUmbrellas(x, y, s));
            }
        }

        private static int moonsAndUmbrellas(int x, int y, String s) {
            Integer[][] dp = new Integer[s.length() + 1][2];
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            }
            return Math.min(find(1, 0, dp, s, x, y), find(0, 0, dp, s, x, y));
        }

        private static int find(int placed, int index, Integer[][] dp, String s, int X, int Y) {
            if (dp[index][placed] != Integer.MAX_VALUE / 2) {
                return dp[index][placed];
            }
            if (s.charAt(index) == 'C' && placed == 1 ||
                    s.charAt(index) == 'J' && placed == 0
            ) {
                dp[index][placed] = Integer.MAX_VALUE / 2;
                return dp[index][placed];
            }
            if (index == s.length() - 1)
                return 0;
            int cur = Integer.MAX_VALUE / 2;
            cur = Math.min(cur, (placed == 1 ? Y : 0) + find(0, index + 1, dp, s, X, Y));
            cur = Math.min(cur, (placed == 0 ? X : 0) + find(1, index + 1, dp, s, X, Y));
            dp[index][placed] = cur;
            return dp[index][placed];
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

