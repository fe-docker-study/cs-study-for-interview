import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//6개 원소로 이루어진 부분집합의 개수를 구하라 
public class Main {
	static void dfs(int idx, int cnt, String selected, int[] arr) {
		if(cnt==6) {
			System.out.println(selected);
			return;
		}
		
		if(idx >= arr.length) {
			return;
		}
		
		String nextSelected = selected +" "+ arr[idx];
		if(selected.length()==0) {
			nextSelected = Integer.toString(arr[idx]);
		}
		
		// 현재 idx 선택함
		dfs(idx+1, cnt+1, nextSelected, arr);
		
		// 선택 안함
		dfs(idx+1, cnt, selected, arr);
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		StringTokenizer st;
		while(true) {
			input = br.readLine();
			if(input.equals("0")) break;
			st = new StringTokenizer(input);
			
			int k = Integer.parseInt(st.nextToken());
			int[] arr = new int[k];
			for(int i=0; i<k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0,"",arr);
			System.out.println();
			
		}
	}
}
