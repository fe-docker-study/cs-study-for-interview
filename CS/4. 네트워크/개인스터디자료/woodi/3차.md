## 인터넷의 전송 방식
![image](https://user-images.githubusercontent.com/47748246/146143829-b34d120d-a9a0-4d78-ac82-42b3403f520a.png)

- 송신자와 수신자 관점에서 나누어 유니캐스트, 브로드캐스트, 멀티캐스트로 구분됨
- 유니캐스트 : 하나의 송신자가 다른 하나의 수신자로 데이터를 전송하는 방식. 일반적인 인터넷 응용프로그램이 모두 유니캐스트 방식을 사용하고 있다.
- 브로드캐스트 : 하나의 송신자가 같은 서브네트웍 상의 모든 수신자에게 데이터를 전송하는 방식
- 멀티캐스트 : 하나 이상의 송신자들이 특정한 하나 이상의 수신자들에게 데이터를 전송하는 방식으로 인터넷 화상 회의 등의 응용에서 사용

### 멀티캐스트 전송의 장점

- 그룹 통신을 위하여 다중 수신자들에게 동일한 데이터를 전송하고자 할 경우,
    - 유니캐스트 전송방식을 이용한다면 : 전송하고자 하는 패킷을 다수의 수신자에게 각각 여러 번 전송해야 함 → 동일한 패킷의 중복 전송 발생 → 네트웍 효율 저하
    - 멀티캐스트 전송 방식 : 송신자는 여러 수신자에게 한 번에 메시지가 전송되도록 하여, 데이터의 중복 전송으로 인한 네트웍 자원 낭비를 최소화할 수 있게 된다.

### 멀티캐스트 주소와 라우팅

**1) 멀티캐스트 전송 패킷**

- 멀티캐스트 전송이 일반적인 유니캐스트 인터넷 응용 분야와 다른 점은 우선 그 **전송 패킷**에 있다.
    - 일반적으로 TCP/IP 상의 인터넷 응용 프로그램은 데이터의 송신자가 이를 수신할 수신자의 인터넷 주소를 전송 패킷의 헤더에 표시해 패킷을 전송한다.
    - 그러나 **멀티캐스트 전송**을 위해서는 **헤더**에 수신자의 주소 대신 **수신자들이 참여하고 있는 그룹 주소**를 표시하여 패킷을 전송한다.

**2) 멀티캐스트 그룹 주소** 

- 멀티캐스트 전송을 위한 그룹 주소는 **D-class IP 주소 (224.0.0.0∼239.255.255.255)**로 전세계 개개의 인터넷 호스트를 나타내는 A, B, C-class IP 주소와는 달리 실제의 호스트를 나타내는 주소가 아니며, 그룹 주소를 갖는 멀티캐스트 패킷을 전송받은 수신자는 자신이 패킷의 그룹에 속해있는 가를 판단해 패킷의 수용여부를 결정하게 된다.

**3) 멀티캐스트 라우팅**

- 그러나 현재 인터넷 상의 라우터들이 대부분 유니캐스트만을 지원하기 때문에 멀티캐스트 패킷을 전송하기 위해서는 **멀티캐스트 라우터 사이에 터널링**이라는 개념을 사용하여 캡슐화된 패킷을 전송한다.

- 즉 멀티캐스트 주소를 가진 데이터 **패킷 헤더 앞에 멀티캐스트 라우터간에 설정된 터널의 양 끝단의 [](http://www.terms.co.kr/IPaddress.htm)IP 주소를 덧붙여 라우팅**을 함으로써 멀티캐스트를 지원하지 않는 일반 라우터들을 거칠 때 기존의 유니캐스트 패킷과 같은 방법으로 라우팅되어 최종적으로 터널의 종착지로 전송될 수 있게 하는 것이다.

## 라우팅 알고리즘과 프로토콜

- **라우팅 프로토콜**은 **라우팅 알고리즘이 필요로하는 네트워크 정보**를 제공한다.
    
    ⇒ 즉, 라우팅 프로토콜은 라우팅 알고리즘에 dependant하다. (무슨 알고리즘을 사용하냐에 따라서 내가 collect하는 정보, 정보를 collect 하는 방식 등이 달라진다.)
    
    ⇒ 따라서 라우팅 프로토콜에는 라우팅 알고리즘이 포함되어 있다. 
    

### 라우팅 알고리즘

**1) 목적**

- forwarding 테이블을 setting해서 실제 네트워크 패킷이 들어 왔을 때 forwarding 테이블을 lookup해서 패킷이 forward 될 수 있게 하는 것이 라우팅 알고리즘의 목적이다.
- 라우팅 알고리즘에서는 일반적으로 네트워크 Graph로 추상화(abstarction)한다. (이산수학)

![image](https://user-images.githubusercontent.com/47748246/146143905-b37b7c12-8ed5-4542-9d04-5c8ac03c978f.png)

- routing algorithm의 목적은 source부터 destination까지 최소 비용의 경로를 찾아내는 것이다.

**2) 라우팅 알고리즘 분류 (classification)**

- 라우팅 알고리즘에서 사용하는 정보가 무엇이냐에 따라 global과 decentralized로 분류되거나 static과 dynamic으로 분류되기도 한다.
- global vs decentralized
    - global
        - "link state" 알고리즘이라고도 불림
        - 각 라우터가 네트워크 전체 topology와  상태 정보를 갖고 있음
        - 이때, 각 라우터가 갖고있는 네트워크 정보가 모두 동일(consistent) 해야 함
    - decentralized
        - "distance vector" 알고리즘이라고도 불림
        - 각 라우터는 아주 지엽적인 정보만 알고 있음 : "나의 neighbor에는 누가있고, 그 neighbor와 나를 연결하는 link의 cost는 얼마다."
        - 그래서 경로를 탐색할때 neighbor들과 얘기한다. "나는 어떤 어떤 destination 서브넷에 갈 수 있는데 그 destination 서브넷으로 내가 가는 cost는 얼마야 ". 그리고 그 neighber들도 마찬가지로 자기 주변의 라우터들에게 얘기한다.  즉 distance vector를 교환
        - 이게 퍼지다보면 각 라우터는 어느 neighbor를 통해서 가면 destination까지 얼마의 cost로 갈 수 있는지를 알게 됨
            
            
- static vs dynamic
    - static : link cost를 static한 것을 사용함.
        - 예) 인터넷 처럼 link가 있으면 1, 없으면 무한대 → link cost가 변할 일이 거의 없다.
        - 혹은 링크의 길이로 링크의 cost를 구한다고 해도, 이 cost가 변할 일이 거의 없다.
        - 라우팅 알고리즘을 자주 실행할 필요가 없다.
    - dynamic : link cost를 dynamic한 것을 사용함
        - 가용한 bandwidth를 기준으로 cost를 정함.
        - 따라서 link cost를 라우터끼리 서로 자주 교환해야 함. 라우팅 알고리즘도 자주 실행해야 함
    

**3) routing algorithm**

- global (link state): Dijkstra's algorithm
- decentralized (distance vector ) : 벨만포드 알고리즘 (dynamic programming)
    
   ![image](https://user-images.githubusercontent.com/47748246/146143981-de02d1db-6ddd-4fc3-9484-cde5f73e6276.png)
    

### 라우팅 프로토콜 in Internet

**Intra-AS Routing**

- interior gateway protocols (IGP)라고도 불림
- intra-AS routing 프로토콜의 대표적인 예로 RIP, OSPF 등이 있음

**1) RIP (Routing Information Protocol)**

- 인터넷 초창기에 사용됨
- distance vector 알고리즘에 속한다.
    - 모든 link의 cost는 1로 가정. 따라서 distance metric이 hop의 개수 (최대 15개, 즉 16부터는 무한대를 의미)
    - 매 30초마다 advertisement 메시지에 distance vector를 심어서 이웃과 교환 (목적지 subnet의 개수는 최대 25개)
- 소규모의 네트워크에만 적용 가능
- 라우팅 프로토콜은 일반적으로 네트워크 계층의 프로토콜임. 근데 RIP는 사실 application-level 프로세스로 구현됨. 그 프로세스의 이름은 route-d(daemon)

**2) OSPF (Open Shortest Path First)**

- 인터넷 규모가 증가하면서 RIP의 제약점이 있음 → OSPF 등장 : 큰 규모의 AS에도 적용 가능
- 현재 인터넷에서 가장 많이 사용됨
- link state 알고리즘 사용
- 다양한 link cost 사용
- large domain에서는 hierarchical OSPF 적용한다.
    - two-level hierarchy : local area, backbone
        - link state advertisements only in **area**
    - Area border routers :  자신의 area에 속한 nets의 distance를 다른 Area Border router들과 교환한다.
    - backbone routers : run OSPF routing limited to backbone
    - boundary routers : AS 전체를 다른 AS와 연결시켜준다 .
![image](https://user-images.githubusercontent.com/47748246/146144026-d487e31a-3963-484a-89a9-4321178479ce.png)

**Inter-AS Routing**

- 각 AS 내부에서는 자신이 사용할 intra-AS routing 프로토콜을 선택할 수 있었다. (RIP or OSPF)
- 반면 Inter-AS Routing 프로토콜은 오직 BGP 하나만 존재한다.

**3) BGP (Border Gateway Protocol)**

- **eBGP** : 이웃 AS들로부터 접속 가능한 subnet 정보를 얻는다.  **obtain** subnet reachability information from neighboring ASs
- **iBGP** : 위 정보를 자신이 담당하는 AS 안에 소문낸다. **propagate** reachability information to all AS-internal routers
- **determine "good" routes** to other networks **based on** reachability information and **policy**
    - cf) intra-AS 라우팅 프로토콜 determine "good" routes to other networks based on "cost" (bandwidth etc)
- **BGP session** : 두 이웃한 BGP router들간에  semi-permanent TCP connections  맺고 → BGP message(BGP advertisement) 교환

## 전송계층

- 목적지 단말 안에서 동작하는 여러 애플리케이션 프로세스 중 통신해야 할 목적지 프로세스를 정확히 찾아가고 패킷 순서가 바뀌지 않도록 잘 조합해 원래 데이터를 잘 만들어 내는 역할

### TCP

![image](https://user-images.githubusercontent.com/47748246/146144054-539571b3-8a4b-495d-8112-aad85244fb8b.png)

- 패킷을 분할하고 조합하기 위해 TCP 프로토콜에서는 시퀀스 번호화 ACK 번호를 사용한다.

![image](https://user-images.githubusercontent.com/47748246/146144090-58bd5865-9f30-4e96-9307-94d3970bb2c3.png)

위 그림을 단계별로 설명하면 다음과 같다.

1. 출발지에서 시퀀스 번호를 0으로 보낸다. (SEQ = 0)
2. 수신 측에서는 0번 패킷을 잘 받았다는 표시로 응답 번호 (ACK)에 1을 적어 응답한다. 이때 수신 측에서는 자신이 처음 보내는 패킷이므로 자신의 패킷에 시퀀스 번호 0을 부여한다.
3. 이 패킷을 받은 송신 측은 SEQ 번호를 1로(수신 측이 ACK 번호로 1을 달라고 요청했으므로), ACK 번호는 상대방의 0번 시퀀스를 잘 받았다는 의미로 시퀀스 번호를 1로 부여해 다시 송신한다.

- 윈도 사이즈와 슬라이딩 윈도
    - 왕복 지연 시간(RTT)를 줄이기 위해 여러 패킷을 한번에 보내고 응답을 하나만 받는 방법
    - 윈도 사이즈 : 가능하면 최대한 많은 패킷을 한번에 보내는 것이 효율적이지만, 네트워크 상태가 안 좋으면 패킷 유실 가능성이 커지므로 적절한 송신량을 결정해야 함. 따라서 한 번에 데이터를 받을 수 있는 데이터 크기를 윈도 사이즈라 한다.
    - 슬라이딩 윈도 : 윈도 사이즈를 조절하는 것

- 3-way handshake
    
   ![image](https://user-images.githubusercontent.com/47748246/146144122-4b5bc1dc-2e23-46f5-9775-550df63907e4.png)
    
    - TCP에서는 유실없는 안전한 통신을 위해 통신 시작 전, 사전 연결 작업을 한다. ⇒ 3 way handshake
    - 진행 상황에 따라 상태(state) 정보를 부르는 이름이 모두 다르다
        - SYN - SYN,ACK - ACK
        

### UDP

![image](https://user-images.githubusercontent.com/47748246/146144157-b17e5525-8aea-4b81-b57a-ac2d88f90e58.png)

- TCP와 달리, 신뢰성 있는 데이터 전송을 보장하지 않음
- 음성 데이터나 실시간 스트리밍과 같이 시간에 민감한 프로토콜이나 애플리케이션을 사용하는 경우에 주로 사용

## 응용 계층 : DHCP

### DHCP 동작 방식

![image](https://user-images.githubusercontent.com/47748246/146144205-ce41478c-4b10-4b37-b50b-1710f7cee0a3.png)

- DHCP Discover
    
    : DHCP 클라이언트는 DHCP 서버를 찾기 위해 DHCP Discover 메시지를 브로드캐스트로 전송한다.
    
- DHCP Offer
    
    : DHCP Discover를 수신한 DHCP 서버는 클라이언트에 할당할 IP 주소화 서브넷, 게이트웨이, DNS 정보, Lease Time 등의 정보를 포함한 DHCP 메시지를 클라이언트로 전송한다.
    
- DHCP Request
    
    : DHCP 서버로부터 제안받은 IP 주소와 DHCP 서버 정보를 포함한 DHCP 요청 메시지를 브로드캐스트로 전송한다 .
    
- DHCP Acknowledgement
    
    : DHCP 클라이언트로부터 IP 주소를 사용하겠다는 요청을 받으면 DHCP 서버에 해당 IP를 어떤 클라이언트가 언제부터 사용하기 시작했는지 정보를 기록하고 DHCP Request 메시지를 정상적으로 수신했다는 응답을 전송한다. 
    

## DNS (Domain Name System)

- 도메인 주소를 IP 주소로 변환하는 역할

**1) 구조**

![image](https://user-images.githubusercontent.com/47748246/146144230-aea82cb7-8b60-4e41-9e25-1fba8f378d22.png)

- **Distributed database** implemented in hierarchy of many name servers
- 클라이언트가 도메인 네임 요청 시, DNS를 통해 질의하는 과정
    
    Q. host at [cis.poly.edu](http://cis.poly.edu) wants IP address for gaia.cs.umass.edu
    
    i) iterated query 
    
   ![image](https://user-images.githubusercontent.com/47748246/146144251-48347f67-2f4b-4c04-90f2-5f721eeb74f8.png)
    
    ii) recursive query
    
    ![image](https://user-images.githubusercontent.com/47748246/146144273-d9353a7a-75d8-404b-b599-4288162923a6.png)
    

**2) 동작방식**
![image](https://user-images.githubusercontent.com/47748246/146144318-5f8cafa7-eae2-43c1-9ef5-54b7ea778315.png)

- 도메인을 IP 주소로 변환하려면 DNS 서버에 도메인 쿼리하는 과정을 거쳐야한다.
- 하지만 DNS 서버 없이 로컬에 도메인과 IP를 직접 설정 가능 ⇒ hosts 파일
    - hosts 파일에 도메인과 IP 주소를 직접 설정해두면 해당 도메인 리스트는 항상 DNS 캐시에 저장된다.
