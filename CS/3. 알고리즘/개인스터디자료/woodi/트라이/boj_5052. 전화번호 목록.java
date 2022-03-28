import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int test =0;
		
		
		while(test++ < t) {
			int n = Integer.parseInt(br.readLine());
			String[] nums = new String[n];
			for(int i=0; i<n; i++) {
				nums[i] = br.readLine();
				
			}
			
			Arrays.sort(nums);
			
//			System.out.println(Arrays.toString(nums));
			
			String temp = nums[0];
			boolean flag = false;
			for(int i=1; i<n; i++) {
				if(nums[i].startsWith(temp)) {
					flag = true;
					break;
				}else {
					temp = nums[i];
				}
			}
			
			if(flag) {
				System.out.println("NO");
			}else {
				System.out.println("YES");
			}
			
		}
	}
}
