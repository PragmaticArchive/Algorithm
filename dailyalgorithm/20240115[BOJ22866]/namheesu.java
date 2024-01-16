import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_L = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] h = new int[N + 1];
        int[][] ans = new int[N + 1][2]; // [][0] : cnt, [][1] : 가까운 건물의 번호
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
            ans[i][1] = MAX_L;
        }
        int[] stack = new int[N + 1];
        stack[0] = -MAX_L;
        int inx = 0;
        for (int i = 1; i <= N; i++) {
            while (inx != 0 && h[stack[inx]] <= h[i]) {
                inx--;
            }
            ans[i][0] = inx;
            ans[i][1] = stack[inx];
            stack[++inx] = i;
        }
        inx = 0;
        for (int i = N; i > 0; i--) {
            while (inx != 0 && h[stack[inx]] <= h[i]) {
                inx--;
            }
            ans[i][0] += inx;
            if (inx != 0 && i - ans[i][1] > stack[inx] - i) {
                ans[i][1] = stack[inx];
            }
            stack[++inx] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i][0]).append(" ");
            if (ans[i][0] != 0) sb.append(ans[i][1]);
            sb.append("\n");
        }
        System.out.println(sb);
    } // end of main
} // end of class
