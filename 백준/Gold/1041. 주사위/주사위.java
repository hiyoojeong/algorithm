import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주사위
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // N이 1일 때, 가장 큰 수만 아래로 감춘다.
        if (N == 1) {
            int total = 0, max = 0;
            for (int i = 0; i < 6; i++) {
                total += dice[i];
                if (max < dice[i]) {
                    max = dice[i];
                }
            }
            System.out.println(total - max);
        }
        // N이 1보다 클 때, 위치마다 보이는 면의 수를 구한 뒤 보이는 면의 최소값을 곱해준다.
        else {
            int one = 50, two = 100, three = 150;
            for (int i = 0; i < 6; i++) {
                if (one > dice[i]) { // 1면이 보이는 경우 중 가장 작은 수
                    one = dice[i];
                }
                for (int j = i + 1; j < 6; j++) {
                    if (i + j == 5) { // 마주보고 있는 관계
                        continue;
                    }
                    if (two > dice[i] + dice[j]) {  // 2면이 보이는 경우 중 가장 작은 수
                        two = dice[i] + dice[j];
                    }
                    for (int k = j + 1; k < 6; k++) {
                        if (i + k == 5 || j + k == 5) { // 마주보고 있는 관계
                            continue;
                        }
                        if (three > dice[i] + dice[j] + dice[k]) { // 3면이 보이는 경우 중 가장 작은 수
                            three = dice[i] + dice[j] + dice[k];
                        }
                    }
                }
            }

            long result = 0;
            result += three * 4;
            result += two * ((N - 2) * 4 + (N - 1) * 4);
            result += one * (5 * (N - 2) * (N - 2) + 4 * (N - 2));
            System.out.println(result);
        }
    }
}