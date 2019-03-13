import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.readInt();
            int[] res = new int[n];
            int[] colorAry = in.readIntArray(n);
            //the number of occurences for each colour for each vertexs
            Map<Integer, Integer>[] colorsMap = new Map[n];
            //total count of sums for each vertexs(result)
            long[] totalColorSums = new long[n];
            // total count for each color
            int[] totalColorCounts = new int[n];
            //read graph
            ArrayList<Integer>[] g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int i = 0; i < n - 1; i++) {
                int a = in.readInt() - 1;
                int b = in.readInt() - 1;
                g[a].add(b);
                g[b].add(a);
            }
            dfs(0, -1, colorAry, g, colorsMap, totalColorSums, totalColorCounts);
            out.printLine(totalColorSums);
        }

        private void dfs(int current, int parentNode, int[] colorAry, ArrayList<Integer>[] g, Map<Integer, Integer>[] colorsMap, long[] totalColorSums, int[] totalColorCounts) {
            int currentColor = colorAry[current];
            Map<Integer, Integer> currentNodeColorCount = new HashMap<>();
            currentNodeColorCount.put(currentColor, 1);
            int count = 1;
            long sum = currentColor;
            for (int next : g[current]) {
                if (next == parentNode) {
                    continue;
                }
                //dfs to all connected nodes
                dfs(next, current, colorAry, g, colorsMap, totalColorSums, totalColorCounts);
                Map<Integer, Integer> connectedNodeColorCount = colorsMap[next];
                if (connectedNodeColorCount.size() > currentNodeColorCount.size()) {// if we are in lead but the data is still previous node's data
                    connectedNodeColorCount = currentNodeColorCount;
                    currentNodeColorCount = colorsMap[next];// move to next
                    count = totalColorCounts[next];
                    sum = totalColorSums[next];
                }

                for (Map.Entry<Integer, Integer> connectedNode : connectedNodeColorCount.entrySet()) {
                    int connectedNodeColor = connectedNode.getKey();
                    int connectedNodeCount = connectedNode.getValue();
                    currentNodeColorCount.put(connectedNodeColor, currentNodeColorCount.getOrDefault(connectedNodeColor, 0) + connectedNodeCount); // update current node's color count
                    if (count < currentNodeColorCount.get(connectedNodeColor)) { // if total color is less than the current color count, in other words, we found larger count of some color.
                        count = currentNodeColorCount.get(connectedNodeColor);// then we need to update the current max color and count.
                        sum = connectedNodeColor;
                    } else if (count == currentNodeColorCount.get(connectedNodeColor)) {// if the total color is same to current color count, in other words,  two color has same color count and the count is the largest count.
                        sum += connectedNodeColor;// we need to add two color together.
                    }
                }
            }

            colorsMap[current] = currentNodeColorCount;
            totalColorCounts[current] = count;
            totalColorSums[current] = sum;
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

        public void print(long[] array) {
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(array[i]);
            }
        }

        public void printLine(long[] array) {
            print(array);
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

