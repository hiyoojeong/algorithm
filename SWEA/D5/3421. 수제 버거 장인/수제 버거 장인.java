import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 3421. 수제 버거 장인
public class Solution {

    static int N, M, arr[][], result;
    static Set<Integer> selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();

            arr = new int[M][2]; // 궁합이 맞지 않는 재료들 쌍
            for (int i = 0; i < M; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
            }

            result = 0;
            selected = new HashSet<>();
            subset(1);

            answer.append(String.format("#%d %d\n", test_case, result));
        }

        System.out.println(answer);

        sc.close();
    }

    public static void subset(int cnt) {
        if (cnt > N) {
            if (isAvailable()) {
                result++;
            }
            return;
        }

        // 재료 선택
        selected.add(cnt);
        subset(cnt + 1);

        // 재료 미선택
        selected.remove(cnt);
        subset(cnt + 1);
    }

    public static boolean isAvailable() {
        for (int i = 0; i < M; i++) {
            // 두 재료 동시 존재하면 햄버거 만들기 불가능
            if (selected.contains(arr[i][0]) && selected.contains(arr[i][1])) {
                return false;
            }
        }
        return true;
    }
}
