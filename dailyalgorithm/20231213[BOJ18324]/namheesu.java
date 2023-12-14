import java.io.*;
import java.util.*;

public class Main {
    private static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int X = Integer.parseInt(br.readLine());
            sb.append(running(X)).append("\n");
        }
        System.out.print(sb.toString());
    } // end of main

    private static int running(int X) {
        int distance = 0;
        int speed=0;
        int ans=0;

        for (speed=1; speed <= X && distance < K ; speed++) {
            distance += speed;
            ans++;
        }

        speed-=1;

        while(distance<K){
            ans++;
            distance+=speed;
            if(distance>=K)break;
            ans++;
            distance+=++speed;
        }
        return ans;
    }
} // end of class
