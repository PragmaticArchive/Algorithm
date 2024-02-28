import java.util.*;
import java.io.*;

public class leehyeonjin {

	static List<List<Character>> numbers = List.of(
		List.of('Z', 'E', 'R', 'O'),
		List.of('O', 'N', 'E'),
		List.of('T', 'W', 'O'),
		List.of('T', 'H', 'R', 'E', 'E'),
		List.of('F', 'O', 'U', 'R'),
		List.of('F', 'I', 'V', 'E'),
		List.of('S', 'I', 'X'),
		List.of('S', 'E', 'V', 'E', 'N'),
		List.of('E', 'I', 'G', 'H', 'T'),
		List.of('N', 'I', 'N', 'E')
	);
	static int[] counts, temps;
	static boolean isDone;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// input
			counts = new int[26];
			for (char ch : br.readLine().toCharArray()) {
				counts[ch - 'A']++;
			}

			// solution & output
			sb.append("Case #").append(t).append(": ");

			temps = new int[26];
			isDone = false;
			dfs("");

			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(String str) {
		if (isSame()) {
			sb.append(str);
			isDone = true;
			return;
		}

		for (int i = 0; i <= 9; i++) {
			List<Character> number = numbers.get(i);

			for (int j = 0; j < number.size(); j++) temps[number.get(j) - 'A']++;
			if (isPossible()) {
				dfs(str + i);
			}
			if (isDone) return;
			for (int j = 0; j < number.size(); j++) temps[number.get(j) - 'A']--;
		}
	}

	static boolean isPossible() {
		for (int i = 0; i < 26; i++) {
			if (counts[i] < temps[i]) return false;
		}
		return true;
	}

	static boolean isSame() {
		for (int i = 0; i < 26; i++) {
			if (counts[i] != temps[i]) return false;
		}
		return true;
	}
}
