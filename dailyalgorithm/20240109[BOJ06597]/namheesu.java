import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[] preOrder, inOrder;
    static int preInx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input;
        try {
            while (true) {
                input = br.readLine();
                if (input.length() == 0) break;
                st = new StringTokenizer(input, " ");
                preOrder = st.nextToken().toCharArray();
                inOrder = st.nextToken().toCharArray();
                preInx = 0;
                postOrder(0, preOrder.length - 1);
                sb.append("\n");
            }
        }catch (Exception e){}
        System.out.println(sb);
    } // end of main

    private static void postOrder(int start, int end) {
        if (start > end) return;
        int mid = getInx(start, end);
        postOrder(start, mid - 1);
        postOrder(mid + 1, end);
        sb.append(inOrder[mid]);
    }

    private static int getInx(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == preOrder[preInx]) {
                preInx++;
                return i;
            }
        }
        return 0;
    }
} // end of class
