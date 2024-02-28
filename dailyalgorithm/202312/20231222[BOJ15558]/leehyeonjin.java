import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, K;
	static int[][] line;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		line = new int[2][N+1];
		for (int i = 0; i < 2; i++) {
			String in = br.readLine();
			for (int j = 1; j <= in.length(); j++) {
				if (in.charAt(j-1) == '1') line[i][j] = 1;
				else line[i][j] = 0;
			}
		}

		// solution
		boolean res = BFS();

		// output
		System.out.println(res ? 1 : 0);
	}

	static boolean BFS() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[2][N+1];
		q.offer(new int[] {0, 1, 1}); // 몇번쨰줄인지, 몇번쨰칸인지, 경과시간
		visited[0][1] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int jul = now[0], kan = now[1], sec = now[2];

			// 한칸 앞으로
			int nkan = kan + 1;
			if (nkan > N) return true;
			if (check(jul, nkan)) {
				q.offer(new int[] {jul, nkan, sec+1});
				visited[jul][nkan] = true;
			}

			// 한칸 뒤로
			nkan = kan - 1;
			if (check(jul, nkan)) {
				q.offer(new int[] {jul, nkan, sec+1});
				visited[jul][nkan] = true;
			}

			// 옆줄 K칸 앞으로
			int njul = jul == 0 ? 1 : 0;
			nkan = kan + K;
			if (nkan > N) return true;
			if (check(njul, nkan)) {
				q.offer(new int[] {njul, nkan, sec+1});
				visited[njul][nkan] = true;
			}

			// 시간지남에 따라 칸 사라짐
			if (sec == N) return false;
			visited[0][sec] = true;
			visited[1][sec] = true;
		}

		return false;
	}

	static boolean check(int njul, int nkan) {
		return nkan >= 1 && nkan <= N && !visited[njul][nkan] && line[njul][nkan] != 0;
	}
}
