import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// LCS 2
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String str1 = br.readLine();
        String str2 = br.readLine();

        // lcs
        int[][] lcs = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        // lcs 역추적
        int i = str1.length();
        int j = str2.length();
        int maxLen = 0;
        while(i > 0 && j > 0) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                sb.insert(0, str1.charAt(i-1));
                maxLen++;
                i--;
                j--;
            } else {
                if(lcs[i-1][j] > lcs[i][j-1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        System.out.println(maxLen);
        System.out.println(sb);
    }
}
