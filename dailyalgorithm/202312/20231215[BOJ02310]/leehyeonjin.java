import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int n;
	static List<Room> rooms;
	static boolean res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;

			// input
			res = false;
			rooms = new ArrayList<>();
			rooms.add(new Room("X", -1, new ArrayList<>()));
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String type = st.nextToken();
				int cost = Integer.parseInt(st.nextToken());
				List<Integer> nexts = new ArrayList<>();
				while (st.hasMoreTokens()) {
					int next = Integer.parseInt(st.nextToken());
					if (next == 0) break;
					nexts.add(next);
				}
				rooms.add(new Room(type, cost, nexts));
			}

			// solution
			DFS(1, 0, new boolean[n+1]);

			// output
			sb.append(res ? "Yes" : "No").append("\n");
		}

		System.out.println(sb.toString());
	}

	static void DFS(int roomNum, int remainCost, boolean[] visited) {
		if (res) return;
		if (roomNum == n) {
			res = true;
			return;
		}

		for (int nextNum : rooms.get(roomNum).nexts) {
			if (visited[nextNum]) continue;
			Room next = rooms.get(nextNum);
			if (next.type.equals("T")) {
				if (next.cost > remainCost) {
					return;
				}
				else {
					visited[nextNum] = true;
					DFS(nextNum, remainCost - next.cost, visited);
				}
			}
			else if (next.type.equals("L")) {
				if (next.cost > remainCost) {
					visited[nextNum] = true;
					DFS(nextNum, next.cost, visited);
				}
				else {
					visited[nextNum] = true;
					DFS(nextNum, remainCost, visited);
				}
			}
			else {
				visited[nextNum] = true;
				DFS(nextNum, remainCost, visited);
			}
		}
	}

	static class Room {
		String type;
		int cost;
		List<Integer> nexts;

		public Room (String type, int cost, List<Integer> nexts) {
			this.type = type;
			this.cost = cost;
			this.nexts = nexts;
		}
	}
}
