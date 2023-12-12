import java.io.*;
import java.util.*;

public class Main {
	private static ArrayList<Integer>[] graph;
	private static int N, yoon, p1, p2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		st = new StringTokenizer(br.readLine(), " ");
		yoon = Integer.parseInt(st.nextToken());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());

		System.out.println(bfs() ? "YES" : "NO");
	} // end of main

	private static boolean bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		int[] visited = new int[N + 1];
		visited[yoon] = 1;
		visited[p1] = visited[p2] = 2;
		queue.offer(new int[] {p1,2});
		queue.offer(new int[] {p2,2});
		queue.offer(new int[] {yoon,1});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[1]==1) {
				if(graph[cur[0]].size()==1) return true;
				if(visited[cur[0]]==2) continue;
			}
			for(int nxt:graph[cur[0]]) {
				if(visited[nxt]==0) {
					visited[nxt]=cur[1];
					queue.offer(new int[] {nxt,cur[1]});
				}
			}
		}
		return false;
	}
} // end of class
