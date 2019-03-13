package on2019_02.on2019_02_07_Educational_Codeforces_Round_2.E___Lomsat_gelral;



import net.egork.collections.Pair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

public class TaskE {
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
        for(int i = 0 ; i < n ; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < n - 1; i++){
            int a = in.readInt()-1;
            int b=  in.readInt()-1;
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1, colorAry, g, colorsMap,totalColorSums, totalColorCounts);
        out.printLine(totalColorSums);
    }

    private void dfs(int current, int parentNode, int[] colorAry,  ArrayList<Integer>[] g,Map<Integer, Integer>[] colorsMap,  long[] totalColorSums,  int[] totalColorCounts) {
        int currentColor = colorAry[current];
        Map<Integer, Integer> currentNodeColorCount = new HashMap<>();
        currentNodeColorCount.put(currentColor, 1);
        int count = 1;
        long sum = currentColor;
        for (int next : g[current]) {
            if (next == parentNode ) {
                continue;
            }
            //dfs to all connected nodes
            dfs(next, current, colorAry, g, colorsMap, totalColorSums, totalColorCounts);
            Map<Integer, Integer> connectedNodeColorCount = colorsMap[next];
            if (connectedNodeColorCount.size() > currentNodeColorCount.size()) {// merge small to large
                connectedNodeColorCount = currentNodeColorCount;
                currentNodeColorCount = colorsMap[next];// move to next
                count = totalColorCounts[next];
                sum = totalColorSums[next];
            }

            for (Map.Entry<Integer, Integer> connectedNode : connectedNodeColorCount.entrySet()) { //merge color and counts
                int connectedNodeColor = connectedNode.getKey();
                int connectedNodeCount = connectedNode.getValue();
                currentNodeColorCount.put(connectedNodeColor,  currentNodeColorCount.getOrDefault(connectedNodeColor, 0) +connectedNodeCount); // update current node's color count
                if (count < currentNodeColorCount.get(connectedNodeColor)) { // if total color is less than the current color count, in other words, we found larger count of some color.
                    count = currentNodeColorCount.get(connectedNodeColor);// then we need to update the current max color and count.
                    sum = connectedNodeColor;
                }
               else if (count == currentNodeColorCount.get(connectedNodeColor)){// if the total color is same to current color count, in other words,  two color has same color count and the count is the largest count.
                    sum += connectedNodeColor;// we need to add two color together.
                }
            }
        }

        colorsMap[current] = currentNodeColorCount;
        totalColorCounts[current] = count;
        totalColorSums[current] = sum;
    }
}
