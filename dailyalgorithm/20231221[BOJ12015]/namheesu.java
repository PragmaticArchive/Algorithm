import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int[] arr = new int[N+1];
        int len = 0;

        for (int i = 0; i < N; i++) {
            if (arr[len] < nums[i]) {
                arr[++len] = nums[i];
            } else {
                int l = 1;
                int r = len;
                while (l != r) {
                    int mid = (r + l) / 2;
                    if (nums[i] <= arr[mid]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                arr[r] = nums[i];
            }
        }
        System.out.println(len);
    } // end of main
} // end of class
