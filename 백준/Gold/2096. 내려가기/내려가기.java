import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2096 내려가기

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int min1 = 0, min2 = 0, min3 = 0;
        int max1 = 0, max2 = 0, max3 = 0;
        int minTmp1 = 0, minTmp2 = 0, minTmp3 = 0;
        int maxTmp1 = 0, maxTmp2 = 0, maxTmp3 = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            minTmp1 = Math.min(min1, min2) + val1;
            maxTmp1 = Math.max(max1, max2) + val1;

            int val2 = Integer.parseInt(st.nextToken());
            minTmp2 = Math.min(min1, Math.min(min2, min3)) + val2;
            maxTmp2 = Math.max(max1, Math.max(max2, max3)) + val2;

            int val3 = Integer.parseInt(st.nextToken());
            minTmp3 = Math.min(min2, min3) + val3;
            maxTmp3 = Math.max(max2, max3) + val3;

            min1 = minTmp1;
            min2 = minTmp2;
            min3 = minTmp3;

            max1 = maxTmp1;
            max2 = maxTmp2;
            max3 = maxTmp3;
        }

        System.out.println(Math.max(max1, Math.max(max2, max3)) + " " + Math.min(min1, Math.min(min2, min3)));
    }
}