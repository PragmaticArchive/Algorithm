import java.io.*;
import java.util.*;

public class Main {
    static int N, K, C;
    static long ans, MAX_VAL;
    static long[] cook;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cook = new long[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            cook[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(cook);
        MAX_VAL = K * cook[N - 1];
        ans = MAX_VAL;
        int biggerThanOne;
        for (biggerThanOne = 0; biggerThanOne < N; biggerThanOne++) {
            if (cook[biggerThanOne] > 1) break;
        }
        encourage(biggerThanOne, 0);
        System.out.println(ans);
    } // end of main

    private static void encourage(int start, int cnt) {
        searchTime();
        if (cnt == C) return;
        for (int i = start; i < N; i++) {
            if (cook[i] > 1) {
                cook[i]--;
                encourage(i, cnt + 1);
                cook[i]++;
            }
        }
    }

    private static void searchTime() {
        long left = 1L;
        long right = MAX_VAL;
        long time = MAX_VAL;
        while (left <= right) {
            long mid = (right + left) >> 1;
            if (cntFood(mid) >= K) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        ans = Math.min(ans, time);
    }

    private static long cntFood(long time) {
        long complete = 0;
        for (int i = 0; i < N; i++) {
            complete += time / cook[i];
        }
        return complete;
    }

} // end of class
