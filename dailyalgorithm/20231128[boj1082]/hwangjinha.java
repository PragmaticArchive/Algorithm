package pragmatic.day231128;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1082_방번호 {
    static int n, m;
    static int[] costs;
    static int minCost, minNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        costs = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        minCost = 51;
        minNum = -1;
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            if (costs[i] < minCost) {
                minCost = costs[i];
                minNum = i;
            }
        }
        m = Integer.parseInt(br.readLine());

        boolean isFisrt = true;
        StringBuilder sb = new StringBuilder();
        while (minCost <= m) {
            int maxLength = 0;
            int num = -1;
            int cost = -1;
            for (int i = n-1; i >= 0; i--) {
                if (isFisrt && i == 0) continue;
                if (m >= costs[i]) {
                    int length = (m - costs[i]) / minCost + 1;
                    if (length > maxLength) {
                        maxLength = length;
                        num = i;
                        cost = costs[i];
                    }
                }
            }
            if (num == -1) {
                System.out.println(0);
                System.exit(0);
            }
            sb.append(num);
            isFisrt = false;
            m -= cost;
        }
        System.out.println(sb);
    }
}