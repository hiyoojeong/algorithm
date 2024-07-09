import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 자릿수 더하기
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int digit = 10;
		int sum = 0;
		while (N != 0) {
			sum += (N % digit);
			N /= 10;
		}
		
		System.out.println(sum);

	}

}