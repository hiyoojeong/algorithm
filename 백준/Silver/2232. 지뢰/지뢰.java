import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 지뢰
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] bomb = new int[N];

        for (int i = 0; i < N; i++) {
            bomb[i] = Integer.parseInt(br.readLine());
        }

        // 직접 터트려야 하는 지뢰의 번호를 찾는다.
        StringBuffer ans = new StringBuffer();
        if(N == 1) {
            System.out.println(1);
            return;
        }

        for (int i = 0; i < N; i++) {
            // 가장 앞에 있는 지뢰의 경우, 뒤에 있는 지뢰에 의해 연쇄적으로 터지지 않으면 직접 터트린다.
            if (i == 0) {
                if (bomb[i] >= bomb[i + 1]) {
                    ans.append(i + 1).append("\n");
                }

            }
            // 가장 뒤에 있는 지뢰의 경우, 앞에 있는 지뢰에 의해 연쇄적으로 터지지 않으면 직접 터트린다.
            else if (i == N - 1) {
                if (bomb[i] >= bomb[i - 1]) {
                    ans.append(i + 1).append("\n");
                }
            }
            // 중간에 있는 지뢰의 경우, 앞뒤에 있는 지뢰에 의해 연쇄적으로 터지지 않으면 직접 터트린다.
            else {
                if (bomb[i] >= bomb[i - 1] && bomb[i] >= bomb[i + 1]) {
                    ans.append(i + 1).append("\n");
                }
            }
        }

        System.out.println(ans);

    }

}