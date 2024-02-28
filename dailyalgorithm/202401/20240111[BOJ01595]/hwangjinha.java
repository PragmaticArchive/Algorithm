package pragmatic.day240111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1595_북쪽나라의_도로 {
    static int size = 10000 + 1;

    static ArrayList<Node>[] graph = new ArrayList[size];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;

        for (int i = 0; i < size; i++) graph[i] = new ArrayList<>();

        int a = 0, b, w;
        while ((line = br.readLine()) != null && !line.equals("")) {
            st = new StringTokenizer(line);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
            size = Math.max(Math.max(size, a), b);
        }

        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[10000 + 1];
        q.add(new Node(a, 0));
        visited[a] = true;
        Node node1 = new Node(0, 0);
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (node1.w < now.w)
                node1 = now;

            for (Node next : graph[now.v]) {
                if (visited[next.v]) continue;
                q.add(new Node(next.v, now.w + next.w));
                visited[next.v] = true;
            }
        }

        q = new ArrayDeque<>();
        visited = new boolean[10000 + 1];
        q.add(new Node(node1.v, 0));
        visited[node1.v] = true;
        Node node2 = new Node(0, 0);
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (node2.w < now.w)
                node2 = now;

            for (Node next : graph[now.v]) {
                if (visited[next.v]) continue;
                q.add(new Node(next.v, now.w + next.w));
                visited[next.v] = true;
            }
        }

        System.out.println(node2.w);

    }

    static class Node {
        int v, w;
        public Node(int next, int w) {
            this.v = next;
            this.w = w;
        }
    }
}