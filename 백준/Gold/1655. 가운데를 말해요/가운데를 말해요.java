import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 가운데를 말해요
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 이진탐색으로 하계영역에 값 추가
            int left = 0;
            int right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            list.add(right, num);

            // 중앙값 출력
            sb.append(list.get((list.size() - 1) / 2) + "\n");
        }
        System.out.println(sb);
    }
}