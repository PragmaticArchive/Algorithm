import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] liars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        liars = new int[N + 2];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int range = Integer.parseInt(st.nextToken());
            if (range > 0) {
                liars[range]++;
                continue;
            }
            liars[0]++;
            liars[-range + 1]--;
        }

        for (int i = 1; i <= N; i++) {
            liars[i] += liars[i - 1];
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= N; i++) {
            if (i == N - liars[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    } // end of main
} // end of class
