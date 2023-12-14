import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int K, N;
	static int[] xList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		xList = new int[N];
		for(int i=0; i<N; i++) xList[i] = Integer.parseInt(br.readLine());

		// solution
		StringBuilder sb = new StringBuilder();
		for(int x : xList) sb.append(binarySerach(0, K+1, x)).append("\n");

		// output
		System.out.println(sb.toString());
	}

	static long binarySerach(long l, long r, long target) {
		while(l+1 != r) {
			long mid = (l+r)/2;
			if(check(mid, target)) l = mid;
			else r = mid;
		}
		return getTime(l, target);
	}

	static boolean check(long mid, long target) {
		long front = mid * (mid+1) / 2;
		long back = Math.max(0, (mid-target) * (mid-1+target) / 2);
		return front + back <= K;
	}

	static long getTime(long l, long target) {
		long front = l * (l+1) / 2;
		long back = Math.max(0, (l-target) * (l-1+target) / 2);
		long total = front + back;
		long time = l + Math.max(0, (l - target));
		while(total < K) {
			total += l;
			time++;
		}
		return time;
	}
}
