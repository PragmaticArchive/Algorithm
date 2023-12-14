import java.io.*;
import java.util.*;

public class Main {
    private static int L, N;
    private static char[] S;
    private static ArrayList<Integer> buffer = new ArrayList<>();

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
        // w,h구역 나누기
        for (int i = 0; i < L; i++) {
            if (buffer.isEmpty()) {
                if (S[i] == 'w') {
                    buffer.add(1);
                } else {
                    sb.append(S[i]);
                }
            } else {
                if (S[i] == 'w' || S[i] == 'h')
                    section(S[i]);
                else {
                    adjustSection();
                    addAns(sb);
                    buffer.clear();
                    i -= 1;
                }
            }
        }
        if(!buffer.isEmpty()){
            adjustSection();
            addAns(sb);
        }
        System.out.println(sb.toString());
    }

    private static void addAns(StringBuilder sb) {
        for (int j = 0; j < buffer.size(); j++) {
            if (j % 2 == 0) {
                repeat(j, 'w', sb);
            } else {
                repeat(j, 'h', sb);
            }
        }
    }

    private static void repeat(int j, char c, StringBuilder sb) {
        for (int k = 0; k < buffer.get(j); k++) {
            sb.append(c);
        }
    }

    private static void adjustSection() {
        if (buffer.size() % 2 == 1) {
            buffer.add(0);
        }
        int cnt = 0;
        for (int i = 0; i < buffer.size(); i += 2) {
            int cur = buffer.get(i) + cnt;
            if (cur > N) {
                buffer.set(i, cur - N);
                cnt = N;
            } else {
                cnt = cur;
                buffer.set(i, 0);
            }
        }
        buffer.add(cnt);
    }

    private static void section(char c) {
        if (c == 'w') {
            if (buffer.size() % 2 == 1) { // w일 경우
                buffer.set(buffer.size() - 1, buffer.get(buffer.size() - 1) + 1);
            } else { // h일 경우
                buffer.add(1);
            }
        } else if (c == 'h') {
            if (buffer.size() % 2 == 1) {//w일 경우
                buffer.add(1);
            } else {
                buffer.set(buffer.size() - 1, buffer.get(buffer.size() - 1) + 1);
            }
        }
    }
} // end of class
