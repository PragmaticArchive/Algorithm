import java.util.*;
import java.io.*;

/**
 * 순회 :
 * - 데이터 처리를 언제 하느냐가 관건!'
 * - L (= L 처리 ) R (= R 처리)
 * - work -> L -> R
 * - L -> work -> R
 * - L -> R -> work
 */
public class leehyeonjin {
	static String preorder, inorder;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String in;
		while ((in = br.readLine()) != null) {
			// input
			StringTokenizer st = new StringTokenizer(in, " ");
			preorder = st.nextToken();
			inorder = st.nextToken();

			// solution
			divideAndConquer(0, preorder.length(), 0, inorder.length());

			// output
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void divideAndConquer(int preStart, int preEnd, int inStart, int inEnd) {
		if (preStart == preEnd) {
			return;
		}

		char mid = preorder.charAt(preStart);
		int idxMid = 0;
		for (int i = 0; i < inorder.length(); i++) {
			if (inorder.charAt(i) == mid) {
				idxMid = i;
			}
		}

		// left
		divideAndConquer(preStart+1, preStart+(idxMid-inStart)+1, inStart, idxMid);
		// right
		divideAndConquer(preStart+(idxMid-inStart)+1, preEnd, idxMid+1, inEnd);
		// mid
		sb.append(mid);
	}
}
