package pragmatic.day240201;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj24395_명진이의_신년계획 {
    static int students;
    static int pills;
    static final int SIZE = 51;
    static int[][] dp = new int[SIZE][SIZE];
    static int[][] dpNext = new int[SIZE][SIZE];
    static final int NOTVISITED = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        students = Integer.parseInt(st.nextToken());
        pills = Integer.parseInt(st.nextToken());

        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(dp[i], NOTVISITED);
            Arrays.fill(dpNext[i], NOTVISITED);
        }

        dpNext[0][0] = 0;

        for (int i = 0; i < pills; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            makeDP(a, b, c);
        }

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < students; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[] {i + 1, dpNext[a][b]==-1?0:dpNext[a][b]});
        }

        Collections.sort(list, (a, b) -> {
            if (a == b) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int[] student : list) {
            sb.append(student[0]).append(" ").append(student[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeDP(int red, int blue, int level) {
        for (int i = 0; i < SIZE; i++) {
            dp[i] = Arrays.copyOf(dpNext[i], SIZE);
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (dp[i][j] == NOTVISITED) continue;
                int ii = i + red;
                int jj = j + blue;
                if (ii <= 50 && jj <= 50)
                    dpNext[ii][jj] = Math.max(dpNext[ii][jj], dp[i][j] + level);
            }
        }
    }
}