import java.util.*;
class Solution {
    static class CourseMenu implements Comparable<CourseMenu>{
		String menu;
		int cnt;
		public CourseMenu(String menu, int cnt) {
			this.menu = menu;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(CourseMenu o) {
			return o.cnt - this.cnt;
		}
	}
	public static void getCombination(HashMap<String, Integer> hm, String order, int menuCnt, int startNow, String now) {
		if(now.length()==menuCnt) {
			char[] temp = now.toCharArray();
			Arrays.sort(temp);
			String menu = new String(temp);
			if(hm.containsKey(menu)) {
				hm.put(menu, hm.get(menu)+1);
			}else {
				hm.put(menu, 1);
			}
			return;
		}
		
		for(int i=startNow; i<order.length(); i++) {
			getCombination(hm, order, menuCnt, i+1, now+order.charAt(i));
		}
		
	}
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> result = new ArrayList<>(); 
        for(int i=0; i<course.length; i++) {
        	// course[i] 개수에 해당하는 모든 메뉴 조합 -> key : 메뉴 조합, value : 해당 메뉴 조합이 주문된 횟순
        	HashMap<String, Integer> hm = new HashMap<>();
        	for(int j=0; j<orders.length; j++) {
        		getCombination(hm, orders[j], course[i],0, "");
        	}
        	// 이제 hm에 course[i] 길이의 모든 메뉴 조합 담김
        	List<CourseMenu> list = new ArrayList<>();
        	for(String menu : hm.keySet()) {
        		if(hm.get(menu)>=2) {
        			list.add(new CourseMenu(menu, hm.get(menu)));
        		}
        	}
        	
        	if(list.size() > 0) {
        		Collections.sort(list);
            	int maxVal = list.get(0).cnt;
            	for(CourseMenu c : list) {
            		if(c.cnt == maxVal) {
            			result.add(c.menu);
            		}else {
            			break;
            		}
            	}
        	}
        	
        }
        
        Collections.sort(result);
        // list 배열로 반환
        answer = new String[result.size()];
        for(int i=0; i<answer.length; i++) {
        	answer[i] = result.get(i);
        }
        
        return answer;
    }
}
