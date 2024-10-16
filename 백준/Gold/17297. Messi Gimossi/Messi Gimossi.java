import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Messi Gimossi
public class Main {

    static final String FIRST_STRING = "Messi "; // 마지막에 공백까지 포함한 문자열
    static final String SECOND_STRING = "Messi Gimossi "; // 마지막에 공백까지 포함한 문자열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        char result = 0;
        if (M <= SECOND_STRING.length()) {
            result = SECOND_STRING.charAt(M - 1);
        } else {
            List<Integer> messi = new ArrayList<>();
            messi.add(-1); // 0번째는 아무거나 저장
            messi.add(FIRST_STRING.length()); // messi[1]
            messi.add(SECOND_STRING.length()); // messi[2]

            // messi[N]의 전체 글자수를 저장하면서, M번째 문자가 위치하는 N을 찾는다.
            int N = 2;
            while (true) {
                if (M <= messi.get(N)) {
                    break;
                }

                messi.add(messi.get(N) + messi.get(N - 1)); // messi[N+1]
                N++;
            }

            // messi[N] 범위를 줄여가며, M번째 문자를 찾는다.
            while (true) {
                if (M <= SECOND_STRING.length()) {
                    break;
                }

                if (M <= messi.get(N - 1)) { // 앞부분에 M번째 문자가 있는 경우
                    N -= 1;
                } else if (M <= messi.get(N - 1) + messi.get(N - 2)) { // 뒷부분에 M번째 문자가 있는 경우
                    M -= messi.get(N - 1);
                    N -= 2;
                }
            }

            result = SECOND_STRING.charAt(M - 1);
        }

        System.out.println(result == ' ' ? "Messi Messi Gimossi" : result);
    }
}