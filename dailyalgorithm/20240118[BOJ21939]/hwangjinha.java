package pragmatic.day240118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class boj21939_문제_추천_시스템_Version_1 {
    static int n, m;
    static TreeSet<Integer> diffs = new TreeSet<>();
    static TreeSet<Integer>[] diffProbs = new TreeSet[101];
    static int[] probDiffs = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 101; i++)
            diffProbs[i] = new TreeSet<>();

        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int prob = Integer.parseInt(st.nextToken());
            int difficulty = Integer.parseInt(st.nextToken());
            probDiffs[prob] = difficulty;
            diffs.add(difficulty);
            diffProbs[difficulty].add(prob);
        }

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int prob = Integer.parseInt(st.nextToken());
                int difficulty = Integer.parseInt(st.nextToken());

                probDiffs[prob] = difficulty;
                diffProbs[difficulty].add(prob);
                diffs.add(difficulty);
            }
            else if (cmd.equals("recommend")) {
                String cmd2 = st.nextToken();
                if (cmd2.equals("1")) {
                    int diff = diffs.last();
                    int prob = diffProbs[diff].last();
                    sb.append(prob).append("\n");
                } else {
                    int diff = diffs.first();
                    int prob = diffProbs[diff].first();
                    sb.append(prob).append("\n");
                }
            } else {
                int prob = Integer.parseInt(st.nextToken());
                int diff = probDiffs[prob];

                diffProbs[diff].remove(prob);
                if (diffProbs[diff].size() == 0)
                    diffs.remove(diff);
            }
        }
        System.out.println(sb);
    }
}