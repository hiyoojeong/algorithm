import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 햄버거 분배
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 식탁의 길이
        int K = Integer.parseInt(st.nextToken()); // 햄버거를 선택할 수 있는 거리

        String table = br.readLine();
        boolean[] check = new boolean[N];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (table.charAt(i) == 'P') {
                boolean isFind = false;

                // 왼쪽에서부터 i 직전까지 탐색
                int sIdx = Math.max(i - K, 0);
                for (int j = sIdx; j < i; j++) {
                    if (table.charAt(j) == 'H' && !check[j]) {
                        isFind = true;

                        check[j] = true;
                        cnt++;
                        break;
                    }
                }

                // i 직후부터 오른쪽까지 탐색
                int eIdx = Math.min(i + K, N - 1);
                if (!isFind) {
                    for (int j = i + 1; j <= eIdx; j++) {
                        if (table.charAt(j) == 'H' && !check[j]) {
                            check[j] = true;
                            cnt++;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(cnt);
    }


}