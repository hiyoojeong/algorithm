import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 감소하는 수
public class Main {

    static int N;
    static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        long answer = 0;

        // 한 자리 수는 해당 수가 순서이다.
        if (N <= 10) {
            answer = N;
        }
        // 만들 수 있는 감소하는 수의 최대값으 9876543210이다. 이는 N=1022으로 이보다 큰값을 가질 수 없다.
        else if (N > 1022) {
            answer = -1;
        }
        // 숫자 i로 시작하여, 오른쪽에 감소하는 수를 붙여나간다.
        else {
            for (int i = 0; i <= 9; i++) {
                getDescending(i, 1);
            }
            Collections.sort(list); // 정렬

            answer = list.get(N); // N번째 감소하는 수 출력
        }

        System.out.println(answer);

    }

    public static void getDescending(long num, int digit) {
        // 감소하는 수이므로, 최대수가 9876543210로 10개 이상의 수가 사용될 수 없다.
        if (digit > 10) {
            return;
        }

        // 감소하는 수가 만들어졌으므로, 리스트에 추가한다.
        list.add(num);

        // num의 가장 오른쪽에 있는 숫자보다, 더 작은 수를 붙줘야한다.
        for (int i = 0; i < num % 10; i++) {
            getDescending(num * 10 + i, i + 1);
        }
    }


}