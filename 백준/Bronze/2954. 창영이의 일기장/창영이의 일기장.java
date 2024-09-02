
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 창영이의 일기장
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		str = str.replaceAll("apa", "a");
		str = str.replaceAll("epe", "e");
		str = str.replaceAll("ipi", "i");
		str = str.replaceAll("opo", "o");
		str = str.replaceAll("upu", "u");
		System.out.println(str);
	}

}
