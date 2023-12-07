package 정렬;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// 수 정렬하기3
class Item implements Comparable<Item> {
	int value;
	int idx;

	public Item(int value, int idx) {
		this.value = value;
		this.idx = idx;
	}

	@Override
	public int compareTo(Item o) {
		return Integer.compare(this.value, o.value);
	}
}

public class No10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		arraysSort();
//		countSort();
//		mergeSort();
	}

	// Arrays에서 제공하는 sort() 함수를 이용
	public static void arraysSort() throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		br.close();

		// 풀이
		StringBuilder sb = new StringBuilder();
		Arrays.sort(arr);
		for(int i=0; i<N; i++)
			sb.append(arr[i]).append("\n");

		// 출력
		System.out.println(sb.toString());
	}

	// 카운트 정렬
	// 주어지는 수가 10000보다 작거나 같은 자연수라는 조건이 있으므로 카운트 정렬을 사용하여 해결할 수 있다.
	public static void countSort() throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[10000];
		for (int i = 0; i < N; i++) {
			count[Integer.parseInt(br.readLine()) - 1]++;
		}
		br.close();

		// 풀이
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < count[i]; j++) {
				sb.append(i + 1).append("\n");
			}
		}

		// 출력
		System.out.println(sb.toString());
	}

	// 병합 정렬
	// 백준에서는 파일 생성이 금지되어 있으므로 병합 정렬이 불가능하다.
	public static void mergeSort() {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int MAXSIZE = 1000000;
		int COUNT = 0;
		while (N > 0) {
			MAXSIZE = N < MAXSIZE ? N : MAXSIZE;
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < MAXSIZE; i++)
				arr.add(sc.nextInt());

			Collections.sort(arr);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < MAXSIZE; i++)
				sb.append(arr.get(i)).append("\n");
			N -= MAXSIZE;

			File file = new File("run" + COUNT++ + ".txt");
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(sb.toString());
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();

		// 풀이
		ArrayList<Scanner> fcList = new ArrayList<Scanner>();
		ArrayList<Item> arr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < COUNT; i++)
			try {
				fcList.add(new Scanner(new File("run" + i + ".txt")));
				arr.add(new Item(fcList.get(i).nextInt(), i));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		Item min = null;
		do {
			Collections.sort(arr);

			min = arr.get(0);
//					System.out.println("min: " + min.value + ", " + min.idx);

			if (min.value == Integer.MAX_VALUE)
				break;
			sb.append(min.value).append("\n");

			if (fcList.get(min.idx) != null && fcList.get(min.idx).hasNext()) {
				min.value = fcList.get(min.idx).nextInt();
			} else {
				fcList.set(min.idx, null);
				arr.remove(0);
			}
		} while (arr.size() > 0);

		// 출력
		System.out.println(sb.toString());
	}
}
