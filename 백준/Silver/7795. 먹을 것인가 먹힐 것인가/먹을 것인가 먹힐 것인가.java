import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 먹을 것인가 먹힐 것인가
public class Main {

    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        // 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            answer.append(getCnt()).append("\n");
        }

        System.out.println(answer);
    }

    public static int getCnt() {
        Arrays.sort(A);
        Arrays.sort(B);

        int totalCnt = 0, cnt = 0;
        int posA = 0; // A를 가리키는 포인터
        int posB = 0; // B를 가리키는 포인터

        while (posA < N && posB < M) {
            // A가 먹을 수 있는 B라면, 먹는다.
            if (A[posA] > B[posB]) {
                cnt++; // 경우의 수 증가
                posB++; // 다음 B로 이동
            }
            // A가 먹을 수 없는 B라면, 다음 A를 확인한다.
            else {
                totalCnt += cnt; // 현재까지의 경우의 수 업데이트
                posA++; // 다음 A로 이동
            }
        }

        // 남아있는 A가 있다면, 현재까지 먹힌 B를 모두 먹을 수 있다.
        while(posA < N) {
            totalCnt += cnt;
            posA++;
        }

        return totalCnt;
    }
}
