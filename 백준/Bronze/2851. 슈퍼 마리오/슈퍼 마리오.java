
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 슈퍼 마리오
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nums = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int sum = 0, closeSum = 0;
		for (int i = 0; i < 10; i++) {
			sum += nums[i];
			if (Math.abs(100 - closeSum) >= Math.abs(100 - sum)) {
				closeSum = sum;
			}
		}

		System.out.println(closeSum);
	}

}
