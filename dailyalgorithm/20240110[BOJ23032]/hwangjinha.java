package pragmatic.day240110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj23032_서프라이즈 {

    static int n;
    static int[] w;
    static int[] sums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        w = new int[n];
        sums = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            sums[i + 1] = sums[i] + w[i];
        }

        int min = Integer.MAX_VALUE;
        int steak = -1;
        int a, b, c;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = sums[i] - sums[j];
                int idx = binSearch(j, sums[i] + sum);

                int diff;
                if ((diff = Math.abs(sum - (sums[idx] - sums[i]))) < min){
                    min = diff;
                    steak = sums[idx] - sums[j];
                } else if (diff == min) {
                    steak = Math.max(steak, sums[idx] - sums[j]);
                }
                idx--;
                if (i == idx)
                    continue;
                if ((diff = Math.abs(sum - (sums[idx] - sums[i]))) < min){
                    min = diff;
                    steak = sums[idx] - sums[j];
                } else if (diff == min) {
                    steak = Math.max(steak, sums[idx] - sums[j]);
                }
            }
        }
        System.out.println(steak);
    }

    private static int binSearch(int j, int target) {
        int l = j;
        int r = n;
        int m;
        while (l < r) {
            m = (l + r) / 2;
            if (sums[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}