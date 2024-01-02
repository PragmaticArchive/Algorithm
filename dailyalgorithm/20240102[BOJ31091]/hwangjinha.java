package pragmatic.day240102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj31091_거짓말 {

    static int n;
    static ArrayList<Integer> over = new ArrayList<>();
    static ArrayList<Integer> below = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a <= 0) {
                below.add(-a);
            } else {
                over.add(a);
            }
        }

        Collections.sort(over);
        Collections.sort(below);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (check(i))
                ans.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (int num : ans)
            sb.append(num).append(" ");

        System.out.println(sb);
    }

    private static boolean check(int lier) {
        int cnt = 0;
        cnt += over.size() - upper_bound(lier);
        cnt += lower_bound(lier);
        return cnt == lier;
    }

    // below 를 검사
    private static int lower_bound(int lier) {
        int l = 0;
        int r = below.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (below.get(m) < lier) l = m + 1;
            else r = m;
        }
        return l;
    }

    // over 를 검사
    private static int upper_bound(int lier) {
        int l = 0;
        int r = over.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (over.get(m) <= lier) l = m + 1;
            else r = m;
        }
        return l;
    }
}