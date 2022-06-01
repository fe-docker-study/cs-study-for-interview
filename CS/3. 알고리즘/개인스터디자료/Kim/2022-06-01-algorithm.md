기존 알고리즘 모음  

# 그리디 알고리즘(Greedy Algorithm)
당장 눈 앞에 최적의 상황만을 쫓는 알고리즘  
극단적인 경우로 정렬 기법이 함께 쓰인다  

<br>

# 문제 분석
카약을 더 챙겨온 팀은 -1 혹은 +1한 팀 번호의 팀에게만 대여 가능  
전체 팀에 대한 리스트를 생성 뒤  
카약이 부러진 팀에 대한 정보, 여분 카약이 있는 팀에 대한 정보를 리스트에 담기  
그러고 두 번째 줄에서 입력받은 카약 부서진 팀의 값과 일치하는 지 비교  
만약 리스트 값에 카약 부서진 팀 번호가 없으면 변수 answer +1  

<br>

# 처음 코드
```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 전체 팀의 수
		int S = Integer.parseInt(st.nextToken()); // 카약이 부러진 팀의 수
		int R = Integer.parseInt(st.nextToken()); // 여분 카약이 있는 팀의 수
		
		int team[] = new int[N]; // 전체 팀에 대한 배열
		
		// 모든 팀에게 카약을 1로 분배
		for(int i=0; i<N; i++) {
			team[i] = 1;
		}
		
		// 카약이 부러진 팀을 입력 받음
		StringTokenizer s = new StringTokenizer(br.readLine(), " ");
		
		// 카약이 부러진 팀을 전체 배열에도 표시(0)
		for(int i=0; i<S; i++) {
			int sNum = Integer.parseInt(s.nextToken()) - 1;
			team[sNum] -= 1;
		}
		
		// 여분 카약이 있는 팀을 입력 받음
		StringTokenizer r = new StringTokenizer(br.readLine(), " ");
		
		// 여분 카약이 있는 팀을 전체 배열에 표시(2)
		for(int i=0; i<R; i++) {
			int rNum = Integer.parseInt(r.nextToken()) - 1;
			team[rNum] += 1;
		}
		
		// 여분 카약이 있는 팀은 2를 가지고 있고 카약이 부러진 팀은 0을 가지고 있음
		// 카약이 부러진 팀 전에 2를 가지고 있다면 team[i-1] = 1로 바꾸고 team[i]=1으로 변경
		// 왼쪽한테 빌려주기 > 안되면 오른쪽한테 빌리기로 진행
		// team[0]와 team[last index]일 경우는 따로 구함
		
		for(int i=0; i<N; i++) {
			
			if(team[i] == 2) {
				if(team[i-1] == 0) {
					team[i-1] = 1;
					team[i] = 1;
					
				}else if(team[i+1] == 0) {
					team[i] = 1;
					team[i+1] = 1;
				}
				
			}
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			if(team[i]==0) {
				answer += 1;
			}
		}
		
		System.out.println(answer);
		bw.flush();
		bw.close();
		br.close();	
	}	
}
```

이러고 런타임 오류가 떴다  
생각해보니 team[N]은 배열 범위 밖이다  
그리고 i=0인데 team[0]==2일 경우  
조건식에 따라 team[i-1]을 구하게 되는데 이것도 범위 밖이다  

<br>

# 정답 코드
```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 전체 팀의 수
		int S = Integer.parseInt(st.nextToken()); // 카약이 부러진 팀의 수
		int R = Integer.parseInt(st.nextToken()); // 여분 카약이 있는 팀의 수
		
		int team[] = new int[N]; // 전체 팀에 대한 배열
		
		// 모든 팀에게 카약을 1로 분배
		for(int i=0; i<N; i++) {
			team[i] = 1;
		}
		
		// 카약이 부러진 팀을 입력 받음
		StringTokenizer s = new StringTokenizer(br.readLine(), " ");
		
		// 카약이 부러진 팀을 전체 배열에도 표시(0)
		for(int i=0; i<S; i++) {
			int sNum = Integer.parseInt(s.nextToken()) - 1;
			team[sNum] -= 1;
		}
		
		// 여분 카약이 있는 팀을 입력 받음
		StringTokenizer r = new StringTokenizer(br.readLine(), " ");
		
		// 여분 카약이 있는 팀을 전체 배열에 표시(2)
		for(int i=0; i<R; i++) {
			int rNum = Integer.parseInt(r.nextToken()) - 1;
			team[rNum] += 1;
		}
		
		// 여분 카약이 있는 팀은 2를 가지고 있고 카약이 부러진 팀은 0을 가지고 있음
		// 카약이 부러진 팀 전에 2를 가지고 있다면 team[i-1] = 1로 바꾸고 team[i]=1으로 변경
		// 왼쪽한테 빌려주기 > 안되면 오른쪽한테 빌리기로 진행
		// team[0]와 team[last index]일 경우는 따로 구함
		
		for(int i=0; i<N; i++) {
			
			if(i==0) {
				if(team[0] == 2 && team[1] == 0) {
					team[0] = 1;
					team[1] = 1;
				}
				
			}else if(i == N-1) {
				if(team[i] == 2 && team[i-1] == 0) {
					team[i] = 1;
					team[i-1] = 1;
				}
				
			}else if(team[i] == 2) {
					if(team[i-1] == 0) {
						team[i-1] = 1;
						team[i] = 1;
						
					}else if(team[i+1] == 0) {
						team[i] = 1;
						team[i+1] = 1;
					}
			}
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			if(team[i]==0) {
				answer += 1;
			}
		}
		
		System.out.println(answer);
		bw.flush();
		bw.close();
		br.close();	
	}	
}
```

```
int main() {
	string s1, s2; //번호1, 번호2
	string add; //새로 만든 문자열
	string res; //새로 만든 문자열에서 번호 궁합을 한 결과
 
	cin >> s1;
	cin >> s2;
 
	//두 개의 문자열을 서로 번갈아가며 새로운 문자열을 만든다.
	for (unsigned int i = 0; i < s1.size(); i++) {
		add = add + s1[i] + s2[i];
	}
 
	//새로 만든 문자열의 길이가 2가 되기 전까지 반복한다.
	while (add.size() != 2) {
		for (unsigned int i = 0; i < add.size() - 1; i++) { //인접한 문자열을 서로 더하기 때문에 문자열 길이에서 -1까지를 범위로 잡는다.
			res = res + char('0' + ((add[i] - '0') + (add[i + 1] - '0')) % 10); //새로운 결과를 갱신
		}
		add = res; //새로 만든 문자열을 번호 궁합한 결과로 갱신
		res.clear(); //번호 궁합 결과 문자열 초기화
	}
 
	cout << add;
}
```
