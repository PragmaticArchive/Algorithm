package pragmatic.day240109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj6597_트리_복구 {
    static char[] a;
    static char[] b;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            a = st.nextToken().toCharArray();
            b = st.nextToken().toCharArray();

            solution();
            ans.append("\n");
        }
        System.out.print(ans);
    }

    private static void solution() {
        dq(0, a.length, 0, a.length);
    }

    private static void dq(int startA, int endA, int startB, int endB) {
        if (endA - startA == 0) {
            return;
        }
        if (endA - startA == 1) {
            ans.append(a[startA]);
            return;
        }
        int bi = find(startB, endB, a[startA]);

        // 왼쪽 길이 startB ~ bi 오른쪽 길이 bi + 1 ~ endB
        dq(startA + 1, startA + 1 + bi - startB, startB, bi);
        dq(startA + 1 + bi - startB, endA, bi + 1, endB);
        ans.append(a[startA]);
    }

    private static int find(int startB, int endB, char c) {
        for (int i = startB; i < endB; i++)
            if (b[i] == c)
                return i;
        return -1;
    }
}