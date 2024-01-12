import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int N;
	static String[] words;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for (int i = 0; i < N; i++) words[i] = br.readLine();

		// solution
		int max = -1;
		int idxS = 0, idxT = 0;

		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if (words[i].equals(words[j])) continue;
				int cnt = 0;
				int len = Math.min(words[i].length(), words[j].length());

				for (int k = 0; k < len; k++) {
					if (words[i].charAt(k) != words[j].charAt(k)) break;
					cnt++;
				}

				if (max < cnt) {
					max = cnt;
					idxS = i;
					idxT = j;
				}
			}
		}

		// output
		System.out.println(words[idxS]);
		System.out.println(words[idxT]);
	}
}
