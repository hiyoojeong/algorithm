import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 투에-모스 문자열
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        String bin = Long.toBinaryString(k - 1);
        long cnt = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                cnt++;
            }
        }

        System.out.println(cnt % 2 == 0 ? 0 : 1);
    }
}