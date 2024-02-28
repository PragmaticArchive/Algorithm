import java.util.*;
import java.io.*;

public class leehyeonjin {
	static String W;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// input
			W = br.readLine();
			K = Integer.parseInt(br.readLine());

			// solution
			List<Integer> lens = new ArrayList<>();

			// 2가지 조건을 만족하는 문자열 길이 저장
			for (int i = 0; i < W.length(); i++) {
				int cnt = 0;
				for (int j = i; j < W.length(); j++) {
					if (W.charAt(i) == W.charAt(j)) {
						cnt++;
					}
					if (cnt == K) {
						lens.add(j - i + 1);
						break;
					}
				}
			}

			// 저장된 길이들 중 가장 짧은 길이, 가장 긴 길이 도출 & output
			Collections.sort(lens);
			if (lens.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(lens.get(0)).append(" ").append(lens.get(lens.size()-1)).append("\n");
			}
		}

		System.out.println(sb);
	}
}
