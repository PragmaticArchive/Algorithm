import java.io.*;
import java.util.*;

public class Main {
    private static int N, minDiff = Integer.MAX_VALUE, maxSum = 0;
    private static int[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            students[i] = Integer.parseInt(st.nextToken()) + students[i - 1];
        }
        startEvent();
    } // end of main

    private static void startEvent() {
        for (int mid = 1; mid < N; mid++) {
            group(mid, mid, mid + 1);
        }
        System.out.println(maxSum);
    }

    private static void group(int start, int mid, int end) {
        while (1 <= start && end <= N) {
            int diff = groupSum(start, mid) - groupSum(mid + 1, end);
            if (minDiff > Math.abs(diff)) {
                minDiff = Math.abs(diff);
                maxSum = groupSum(start, mid) + groupSum(mid + 1, end);
            } else if (minDiff == Math.abs(diff)) {
                if (maxSum < groupSum(start, mid) + groupSum(mid + 1, end)) {
                    maxSum = groupSum(start, mid) + groupSum(mid + 1, end);
                }
            }
            if (diff > 0) {
                end++;
            } else {
                start--;
            }
        }
    }

    private static int groupSum(int start, int end) {
        return students[end] - students[start - 1];
    }
} // end of class
