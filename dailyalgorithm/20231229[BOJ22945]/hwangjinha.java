package pragmatic.day231229;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj22945_팀_빌딩 {

    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = n - 1;

        int ans = -1;
        while (l < r) {
            int c = calc(l, r);
            ans = Math.max(ans, c);

            if (arr[l] < arr[r]) {
                l++;
            } else if (arr[l] > arr[r]) {
                r--;
            } else {
                if (arr[l+1] - arr[l] > arr[r-1] - arr[r])
                    l++;
                else
                    r--;
            }
        }
        System.out.println(ans);
    }

    static int calc(int l, int r){
        return (r - l - 1) * Math.min(arr[l], arr[r]);
    }

}