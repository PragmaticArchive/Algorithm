package pragmatic.day240116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj14658_하늘에서_별똥별이_빗발친다 {

    static int n, m, l, k;
    static List<Point> stars = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            stars.add(new Point(a, b));
        }

        int ans = 0;
        for (Point star1 : stars) {
            int i = star1.y;
            for (Point star2 : stars) {
                int j = star2.x;
                int cnt = 0;
                for (Point star3 : stars) {
                    if (i <= star3.y && star3.y <= i + l && j <= star3.x && star3.x <= j + l)
                        cnt++;

                }
                ans = Math.max(ans, cnt);
            }
        }
        System.out.println(k - ans);
    }
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
