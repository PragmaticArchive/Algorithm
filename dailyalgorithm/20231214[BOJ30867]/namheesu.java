import java.io.*;
import java.util.*;

public class Main {
    private static int L, N;
    private static char[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        S = br.readLine().toCharArray();
        getHeadache();
    } // end of main

    private static void getHeadache() {
        StringBuilder sb = new StringBuilder();
        int cntW = 0;
        for (char c : S) {
            switch (c) {
                case 'w':
                    cntW++;
                    if (cntW > N) {
                        cntW = N;
                        sb.append('w');
                    }
                    break;
                case 'h':
                    sb.append('h');
                    break;
                default:
                    repeat(cntW, sb);
                    cntW = 0;
                    sb.append(c);
            }
        }
        repeat(cntW, sb);
        System.out.println(sb.toString());
    }

    private static void repeat(int cntW, StringBuilder sb) {
        for (int i = 0; i < cntW; i++) {
            sb.append('w');
        }
    }
} // end of class
