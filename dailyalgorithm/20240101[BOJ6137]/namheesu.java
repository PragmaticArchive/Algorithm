import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static char[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new char[N];
        for (int i = 0; i < N; i++) {
            S[i] = br.readLine().charAt(0);
        }
        makeWord();
    } // end of main

    private static void makeWord() {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = N - 1;
        while (N-- > 0) {
            boolean chk = true;
            for (int i = 0; i < (right - left + 1) >> 1; i++) {
                if (S[left + i] == S[right - i]) continue;
                else if (S[left + i] > S[right - i]) {
                    chk = false;
                }
                break;
            }
            sb.append(chk ? S[left++] : S[right--]);
            // 80글자마다 출력
            if (sb.length() == 80) {
                System.out.println(sb);
                sb.setLength(0);
            }
        }
        System.out.println(sb);
    }
} // end of class
