import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer foxSound = new StringBuffer();

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String sounds = " " + br.readLine() + " ";

			while (true) {
				String input = br.readLine();

				if (input.equals("what does the fox say?")) {
					break;
				}

				String sound = " " + input.split(" ")[2] + " ";
				while (sounds.contains(sound)) {
					String newsounds = sounds.replaceAll(sound, " ");
					if (newsounds.equals(sound)) {
						break;
					}
					sounds = newsounds;
				}

			}

			foxSound.append(sounds.substring(1, sounds.length() - 1) + "\n");
		}

		System.out.println(foxSound);
	}

}
