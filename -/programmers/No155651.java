import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// 호텔 대실
public class No155651 {

	public static void main(String[] args) {
		System.out.println(solution(new String[][] { { "15:00", "17:00" }, { "16:40", "18:20" }, { "14:20", "15:20" },
				{ "14:10", "19:20" }, { "18:20", "21:20" } }));
		System.out.println(solution(new String[][] { { "09:10", "10:10" }, { "10:20", "12:20" } }));
		System.out
				.println(solution(new String[][] { { "10:20", "12:30" }, { "10:20", "12:30" }, { "10:20", "12:30" } }));
	}

	public static int solution_fail(String[][] book_time) {
		// 예약 시간을 오름차순으로 정렬
		Arrays.sort(book_time, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if (o1[0].equals(o2[0]))
					return o1[1].compareTo(o2[1]);
				else
					return o1[0].compareTo(o2[0]);
			}
		});

		// 예약 시간을 LocalTime 타입으로 변경
		LocalTime[][] timelist = new LocalTime[book_time.length][2];
		for (int i = 0; i < book_time.length; i++) {
			for (int j = 0; j < 2; j++) {
				String[] hhmm = book_time[i][j].split(":");
				timelist[i][j] = LocalTime.of(Integer.parseInt(hhmm[0]), Integer.parseInt(hhmm[1]));
			}
		}

//		for (int i = 0; i < timelist.length; i++) {
//			System.out.println(timelist[i][0] + "입실 / " + timelist[i][1] + "퇴실");
//		}

		// 예약 시간별로 객실 할당하기
		ArrayList<LocalTime> rooms = new ArrayList<>();
		for (int i = 0; i < timelist.length; i++) {
			Collections.sort(rooms); // 대실 종료 시간을 기준으로 오름차순으로 정렬

			boolean isAdd = false; // 객실이 할당되었는가

			for (int j = 0; j < rooms.size(); j++) { // 이미 할당된 객실 중에 현재 사용 가능한 객실이 있다면, 할당
//				System.out.println("입실가능시간: " + rooms.get(j).plusMinutes(10) + " 이후");
//				System.out.println("입실희망시간: " + timelist[i][0]);
				if (timelist[i][0].compareTo(rooms.get(j).plusMinutes(10)) >= 0) {
//					System.out.println(timelist[i][0].compareTo(rooms.get(j).plusMinutes(10)) + ": 입실가능 입실!");
					rooms.set(j, timelist[i][1]);
					isAdd = true;
					break;
				} else {
//					System.out.println(timelist[i][0].compareTo(rooms.get(j).plusMinutes(10)) + ": 입실불가능");
				}
			}

			if (!isAdd) { // 이미 할당된 객실 중에 현재 사용 가능한 객실이 없다면, 새로 할당
//				System.out.println("새입실!");
				rooms.add(timelist[i][1]);
			}

//			System.out.println("방 수: " + rooms.size());
		}

		return rooms.size();
	}

	public static int solution(String[][] book_time) {

		// 예약 시간을 오름차순으로 정렬
		Arrays.sort(book_time, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if (o1[0].equals(o2[0]))
					return o1[1].compareTo(o2[1]);
				else
					return o1[0].compareTo(o2[0]);
			}
		});

		// 예약 시간 정수로 변경
		int[][] time = new int[book_time.length][2];
		for (int i = 0; i < book_time.length; i++) {
			int start_time = Integer.parseInt(book_time[i][0].replace(":", ""));
			int end_time = Integer.parseInt(book_time[i][1].replace(":", ""));

			end_time += 10;
			if (end_time % 100 >= 60) {
				end_time += 40;
			}

			time[i][0] = start_time;
			time[i][1] = end_time;
		}

		// 예약 시간별로 객실 할당하기
		ArrayList<Integer> rooms = new ArrayList<>();
		for (int i = 0; i < time.length; i++) {
			Collections.sort(rooms); // 대실 종료 시간을 기준으로 오름차순으로 정렬 (종료 시간이 짧은 방부터 우선 할당)

			boolean isAdd = false; // 객실이 할당되었는가

			for (int j = 0; j < rooms.size(); j++) { // 이미 할당된 객실 중에 현재 사용 가능한 객실이 있다면, 할당
				if (time[i][0] >= rooms.get(j)) {
					rooms.set(j, time[i][1]);
					isAdd = true;
					break;
				}
			}

			if (!isAdd) { // 이미 할당된 객실 중에 현재 사용 가능한 객실이 없다면, 새로 할당
				rooms.add(time[i][1]);
			}
		}

		return rooms.size();
	}
}
