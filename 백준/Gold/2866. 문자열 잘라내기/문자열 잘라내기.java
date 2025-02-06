import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 문자열 잘라내기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] words = new char[R][C];
        for (int i = 0; i < R; i++) {
            words[i] = br.readLine().toCharArray();
        }

        // 뒤에서부터 문자열 정보 저장
        Map<Character, HashMap> root = new HashMap<>();
        Map<Character, HashMap> cur = root;
        for (int i = R - 1; i >= 0; i--) {
            if (!cur.containsKey(words[i][0])) {
                cur.put(words[i][0], new HashMap<>());
            }
            cur = cur.get(words[i][0]);
        }

        // 뒤에서부터 가장 먼저 문자열이 달라지는 구간 탐색
        int cnt = R - 1;
        for (int j = 1; j < C; j++) {
            int tmpCnt = 0; // 문자열이 한번도 달라지는 구간이 없었다면 0 유지
            cur = root;
            for (int i = R - 1; i >= 0; i--) {
                if (!cur.containsKey(words[i][j])) {
                    cur.put(words[i][j], new HashMap<>());
                    if (tmpCnt == 0) { // 문자열이 처음 달라지는 구간 체크
                        tmpCnt = i;
                    }
                }
                cur = cur.get(words[i][j]);
            }
            cnt = Math.min(cnt, tmpCnt);
        }

        System.out.println(cnt);
    }

}