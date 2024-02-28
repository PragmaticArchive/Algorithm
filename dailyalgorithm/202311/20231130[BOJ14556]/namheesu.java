import java.io.*;

public class Main {
	static final int MOD = 1000000009;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long a = 1;
		for (int i = 1; i < n; i++) {
			a = ((2 * i + 1) * a) % MOD;
		}
		System.out.println(a);
	}
}
