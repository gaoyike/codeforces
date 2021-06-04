package on2021_06.on2021_06_04_Educational_Codeforces_Round_110__Rated_for_Div__2_.A___Fair_Playoff;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for (int i = 0; i < k; i++) {
            solve(in.readIntArray(4));
        }
    }
    private void solve(int[] arr){
        int[] copy = Arrays.copyOf(arr, 4);
        Arrays.sort(copy);
        int a = Math.max(arr[0], arr[1]);
        int b = Math.max(arr[2], arr[3]);
        if ((a == copy[2] && b == copy[3]) || (b == copy[2] && a == copy[3]))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
