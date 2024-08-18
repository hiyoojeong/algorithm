import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 준환이의 양팔저울
public class Solution {

    static int N; // 무게 추의 개수
    static int[] weights; // 무게 추의 무게
    static int[] selected; // 추의 순서
    static boolean[] isSelected; // 선택한 추

    static int totalCnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            weights = new int[N];
            selected = new int[N];
            isSelected = new boolean[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            totalCnt = 0;
            permutation(0);

            answer.append(String.format("#%d %d\n", t, totalCnt));
        }

        System.out.println(answer);
    }

    public static void permutation(int cnt) {
        if (cnt == N) {
            // 만들어진 순서로, 왼쪽 혹은 오른쪽에 올려본다.
            subset(0, 0, 0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) {
                selected[cnt] = weights[i];
                isSelected[i] = true;
                permutation(cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    public static void subset(int cnt, int left, int right) {
        // 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다.
        if (left < right) {
            return;
        }

        // 조건을 만족하여 모든 추를 양팔저울에 올렸으므로, 경우의 수를 추가한다.
        if (cnt == N) {
            totalCnt++;
            return;
        }

        // 왼쪽에 올리기
        subset(cnt + 1, left + selected[cnt], right);
        // 오른쪽에 올리기
        subset(cnt + 1, left, right + selected[cnt]);
    }

}
