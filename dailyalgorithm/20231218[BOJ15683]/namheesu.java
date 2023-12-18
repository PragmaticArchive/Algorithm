import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans, blindSpot;
    static int[][][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();

    static class CCTV {
        int type;
        int y;
        int x;

        public CCTV(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = input.charAt(0) - '0';
        M = input.charAt(2) - '0';
        map = new int[N][M][2];
        blindSpot = N * M;
        ans = N * M;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0, inx = 0; j < M; j++, inx += 2) {
                map[i][j][0] = input.charAt(inx) - '0';
                if (map[i][j][0] > 0) {
                    blindSpot--;
                    if (map[i][j][0] < 6) cctvs.add(new CCTV(map[i][j][0], i, j));
                }
            }
        }
        func(0);
        System.out.println(ans);
    } // end of main

    private static void func(int inx) {
        ans = Math.min(ans, blindSpot);
        if (ans==0 || inx == cctvs.size()) {
            return;
        }
        switch (cctvs.get(inx).type) {
            case 1:
                pickDir1(inx);
                break;
            case 2:
                pickDir2(inx);
                break;
            case 3:
                pickDir3(inx);
                break;
            case 4:
                pickDir4(inx);
                break;
            case 5:
                pickDir5(inx);
                break;
        }
    }

    private static void pickDir5(int inx) {
        for (int i = 0; i < 4; i++) {
            spread(inx, i);
        }
        func(inx + 1);
        for (int i = 0; i < 4; i++) {
            rollback(inx, i);
        }
    }

    private static void pickDir4(int inx) {
        int j = 0, k = 0;
        spread(inx, 0);
        spread(inx, 1);
        for (int i = 0; i < 4; i++) {
            j = (i + 1) % 4;
            k = (j + 1) % 4;
            spread(inx, k);
            func(inx + 1);
            rollback(inx, i);
        }
        rollback(inx, j);
        rollback(inx, k);
    }

    private static void pickDir3(int inx) {
        int j = 0;
        spread(inx, 0);
        for (int i = 0; i < 4; i++) {
            j = (i + 1) % 4;
            spread(inx, j);
            func(inx + 1);
            rollback(inx, i);
        }
        rollback(inx, j);
    }

    private static void pickDir2(int inx) {
        int j;
        for (int i = 0; i < 2; i++) {
            j = i + 2;
            spread(inx, i);
            spread(inx, j);
            func(inx + 1);
            rollback(inx, i);
            rollback(inx, j);
        }
    }


    private static void pickDir1(int inx) {
        for (int i = 0; i < 4; i++) {
            spread(inx, i);
            func(inx + 1);
            rollback(inx, i);
        }
    }

    private static void rollback(int inx, int i) {
        int y = cctvs.get(inx).y;
        int x = cctvs.get(inx).x;
        if (i % 2 == 0) {
            if (i / 2 == 0) {
                for (int j = y; j >= 0; j--) {
                    if (isWallReverse(j, x)) break;
                }
            } else {
                for (int j = y; j < N; j++) {
                    if (isWallReverse(j, x)) break;
                }
            }
        } else {
            if (i / 2 == 0) {
                for (int j = x; j < M; j++) {
                    if (isWallReverse(y, j)) break;
                }
            } else {
                for (int j = x; j >= 0; j--) {
                    if (isWallReverse(y, j)) break;
                }
            }
        }
    }

    private static boolean isWallReverse(int y, int x) {
        if (map[y][x][0] == 6) return true;
        else if (map[y][x][0] == 7) {
            map[y][x][1] -= 1;
            if (map[y][x][1] == 0) {
                map[y][x][0] = 0;
                blindSpot++;
            }
        }
        return false;
    }

    private static void spread(int inx, int i) {
        int y = cctvs.get(inx).y;
        int x = cctvs.get(inx).x;
        if (i % 2 == 0) {
            if (i / 2 == 0) {
                for (int j = y; j >= 0; j--) {
                    if (isWall(j, x)) break;
                }
            } else {
                for (int j = y; j < N; j++) {
                    if (isWall(j, x)) break;
                }
            }
        } else {
            if (i / 2 == 0) {
                for (int j = x; j < M; j++) {
                    if (isWall(y, j)) break;
                }
            } else {
                for (int j = x; j >= 0; j--) {
                    if (isWall(y, j)) break;
                }
            }
        }
    }

    private static boolean isWall(int y, int x) {
        if (map[y][x][0] == 6) return true;
        else if (map[y][x][0] == 0) {
            map[y][x][0] = 7;
            map[y][x][1] += 1;
            blindSpot--;
        }else if(map[y][x][0] == 7){
            map[y][x][1] += 1;
        }
        return false;
    }
} // end of class
