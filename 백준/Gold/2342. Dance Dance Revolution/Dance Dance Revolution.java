import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2342 Dance Dance Revolution

public class Main {

    static List<Integer> list;
    static int[][] power;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지시 사항
        list = new ArrayList<>();
        int n = 0;
        while ((n = Integer.parseInt(st.nextToken())) != 0) {
            list.add(n);
        }

        // 발판 움직일 때 사용하는 힘
        power = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) power[i][j] = 1;
                else if (i == 0 || j == 0) power[i][j] = 2;
                else if (i % 2 != j % 2) power[i][j] = 3;
                else power[i][j] = 4;
            }
        }

        dp = new int[5][5][list.size()];
        int answer = dfs(0,0,0);
        System.out.println(answer);
    }

    public static int dfs(int left, int right, int idx) {
        if (idx == list.size()) {
            return 0;
        }

        if (dp[left][right][idx] != 0) { // 이미 동일조건에서 최소 힘을 구했다면 그대로 활용
            return dp[left][right][idx];
        }

        int next = list.get(idx);
        int moveLeft = dfs(next, right, idx + 1) + power[left][next]; // 다음 발판으로 왼발을 움직이는 경우
        int moveRight = dfs(left, next, idx + 1) + power[right][next]; // 다음 발판으로 오른발을 움직이는 경우
        dp[left][right][idx] = Math.min(moveLeft, moveRight);

        return dp[left][right][idx];
    }
}