import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] developers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        int left = 0, right = N - 1;
        while (left < right) {
            int score = (right - left - 1) * Math.min(developers[left], developers[right]);
            ans = Math.max(ans, score);
            if (developers[left] < developers[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(ans);
    } // end of main
} // end of class
