import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// A와 B 2
public class Main {

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String source = br.readLine();
        String target = br.readLine();

        // source -> target으로 바꿀 수 있는지 확인하지 않고
        // target -> source로 바꿀 수 있는지 확인한다.
        dfs(source, target);

        System.out.println(answer);

    }

    public static void dfs(String source, String target) {
        if (source.length() == target.length()) {
            if (source.equals(target)) {
                answer = 1;
            }
            return;
        }

        // target의 가장 오른쪽 문자가 A라면, A를 제거한다.
        if (target.charAt(target.length() - 1) == 'A') {
            String nextTarget = target.substring(0, target.length() - 1);
            dfs(source, nextTarget);

        }

        // target의 가장 왼쪽 문자가 B라면, B를 제거하고 문자열을 뒤집는다.
        if (target.charAt(0) == 'B') {
            String nextTarget = (new StringBuffer(target.substring(1, target.length())).reverse()).toString();
            dfs(source, nextTarget);
        }

    }


}