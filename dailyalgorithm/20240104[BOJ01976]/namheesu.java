import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().charAt(0) == '1') {
                    union(parent, i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        System.out.println(travel(st, parent, M));

    } // end of main

    private static String travel(StringTokenizer st, int[] parent, int M) {
        int pre = Integer.parseInt(st.nextToken());
        int target;
        for (int i = 1; i < M; i++) {
            target = Integer.parseInt(st.nextToken());
            if (isNotRoute(parent, target, pre)) return "NO";
            pre = target;
        }
        return "YES";
    }

    private static boolean isNotRoute(int[] parent, int target, int pre) {
        return find(parent, target) != find(parent, pre);
    }

    private static void union(int[] parent, int a, int b) {
        int aParent = find(parent, a);
        int bParent = find(parent, b);
        if (aParent < bParent) parent[b] = aParent;
        else parent[aParent] = bParent;
    }

    static int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent, parent[node]);
    }
} // end of class
