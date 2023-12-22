import java.io.*;
import java.util.*;

public class Main {
    static int N, k;

    static class Node {
        int inx;
        int dir;
        int cnt;

        public Node(int inx, int dir, int cnt) {
            this.inx = inx;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        char[][] arr = new char[2][N];
        arr[0] = br.readLine().toCharArray();
        arr[1] = br.readLine().toCharArray();
        System.out.println(bfs(arr) ? 1 : 0);
    } // end of main

    private static boolean bfs(char[][] arr) {
        int clock = -1;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.cnt != clock) {
                clock++;
                if(clock == N) return false;
                // 한칸씩 사라지기
                for (int i = 0; i < 2; i++) {
                    arr[i][clock] = '0';
                }
            }
            if (cur.inx + 1 >= N || cur.inx + k >= N) return true;
            if (arr[cur.dir][cur.inx + 1] != '0') {
                arr[cur.dir][cur.inx + 1] = '0';
                queue.offer(new Node(cur.inx + 1, cur.dir, cur.cnt + 1));
            }
            if (cur.inx - 1 >= 0 && arr[cur.dir][cur.inx - 1] != '0') {
                arr[cur.dir][cur.inx - 1] = '0';
                queue.offer(new Node(cur.inx - 1, cur.dir, cur.cnt + 1));
            }
            if (arr[(cur.dir + 1) % 2][cur.inx + k] != '0') {
                arr[(cur.dir + 1) % 2][cur.inx + k] = '0';
                queue.offer(new Node(cur.inx + k, (cur.dir + 1) % 2, cur.cnt + 1));
            }
        }
        return false;
    }
} // end of class
