import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N;
	static int[] values;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		values = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) values[i] = Integer.parseInt(st.nextToken());

		// solution
		int l = 0, r = N-1, res = -1;

		while (l < r) {
			int value = (r-l-1) * Math.min(values[l], values[r]);
			res = Math.max(res, value);

			if (values[l] < values[r]) {
				l++;
			}
			else if (values[l] > values[r]) {
				r--;
			}
			else {
				if (values[l]-values[l+1] < values[r]-values[r-1]) {
					r--;
				}
				else {
					l++;
				}
			}
		}

		// output
		System.out.println(res);
	}
}
