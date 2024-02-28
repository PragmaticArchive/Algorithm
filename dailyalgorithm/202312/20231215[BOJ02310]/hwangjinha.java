import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static char[] infos;
    static int[] nums;
    static boolean[] visited;
    static boolean ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while ((n = Integer.parseInt(br.readLine())) != 0) {
            graph = new ArrayList[n+1];
            infos = new char[n+1];
            nums = new int[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                String info = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                infos[i] = info.charAt(0);
                nums[i] = num;

                while (st.hasMoreTokens()) {
                    num = Integer.parseInt(st.nextToken());
                    if (num == 0)
                        break;
                    else if (num == i)
                        continue;
                    graph[i].add(num);
                }
            }

            visited = new boolean[n+1];
            ans = false;
            dfs(1, 0);
            System.out.println(ans? "Yes":"No");
        }
    }

    private static void dfs(int node, int money) {
        // 현재 칸에 올 수 있는가 검사
        if (infos[node] == 'L') {
            money = Math.max(money, nums[node]);
        }
        else if (infos[node] == 'T') {
            if (money < nums[node])
                return;
            else
                money -= nums[node];
        }
        // 마지막 칸이었는가 검사
        if (node == n) {
            ans = true;
            return;
        }
        // 다음 칸 검사
        visited[node] = true;
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (!visited[next])
                dfs(next, money);
            if (ans)
                return;
        }
        visited[node] = false;
    }
}