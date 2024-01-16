import java.util.*;
import java.io.*;

public class leehyeonjin {

	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, L, K;
	static List<Pos> positions = new ArrayList<>();
	static int[] rArr, cArr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		rArr = new int[K];
		cArr = new int[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			positions.add(new Pos(r, c));
			rArr[i] = r;
			cArr[i] = c;
		}

		// solution
		int res = Integer.MIN_VALUE;

		for (int r : rArr) {
			for (int c : cArr) {
				int cnt = 0;
				for (Pos pos : positions) {
					if (check(r, r+L, c, c+L, pos)) {
						cnt++;
					}
				}
				res = Math.max(res, cnt);
			}
		}

		// output
		System.out.println(K - res);
	}

	static boolean check(int sr, int er, int sc, int ec, Pos pos) {
		return sr <= pos.r && pos.r <= er && sc <= pos.c && pos.c <= ec;
	}
}
