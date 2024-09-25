import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// AC
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            String ops = br.readLine(); // 명령어
            int N = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수
            int[] num = new int[N]; // 수

            String numStr = br.readLine();
            numStr = numStr.substring(1, numStr.length() - 1);
            st = new StringTokenizer(numStr, ",");
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            boolean isFront = true, isError = false;
            int s = 0, e = N - 1;
            for (int i = 0; i < ops.length(); i++) {
                if (ops.charAt(i) == 'R') { // R = 뒤집기
                    isFront = !isFront;
                } else { // D
                    if (isFront) { // 정방향으로 나열된 경우 앞 버리기
                        s++;
                    } else { // 역방향으로 나열된 경우 뒤 버리기
                        e--;
                    }
                    if (e - s + 1 < 0) { // 배열이 비어있는데 D를 사용한 경우
                        isError = true;
                        break;
                    }
                }
            }

            if (isError) { // 에러
                answer.append("error\n");
            } else {
                answer.append("[");
                if (isFront) { // 정방향으로 나열된 경우
                    for (int i = s; i <= e; i++) {
                        answer.append(num[i] + (i == e ? "" : ","));
                    }
                } else { // 역방향으로 나열된 경우
                    for (int i = e; i >= s; i--) {
                        answer.append(num[i] + (i == s ? "" : ","));
                    }
                }
                answer.append("]\n");
            }
        }

        System.out.println(answer);
    }
}