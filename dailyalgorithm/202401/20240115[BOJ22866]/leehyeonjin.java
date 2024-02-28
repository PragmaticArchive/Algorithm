import java.sql.Statement;
import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int N;
	static int[] hei;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		hei = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) hei[i] = Integer.parseInt(st.nextToken());

		// solution
		int[][] nears = new int[N][2];
		for (int i = 0; i < N; i++) Arrays.fill(nears[i], 100001);
		int[] counts = new int[N];

		Stack<int[]> left = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!left.isEmpty() && left.peek()[0] <= hei[i]) {
				left.pop();
			}
			counts[i] += left.size();
			if (!left.isEmpty()) {
				int dist = Math.abs(left.peek()[1] - i);
				if (dist < nears[i][1]) {
					nears[i][0] = left.peek()[1];
					nears[i][1] = dist;
				}
				else if (dist == nears[i][1] && left.peek()[1] < nears[i][0]) {
					nears[i][0] = left.peek()[1];
				}
			}
			left.push(new int[] {hei[i], i});
		}

		Stack<int[]> right = new Stack<>();
		for (int i = N-1; i >= 0; i--) {
			while (!right.isEmpty() && right.peek()[0] <= hei[i]) {
				right.pop();
			}
			counts[i] += right.size();
			if (!right.isEmpty()) {
				int dist = Math.abs(right.peek()[1] - i);
				if (dist < nears[i][1]) {
					nears[i][0] = right.peek()[1];
					nears[i][1] = dist;
				}
				else if (dist == nears[i][1] && right.peek()[1] < nears[i][0]) {
					nears[i][0] = right.peek()[1];
				}
			}
			right.push(new int[] {hei[i], i});
		}

		// output
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (counts[i] == 0) {
				sb.append(counts[i]).append("\n");
			}

			else {
				sb.append(counts[i]).append(" ").append(nears[i][0]+1).append("\n");
			}
		}

		System.out.println(sb);
	}
}
