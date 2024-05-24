import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 폴리오미노
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		str = str.replaceAll("XXXX", "AAAA");
		str = str.replaceAll("XX", "BB");

		if (str.contains("X")) {
			System.out.println(-1);
		} else {
			System.out.println(str);
		}

	}

}
