import java.util.ArrayList;
import java.util.List;

// 불량 사용자
public class No64064 {

	static List<List<String>> banned_user_lists;

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "abc1**" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "*rodo", "*rodo", "******" }));
		System.out.println(solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
				new String[] { "fr*d*", "*rodo", "******", "******" }));
	}

	public static int solution(String[] user_id, String[] banned_id) {
		banned_user_lists = new ArrayList<>();
		dfs(new boolean[user_id.length], 0, user_id, banned_id);
		return banned_user_lists.size();
	}

	public static void dfs(boolean[] visited, int banned_idx, String[] user_id, String[] banned_id) {
		if (banned_idx == banned_id.length) {
			List<String> cur_list = new ArrayList<>();
			for (int i = 0; i < user_id.length; i++) {
				if (visited[i]) {
					cur_list.add(user_id[i]);
				}
			}

			if (banned_user_lists.size() > 0) {
				for (List<String> banned_user_list : banned_user_lists) {
					boolean isSame = true;
					for (String banned_user : banned_user_list) {
						if (!cur_list.contains(banned_user)) {
							isSame = false;
						}
					}

					if (isSame) {
						return;
					}
				}
			}

			banned_user_lists.add(cur_list);
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			String banned = banned_id[banned_idx].replaceAll("\\*", "\\.");
			String user = user_id[i];

			if (user.matches(banned) && !visited[i]) {
				visited[i] = true;
				dfs(visited, banned_idx + 1, user_id, banned_id);
				visited[i] = false;
			}
		}
	}
}
