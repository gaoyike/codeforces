import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
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
        ReversortEngineering solver = new ReversortEngineering();
        solver.solve(1, in, out);
        out.close();
    }

    static class ReversortEngineering {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int k = in.readInt();
            for (int i = 1; i <= k; i++) {
                int n = in.readInt();
                int c = in.readInt();
                int[] r = ReverEng(n, c);
                if (r == null)
                    out.printLine("Case #" + i + ": IMPOSSIBLE");
                else {
                    out.print("Case #" + i + ": ");
                    out.printLine(r);
                }
            }
        }

        private int[] ReverEng(int n, int c) {
            if (!check(n, c))
                return null;
            int[] arr = new int[n];
            for (int i = 1; i <= n; i++) {
                arr[i - 1] = i;
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int len = n - i; len >= 1; len--) {
                    if (check(i, n, c - len)) {
                        rev(arr, i, i + len - 1);
                        c -= len;
                        break;
                    }
                }
            }
            return arr;
        }

        private boolean check(int n, int c) {
            return n - 1 <= c && c <= (n) * (n + 1) / 2 - 1;
        }

        private boolean check(int p, int n, int c) {
            return p <= c && c <= p * (2 * n - (p - 1)) / 2;
        }

        private void rev(int[] arr, int i, int j) {
            while (i < j) {
                swap(arr, i++, j--);
            }
        }

        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
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

        public void print(int[] array) {
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(array[i]);
            }
        }

        public void printLine(int[] array) {
            print(array);
            writer.println();
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
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

