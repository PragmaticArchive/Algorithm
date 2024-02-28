import java.io.*;
import java.util.*;

public class Main {
    static class City {
        int city;
        int dist;

        public City(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    static int[] ans = new int[2];
    static List<City>[] cities = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringTokenizer st;

        for (int i = 1; i <= 10000; i++) {
            cities[i] = new ArrayList<>();
        }

        int end = -1;
        while ((input = br.readLine()) != null && !input.equals("")) {
            st = new StringTokenizer(input, " ");
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            cities[city1].add(new City(city2, dist));
            cities[city2].add(new City(city1, dist));
            end = Math.max(end, Math.max(city1, city2));
        }
        System.out.println(chkNPE(end));
    } // end of main

    private static int chkNPE(int end) {
        if(end==-1) return 0;
        findMaxDist(1, 0);
        Arrays.fill(visited, false);
        ans[1] = 0;
        findMaxDist(ans[0], 0);
        return ans[1];
    }

    private static void findMaxDist(int city, int dist) {
        if (dist > ans[1]) {
            ans[0] = city;
            ans[1] = dist;
        }
        visited[city] = true;
        for (City nxt : cities[city]) {
            if (!visited[nxt.city]) {
                findMaxDist(nxt.city, dist + nxt.dist);
            }
        }
    }
} // end of class
