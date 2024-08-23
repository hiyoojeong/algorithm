import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12891. DNA 비밀번호
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 부분문자열의 길이

        String str = br.readLine(); // DNA 문자열

        int[] minCnt = new int[4]; // {‘A’, ‘C’, ‘G’, ‘T’} 최소 개수
        int[] cnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minCnt[i] = Integer.parseInt(st.nextToken());
        }

        // P - 1개 미리 카운팅
        for (int i = 0; i < P - 1; i++) {
            int idx = getIndex(str.charAt(i));
            cnt[idx]++;
        }

        // 문자 하나씩 늘리고 줄이며, 가능한 문자열이면 카운팅
        int result = 0;
        for (int i = 0, j = P - 1; j < S; i++, j++) {
            // 늘리기
            int eIdx = getIndex(str.charAt(j));
            cnt[eIdx]++;

            // 가능한 문자열 확인
            boolean isAvailable = true;
            for (int k = 0; k < 4; k++) {
                if (cnt[k] < minCnt[k]) {
                    isAvailable = false;
                }
            }

            // 가능한 문자열 수 업데이트
            if (isAvailable) {
                result++;
            }

            // 줄이기
            int sIdx = getIndex(str.charAt(i));
            cnt[sIdx]--;
        }

        System.out.println(result);
    }

    public static int getIndex(char ch) {
        if (ch == 'A') {
            return 0;
        } else if (ch == 'C') {
            return 1;
        } else if (ch == 'G') {
            return 2;
        } else if (ch == 'T') {
            return 3;
        }
        return -1;
    }
}
