import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.math.BigDecimal;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.readInt(); // numbers of days
            int m = in.readInt(); // total number of gadgets
            int k = in.readInt(); // required number of gadgets
            long s = in.readLong(); // number of $

            int[] dollar = in.readIntArray(n);
            int[] pound = in.readIntArray(n);

            Pair<Integer, Integer>[] res = new Pair[k];
            Item[] items = new Item[m];
            for (int i = 0; i < m; i++) {
                items[i] = new Item();
                items[i].type = in.readInt();
                items[i].cost = in.readInt();
                items[i].pos = i;
            }
            int start = 0;
            int end = n - 1;
            int minDay = -1;
            while (end >= start) {
                int mid = start + (end - start) / 2;
                if (canDo(mid, dollar, m, k, s, pound, items, res)) {
                    minDay = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (minDay == -1) {
                out.printLine("-1");
            } else {
                out.printLine(minDay + 1);
                for (int i = 0; i < k; i++) {
                    out.printLine((res[i].first + 1) + " " + (res[i].second + 1));
                }
            }
        }

        private boolean canDo(int cur, int[] dollar, int m, int k, long s, int[] pound, Item[] items, Pair<Integer, Integer>[] res) {
            int dayD = -1;
            int dayP = -1;
            int minD = Integer.MAX_VALUE;
            int minP = Integer.MAX_VALUE;

            for (int i = 0; i <= cur; i++) {
                if (dollar[i] < minD) {
                    minD = dollar[i];
                    dayD = i;
                }
                if (pound[i] < minP) {
                    minP = pound[i];
                    dayP = i;
                }
            }

            for (int i = 0; i < m; i++) {
                if (items[i].type == 1) {
                    items[i].need = BigDecimal.valueOf(minD).multiply(BigDecimal.valueOf(items[i].cost));
                } else {
                    items[i].need = BigDecimal.valueOf(minP).multiply(BigDecimal.valueOf(items[i].cost));
                }
            }
            Arrays.sort(items); // sort by the required burles
            BigDecimal sum = new BigDecimal(0);
            for (int i = 0; i < k; i++) {
                sum = sum.add(items[i].need);
            }
            if (sum.compareTo(new BigDecimal(s)) <= 0) {
                for (int i = 0; i < k; i++) {
                    if (items[i].type == 1) {
                        res[i] = Pair.makePair(items[i].pos, dayD);
                    } else {
                        res[i] = Pair.makePair(items[i].pos, dayP);
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        class Item implements Comparable<Item> {
            int type;
            int cost;
            int pos;
            BigDecimal need;

            public int compareTo(Item o) {
                return need.compareTo(o.need);
            }

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

        public void printLine(int i) {
            writer.println(i);
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

        public int[] readIntArray(int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = readInt();
            }
            return array;
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

        public long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
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

    static class Pair<U, V> implements Comparable<Pair<U, V>> {
        public final U first;
        public final V second;

        public static <U, V> Pair<U, V> makePair(U first, V second) {
            return new Pair<U, V>(first, second);
        }

        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Pair pair = (Pair) o;

            return !(first != null ? !first.equals(pair.first) : pair.first != null) &&
                    !(second != null ? !second.equals(pair.second) : pair.second != null);
        }

        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        public String toString() {
            return "(" + first + "," + second + ")";
        }

        public int compareTo(Pair<U, V> o) {
            int value = ((Comparable<U>) first).compareTo(o.first);
            if (value != 0) {
                return value;
            }
            return ((Comparable<V>) second).compareTo(o.second);
        }

    }
}

