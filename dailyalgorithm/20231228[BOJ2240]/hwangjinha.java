package pragmatic.day231228;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2240_자두나무 {

    static int t, w;
    static int[][] trees;
    static int[][][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        trees = new int[t][2];
        trees[0][Integer.parseInt(br.readLine())-1] += 1;
        for (int i = 1; i < t; i++) {
            trees[i][Integer.parseInt(br.readLine())-1] += 1;
        }

        memo = new int[t][w+1][2];
        System.out.println(Math.max(
            dfs(0, w, 0),
            dfs(0, w-1, 1)
        ));
    }

    private static int dfs(int time, int moveCnt, int position) {
        if (time == t-1)
            return trees[time][position];
        if (memo[time][moveCnt][position] != 0)
            return memo[time][moveCnt][position];

        int max = dfs(time + 1, moveCnt, position);
        if (moveCnt > 0)
            max = Math.max(max, dfs(time + 1, moveCnt - 1, (position+1)%2));
        return memo[time][moveCnt][position] = max + trees[time][position];
    }

}