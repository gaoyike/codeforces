package on2021_03.on2021_03_26_GCJ2021.Reversort;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Reversort {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        for(int i = 1 ; i <= k; i++){
            int n = in.readInt();
            int[] arr = in.readIntArray(n);
             out.printLine("Case #" + i +": " + ReverSort(arr));
        }
    }
    private int ReverSort(int[] arr){
        int res = 0;
        for(int i = 0; i < arr.length - 1; i++){
            int min = arr[i];
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            res += minIndex - i + 1;
            rev(arr,i, minIndex);
        }
        return res;
    }

    private void rev(int[] arr, int i, int j){
        while (i < j){
            swap(arr, i++, j--);
        }
    }
    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
