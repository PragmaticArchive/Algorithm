import java.io.*;
import java.util.*;

// dfs
// Room(정보, 연결된 방목록)
// 레프리콘 : 모험가 소지금 < 숫자: 소지금=숫자
// 트롤: 모험가 소지금 < 숫자 : 못지나감 / >= -= 숫자
// 방 도착 -> 방정보체크(E,T,L) -> 방목록돌기
public class Main {
    static class Room {
        char info;
        int cost;
        List<Integer> doors = new ArrayList<>();
    }

    private static Room[] rooms;
    private static boolean[] visited;
    private static boolean ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n, door;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            rooms = new Room[n + 1];
            visited = new boolean[n + 1];
            ans = false;
            for (int i = 1; i <= n; i++) {
                rooms[i] = new Room();
                st = new StringTokenizer(br.readLine(), " ");
                rooms[i].info = st.nextToken().charAt(0);
                rooms[i].cost = Integer.parseInt(st.nextToken());
                while ((door = Integer.parseInt(st.nextToken())) != 0) {
                    rooms[i].doors.add(door);
                }
            }
            dfs(1, 0, n);
            sb.append(ans ? "Yes" : "No").append("\n");
        }
        System.out.println(sb);
    } // end of main

    private static void dfs(int location, int cost, int n) {
        if(ans)return;
        if (location == n) {
            ans = true;
            return;
        }
        for (int door : rooms[location].doors) {
            if(visited[door])continue;
            int nxtCost = cost;
            switch (rooms[door].info) {
                case 'L':
                    if (cost < rooms[door].cost)
                        nxtCost = rooms[door].cost;
                    break;
                case 'T':
                    if (cost >= rooms[door].cost)
                        nxtCost -= rooms[door].cost;
                    else return;
            }
            visited[door]=true;
            dfs(door, nxtCost, n);
            visited[door]=false;
        }
    }
} // end of class
