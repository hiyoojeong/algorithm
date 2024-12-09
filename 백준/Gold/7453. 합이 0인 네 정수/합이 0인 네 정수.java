import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 합이 0인 네 정수
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[4][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        // AB 조합으로 나오는 수, CD 조합으로 나오는 수 저장
        int[] AB = new int[N * N];
        int[] CD = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = arr[0][i] + arr[1][j];
                CD[idx] = arr[2][i] + arr[3][j];
                idx++;
            }
        }

        // CD 오름차순 정렬 -> lower, upper로 숫자 카운팅하기 위함
        Arrays.sort(CD);

        // -(AB 조합)이 나오는 CD 조합의 개수를 구함
        long cnt = 0;
        for (int i = 0; i < N * N; i++) {
            int key = (-1) * AB[i];
            int left = 0, right = N * N;
            int Lidx = lower(CD, left, right, key);
            int Uidx = upper(CD, left, right, key);
            if (Lidx == Uidx) { // key와 같은 값이 없는 경우
                continue;
            }
            cnt += Uidx - Lidx; // key와 같은 값이 있는 경우, 상계 - 하계로 key 개수 카운팅
        }

        System.out.println(cnt);
    }

    private static int lower(int[] arr, int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            int val = arr[mid];
            if (val >= key) { // 크거나 같으면 right로 지정
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private static int upper(int[] arr, int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            int val = arr[mid];
            if (val > key) { // 크면 right로 지정
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}