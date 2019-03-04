import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s = in.readString();
            int[] count = new int[26];
            /**
             * counting the  occurences
             */
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            int i = 0;
            int j = 25;
            while (i <= j) {
                while (i < count.length && count[i] % 2 == 0) { // find the left side first odd
                    i++;
                }
                while (j >= 0 && count[j] % 2 == 0) { // find the right side first odd
                    j--;
                }
                // move right to first (because it is asked for the lexicographically (alphabetically) smallest palindrome)
                if (i < count.length) {
                    count[i++]++;
                }
                if (j >= 0) {
                    count[j--]--;
                }
            }
            StringBuilder sb = new StringBuilder();
            int odd_Index = -1;
            for (int k = count.length - 1; k >= 0; k--) {
                if (count[k] % 2 != 0) { // we know there is no more than 1 odd number in our count array
                    odd_Index = k;
                    count[k]--;
                }
                for (int l = 0; l < count[k]; l++) {
                    if (l % 2 == 0) {
                        sb.append((char) ('a' + k));
                    } else {
                        sb.insert(0, (char) ('a' + k));
                    }
                }
            }
            if (odd_Index != -1) {
                sb.insert(sb.length() / 2, (char) ('a' + odd_Index)); // put the only odd number (if it has) in the middle
            }
            out.print(sb.toString());
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

        public void close() {
            writer.close();
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
}

