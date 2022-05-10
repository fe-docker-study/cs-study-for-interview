import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		boolean[] crashed = new boolean[n+1]; // 팀의 카약 부서짐 여부 
		int[] spare = new int[r];		// spare가 있는 팀의 번호
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<s; i++) {
			int teamNum = Integer.parseInt(st.nextToken());
			crashed[teamNum] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<r; i++) {
			spare[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(spare);
		
		int repair = 0;
		for(int i=0; i<r; i++) {
			int spareTeamNum = spare[i];
			if(crashed[spareTeamNum]) {
				repair++;
				crashed[spareTeamNum] = false;
				continue;
			}
			if(crashed[spareTeamNum-1]) {
				repair++;
				crashed[spareTeamNum-1] = false;
				continue;
			}
			if(spareTeamNum!=n && crashed[spareTeamNum+1]) {
				repair++;
				crashed[spareTeamNum+1] = false;
				continue;
			}
		}
		System.out.println(s-repair);
		
	}
}
