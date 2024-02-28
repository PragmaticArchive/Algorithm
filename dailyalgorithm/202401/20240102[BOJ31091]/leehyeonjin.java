import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N;
	static List<Integer> upper = new ArrayList<>();
	static List<Integer> lower = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int in = Integer.parseInt(st.nextToken());
			if (in <= 0) lower.add(Math.abs(in));
			else upper.add(in);
		}

		// solution
		Collections.sort(upper);
		Collections.sort(lower);

		List<Integer> res = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			int cntUpper = UPPER_BOUND(0, upper.size(), i);
			int cntLower = LOWER_BOUND(0, lower.size(), i);
			if (cntUpper + cntLower == i) {
				res.add(i);
			}
		}

		// output
		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append("\n");
		for (int i = 0; i < res.size(); i++) sb.append(res.get(i)).append(" ");
		System.out.println(sb);
	}

	static int UPPER_BOUND(int l, int r, int target) {
		while (l < r) {
			int m = (l + r) / 2;
			if (upper.get(m) <= target) l = m + 1;
			else r = m;
		}
		return upper.size() - r;
	}

	static int LOWER_BOUND(int l, int r, int target) {
		while (l < r) {
			int m = (l + r) / 2;
			if (lower.get(m) < target) l = m + 1;
			else r = m;
		}
		return l;
	}
}
