import java.io.*;
import java.util.*;

public class leehyeonjin {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		String input = br.readLine() + "a";

		// solution
		StringBuilder res = new StringBuilder();
		List<Integer> buffer = new ArrayList<>(); // [w 개수, h 개수, w 개수, ... h 개수, w 개수] 저장 버퍼
		boolean isPrevBuffer = false;
		int i = 0;

		while (i < input.length()) {
			if (isPrevBuffer) {
				// 버퍼 계속됨
				if (input.charAt(i) == 'w' || input.charAt(i) == 'h') {
					int bufferSize = buffer.size();
					if (input.charAt(i) == 'w' && bufferSize % 2 == 1) { // 이전 w 에 이어서 계속 w
						int cnt = buffer.get(bufferSize - 1);
						buffer.set(bufferSize - 1, cnt + 1);
					}
					else if (input.charAt(i) == 'w' && bufferSize % 2 == 0) { // 이전 h 에 이어서 w
						buffer.add(1);
					}
					else if (input.charAt(i) == 'h' && bufferSize % 2 == 1) { // 이전 w 에 이어서 h
						buffer.add(1);
					}
					else if (input.charAt(i) == 'h' && bufferSize % 2 == 0) { // 이전 h 에 이어서 계속 h
						int cnt = buffer.get(bufferSize - 1);
						buffer.set(bufferSize - 1, cnt + 1);
					}
				}
				// 버퍼 끝남
				else {
					// 버퍼 조절
					int bufferSize = buffer.size();
					if (bufferSize % 2 == 0) {
						buffer.add(0);
						bufferSize++;
					}
					int j = 0, cnt = 0;
					while (j < buffer.size() - 1) {
						int cntNow = buffer.get(j);
						if (cnt + buffer.get(j) > N) {
							buffer.set(j, cntNow - (N - cnt));
							cnt += (N - cnt);
							break;
						}
						else {
							buffer.set(j, 0);
							cnt += cntNow;
						}
						j += 2;
					}
					buffer.set(bufferSize - 1, buffer.get(bufferSize - 1) + cnt);
					// 최종 버퍼 결과를 통해 문자열 생성
					StringBuilder temp = new StringBuilder();
					for (int k = 0; k < bufferSize; k++) {
						if (k % 2 == 0) { // w 더해줌
							for (int t = 0; t < buffer.get(k); t++) {
								temp.append("w");
							}
						}
						else { // h 더해줌
							for (int t = 0; t < buffer.get(k); t++) {
								temp.append("h");
							}
						}
					}
					res.append(temp);
					// 버퍼 초기화
					buffer = new ArrayList<>();
					isPrevBuffer = false;
					i -= 1;
				}
			}
			else {
				// 버퍼 시작됨
				if (input.charAt(i) == 'w') {
					buffer.add(1);
					isPrevBuffer = true;
				}
				// 그냥 결과에 추가
				else {
					res.append(input.charAt(i));
				}
			}
			i++;
		}

		// output
		System.out.println(res.substring(0, L));
	}
}
