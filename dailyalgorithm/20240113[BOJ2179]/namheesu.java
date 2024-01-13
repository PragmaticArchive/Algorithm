import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    private static class Word implements Comparable<Word> {
        char[] word;
        int inx;

        public Word(char[] word, int inx) {
            this.word = word;
            this.inx = inx;
        }

        @Override
        public int compareTo(Word o) {
            return Arrays.compare(this.word, o.word);
        }
    }

    private static int[][] cnt;
    private static char[][] inputs;
    private static Word[] sortWords;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inputs = new char[N][];
        cnt = new int[N][];
        sortWords = new Word[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine().toCharArray();
            sortWords[i] = new Word(inputs[i], i);
            cnt[i] = new int[inputs[i].length];
            Arrays.fill(cnt[i], Integer.MAX_VALUE);
        }

        Arrays.sort(sortWords);
        findSimilarWord();
    } // end of main

    private static void findSimilarWord() {
        int max = 0;
        int[] point = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (sortWords[i].word[0] != sortWords[j].word[0]) break;
                max = Math.max(max, chkPrefix(point, sortWords[i], sortWords[j]));
            }
        }
        for (int i = 0; i < N; i++) {
            if (inputs[i].length > max && cnt[i][max] != Integer.MAX_VALUE) {
                StringBuilder sb = new StringBuilder();
                sb.append(inputs[i]).append("\n").append(inputs[cnt[i][max]]);
                System.out.println(sb);
                return;
            }
        }
    }

    private static int chkPrefix(int[] point, Word w1, Word w2) {
        int index = Math.min(point[w1.inx], point[w2.inx]);
        int len = Math.min(w1.word.length, w2.word.length);
        for (; index < len; index++) {
            if (w1.word[index] != w2.word[index]) break;
            point[w1.inx] = Math.max(point[w1.inx], index);
            point[w2.inx] = Math.max(point[w2.inx], index);
        }
        cnt[w1.inx][index - 1] = Math.min(cnt[w1.inx][index - 1], w2.inx);
        cnt[w2.inx][index - 1] = Math.min(cnt[w2.inx][index - 1], w1.inx);
        return index - 1;
    }
} // end of class
