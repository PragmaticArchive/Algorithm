package pragmatic.day231222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj15558_점프게임 {

    static int n, k;
    static String left;
    static String right;

    static boolean[] visitedL;
    static boolean[] visitedR;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visitedL = new boolean[n + 1];
        visitedR = new boolean[n + 1];

        left = "0" + br.readLine();
        right = "0" + br.readLine();

        System.out.println(bfs());
    }


    private static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> times = new ArrayDeque<>();
        Queue<Boolean> isLeft = new ArrayDeque<>();
        q.add(1);
        visitedL[0] = true;
        times.add(0);
        isLeft.add(true);
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now >= n) return 1;
            boolean l = isLeft.poll();
            int time = times.poll();

            if (now - 1 > time + 1) {
                if (l && left.charAt(now - 1) == '1' && !visitedL[now - 1]) {
                    visitedL[now - 1] = true;
                    q.add(now-1);
                    isLeft.add(l);
                    times.add(time + 1);
                } else if (!l && right.charAt(now - 1) == '1' && !visitedR[now - 1]) {
                    visitedR[now - 1] = true;
                    q.add(now-1);
                    isLeft.add(l);
                    times.add(time + 1);
                }
            }

            if (l && now + 1 <= n && left.charAt(now + 1) == '1' && !visitedL[now + 1]) {
                visitedL[now + 1] = true;
                q.add(now+1);
                isLeft.add(l);
                times.add(time + 1);
            } else if (!l && now + 1 <= n && right.charAt(now + 1) == '1' && !visitedR[now + 1]) {
                visitedR[now + 1] = true;
                q.add(now+1);
                isLeft.add(l);
                times.add(time + 1);
            }

            if (l && now + k <= n && right.charAt(now + k) == '1' && !visitedR[now + k]) {
                visitedR[now + k] = true;
                q.add(now+k);
                isLeft.add(!l);
                times.add(time + 1);
            } else if (!l && now + k <= n && left.charAt(now + k) == '1' && !visitedL[now + k]) {
                visitedL[now + k] = true;
                q.add(now+k);
                isLeft.add(!l);
                times.add(time + 1);
            }
            else if (now + k > n) {
                q.add(now+k);
                isLeft.add(!l);
                times.add(time + k);
            }
        }
        return 0;
    }
}