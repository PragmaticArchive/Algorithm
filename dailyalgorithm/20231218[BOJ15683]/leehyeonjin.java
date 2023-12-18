import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, M;
	static int[][] map;
	static List<CCTV> CCTVs = new ArrayList<>();
	static int[][][] moves = { // 타입, 방향, 이동
		{},
		{ {-1,0,0,0}, {0,0,0,-1}, {0,-1,0,0}, {0,0,-1,0} },
		{ {-1,-1,0,0}, {0,0,-1,-1} },
		{ {-1,0,0,-1}, {0,-1,0,-1}, {0,-1,-1,0}, {-1,0,-1,0} },
		{ {-1,0,-1,-1}, {-1,-1,0,-1}, {0,-1,-1,-1}, {-1,-1,-1,0} },
		{ {-1,-1,-1,-1} }
	};
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] >= 1 && map[r][c] <= 5) {
					CCTVs.add(new CCTV(r, c, map[r][c]));
				}
			}
		}

		// solution
		DFS(0);

		// output
		System.out.println(res);
	}

	static void DFS(int depth) {
		if (depth == CCTVs.size()) {
			res = Math.min(res, count());
			return;
		}

		// depth 번째 카메라의 방향 지정
		CCTV cctv = CCTVs.get(depth);
		int idxDirection = 4;
		if (cctv.type == 2) idxDirection = 2;
		else if (cctv.type == 5) idxDirection = 1;
		for (int direction = 0; direction < idxDirection; direction++) {
			cctv.direction = direction;

			// 다음 넘겨줄 친구 깊이 우선 탐색
			DFS(depth + 1);
		}
	}

	static int count() {
		int[][] copied = copyMap();

		// 감시구역 표시
		for (CCTV cctv : CCTVs) {
			int[] move = moves[cctv.type][cctv.direction];
			// UP
			for (int c = cctv.c, r = cctv.r; r >= 0; r--) {
				if (copied[r][c] == 6) break;
				else if (copied[r][c] >= 1 && copied[r][c] <= 5) continue;
				copied[r][c] += move[0];
			}
			// DOWN
			for (int c = cctv.c, r = cctv.r; r < N; r++) {
				if (copied[r][c] == 6) break;
				else if (copied[r][c] >= 1 && copied[r][c] <= 5) continue;
				copied[r][c] += move[1];
			}
			// LEFT
			for (int r = cctv.r, c = cctv.c; c >= 0; c--) {
				if (copied[r][c] == 6) break;
				else if (copied[r][c] >= 1 && copied[r][c] <= 5) continue;
				copied[r][c] += move[2];
			}
			// RIGHT
			for (int r = cctv.r, c = cctv.c; c < M; c++) {
				if (copied[r][c] == 6) break;
				else if (copied[r][c] >= 1 && copied[r][c] <= 5) continue;
				copied[r][c] += move[3];
			}
		}

		// 감시 피하는 구역 카운팅
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copied[r][c] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	static int[][] copyMap() {
		int[][] copiedMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copiedMap[r][c] = map[r][c];
			}
		}
		return copiedMap;
	}

	static class CCTV {
		int r, c, type, direction;

		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.direction = 0;
		}
	}
}
