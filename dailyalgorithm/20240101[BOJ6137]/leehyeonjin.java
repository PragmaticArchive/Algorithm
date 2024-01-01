import java.io.*;
import java.util.*;

public class leehyeonjin {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder();
		StringBuilder T = new StringBuilder();

		// input
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) S.append(br.readLine());

		// solution
		int l = 0, r = N-1, cnt = 0;
		while (l <= r) {
			if (cnt != 0 && cnt % 80 == 0) T.append("\n");

			if (S.charAt(l) < S.charAt(r)) {
				T.append(S.charAt(l++));
				cnt++;
			}
			else if (S.charAt(l) > S.charAt(r)) {
				T.append(S.charAt(r--));
				cnt++;
			}
			else {
				int nl = l, nr = r, lenT = T.length();
				while (++nl <= --nr) {
					if (S.charAt(nl) < S.charAt(nr)) {
						T.append(S.charAt(l++));
						cnt++;
						break;
					}
					else if (S.charAt(nl) > S.charAt(nr)) {
						T.append(S.charAt(r--));
						cnt++;
						break;
					}
				}
				// 늘어난게 하나도 없는경우
				if (lenT == T.length()) {
					T.append(S.charAt(l++));
					cnt++;
				}
			}
		}

		// output
		System.out.println(T);
	}
}
