import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 고냥이
public class Main {

    static int N;
    static String str;
    static Map<Character, Integer> cnts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        cnts = new HashMap<>();

        int maxLen = 1, len = 1;
        int s = 0; // 현재 문자열의 시작을 가리키는 포인터
        int e = 1; // 다음 문자를 가리키는 포인터
        cnts.put(str.charAt(0), 1);

        while (e < str.length()) {
            char next = str.charAt(e);

            // 이미 인식하고 있는 문자를 넣어야 하는 경우 -> 넣을 수 있다.
            if (cnts.containsKey(next)) {
                // 해당 문자의 개수를 늘린다.
                cnts.put(next, cnts.get(next) + 1);

                // 끝 인덱스를 늘려, 문자열 길이를 늘린다.
                e++;
                len++;
            }
            // 새로운 문자를 넣어야 하는 경우
            else {
                // 줄이기
                // 문자를 더 인식할 수 없다면, 넣을 수 있을 때까지 줄이고 넣는다.
                if (cnts.size() >= N) {
                    while (cnts.size() >= N) {
                        // 해당 문자의 개수를 차감한다. 개수가 0이 되었으면, 완전히 삭제한다.
                        char front = str.charAt(s);
                        cnts.put(front, cnts.get(front) - 1);
                        if (cnts.get(front) == 0) {
                            cnts.remove(front);
                        }

                        // 시작 인덱스를 늘려, 문자열 길이를 줄인다.
                        s++;
                        len--;
                    }
                }

                // 넣기
                // 해당 문자의 개수를 설정한다.
                cnts.put(next, 1);

                // 끝 인덱스를 늘려, 문자열 길이를 늘린다.
                e++;
                len++;
            }

            maxLen = Math.max(len, maxLen);
        }

        System.out.println(maxLen);
    }
}
