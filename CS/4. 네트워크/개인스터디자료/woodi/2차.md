# 전달과 포워딩

### 전달 : 최종 목적지까지 패킷을 전달하는 방법

**1. 직접 전달 (direct delivery)**

- 최종 목적지가 전달자(deliverer)와 같은 네트워크에 연결되어 있는 호스트 (= 패킷의 발신지와 목적지가 같은 네트워크에 위치)
- 최종 라우터와 목적지 호스트 사이에 수행
- 목적지 주소(최종 라우터?)에서 netid를 추출한 후 자신의 네트워크 주소와 비교하여 같으면 직접 전달 수행
- 송신자는 ARP를 이용하여 목적지 IP 주소를 쿼리에 담아 브로드 캐스트하여 패킷을 전달한다.

<img width="689" alt="image" src="https://user-images.githubusercontent.com/47748246/144726952-2483b207-0c6c-44df-8963-34d7847c6325.png">


**2. 간접 전달 (indirect delivery)**
    - 최종 목적지가 같은 네트워크에 있지 않은 호스트
    - 최종 목적지와 같은 네트워크에 연결된 라우터에 도달할 때까지 여러 라우터를 경유해서 전달
    - 목적지 IP 주소와 라우팅 테이블을 이용하여 패킷이 전달되어야 하는 다음 라우터의 IP 주소를 찾는다
    
<img width="666" alt="image" src="https://user-images.githubusercontent.com/47748246/144726972-e88820d1-8768-4740-af92-4a7607af2ef4.png">


    

### 포워딩

1. Next Hop 방법
    
    <img width="672" alt="image" src="https://user-images.githubusercontent.com/47748246/144726984-096bbc10-b5d5-446c-b54e-936595d49da7.png">

    
    - 라우팅 테이블의 크기를 작게 만드는 기술 중 하나
    - 전체 경로에 대한 정보 대신 다음 홉 주소만 저장
2. 네트워크 지정 (Network-Specific) 방법
    
    <img width="672" alt="image" src="https://user-images.githubusercontent.com/47748246/144726988-1c425b70-d3a4-4769-8402-17832620c471.png">

    
    - 네트워크에 연결된 모든 호스트에 대해 각 호스트별 엔트리 대신에 네트워크에 대한 엔트리만 저장
3. 호스트 지정 (Host-Specific) 방법
    
    <img width="622" alt="image" src="https://user-images.githubusercontent.com/47748246/144726989-8ce93606-0a66-4f5d-b653-e19908314d37.png">

    
    - 라우팅 테이블에 호스트 주소 저장
    - 관리자가 라우팅 테이블을 제어할 때 사용
    - 경로 점검이나 보안성 제공에 매우 좋음
4. 디폴트 (Default) 방법
    
    <img width="675" alt="image" src="https://user-images.githubusercontent.com/47748246/144727006-55575871-8d5b-48cd-931a-088318b6423e.png">

    
    - 인터넷 상의 모든 네트워크 나열 대신 디폴트 엔트리만 지정

### 포워딩 과정

1. 목적지 주소를 추출
2. 주소를 오른쪽으로 28비트 쉬프트한 결과로부터 주소의 클래스를 알 수 있다.
3. 네트워크 주소를 찾는다. 
4. 클래스와 네트워크 주소를 사용하여 다음 홉 주소를 찾는다. 부합되는 엔트리가 없으면 디폴트가 사용된다. 직접 전달인 경우, 다음 홉 주소는 목적지 주소이다.
5. 다음 홉 주소와 인터페이스 번호를 ARP에게 전달한다. 

# 라우터의 구조

## Router의 2가지 key function

1. routing 
    - 라우팅 프로토콜 (RIP, OSPF, BGP)
    - 라우팅 알고리즘
2. forwarding 
    - Routing 결과에 따라서 패킷을 다음 홉으로 전달하는 것

## Router의 구조

<img width="735" alt="image" src="https://user-images.githubusercontent.com/47748246/144727011-7489614c-b5c9-4f53-a055-7f665b9f68be.png">


- Routing & Management Control Plane (Software)
    
    : 라우팅 프로토콜 메시지 주고 받음 (라우팅 기능 수행)
    
- Forwarding data plane (hardware)
    
    : 사용자의 데이터를 주고 받음 
    

### **Input port**

<img width="729" alt="image" src="https://user-images.githubusercontent.com/47748246/144727017-8a17fa3a-ac0f-410a-a628-0a85cc8102b2.png">


- line termination :  패킷을 모아서 하나의 프레임으로 만듦
- link layer protocol :  데이터가 온전한지, 잘 도착했는지 검사 (link layer 에서 하는일 수행)
- lookup, forwarding, queueing
    - lookup : 라우팅 테이블을 이용해 내보낼 output port를 결정한다.
    - forwarding : 결정된 output port로 전달한다.
    - queueing : datagram이 도착하는 속도가 switch fabric으로 나가는 속도보다 빠를 경우, buffer에 queueing 한다.

### Switching fabric

- Input port로 들어오는 데이터를 Output port로 전달
- Switching rate가 중요
- Switching fabric의 3가지 타입
    - memory : 첫 세대 라우터에서 이 방식을 선택.  input port의 패킷이 메모리에 복사되고, 이는 다시 output port에 복사됨. 이는 CPU에 의해 수행됨. memory bandwidth에 의해 속도가 결정됨
    - bus : 공유 버스에 의해 데이터 그램이 전달됨.  bus bandwidth에 의해 switching speed가 결정됨.
    - crossbar : bus bandwidth 제한을 극복하기 위해 나타난 방법.

<img width="718" alt="image" src="https://user-images.githubusercontent.com/47748246/144727036-d47fc1b9-35ee-4c8b-943c-c7deb13aa1e1.png">


### Output port

<img width="684" alt="image" src="https://user-images.githubusercontent.com/47748246/144727044-845305c3-2e61-49c2-8ad2-38816d1b0565.png">


- datagram buffer
    - Nework layer에 해당하는 작업 수행
    - buffering : datagram이 fabric으로 부터 들어오는 속도가 transmission 속도보다 클 경우, buffer에 queueing한다.
    - scheduling discipline : 어느 datagram을 먼저 transmission 할지 결정한다.
    

# Network Layer의 프로토콜

<img width="721" alt="image" src="https://user-images.githubusercontent.com/47748246/144727046-b5e2bf13-18b2-4cad-bda6-bbe723a8a953.png">


1. Routing Protocol 
    - 라우터에서 Control Plane 관련 프로토콜
    - 경로 결정  ⇒ RIP, OSPF, BGP
2. **IP 프로토콜** 
    - 라우터에서 Data Plane 관련 프로토콜
    - 데이터 전송 관련 프로토콜
        - datagram formating
        - packing handling coventions
3. **ICMP 프로토콜** 
    - error reporting
    - router "signaling"

## IPv4 프로토콜

### IP datagram format

<img width="726" alt="image" src="https://user-images.githubusercontent.com/47748246/144727053-9acd21db-d028-458e-806e-8e76ce6d2c28.png">


### IP fragmentation, reassembly

- 네트워크 link는 IP datagram을 전송할 수 있는 최대 크기 (MTU : Max Transfer Unit)이 정해져 있다.
- 따라서 MTU 보다 큰 IP datagram은 여러 개의 작은 IP datagram으로 나누어져야 하고, 이는 최종 도착지에서 다시 reassemble 된다.
- 이를 위해 IP 프로토콜의 헤더에는 16-bit identifier, flgs, fragment offset 필드가 있다.
    - length : 데이터 fragment의 크기
    - id : 해당 data fragment들이 모두 같은 data frame임을 알게 해줌
    - frgflag : 이 fragment 이후에 fragement가 더 있으면 1, 없으면 0
    - offset : 8byte당 1로 표현함. 따라서 첫 번째 fragment에 담긴 총 데이터가 1480 byte이므로 이를 8로 나눈 185가 담김, 이는 누적으로 담긴다. 그래서 이후 fragment의 offset이 1480*2/8=370인 것

<img width="718" alt="image" src="https://user-images.githubusercontent.com/47748246/144727058-0b1c1eb4-31ae-4e0e-bdff-b716ab45a51a.png">


## ICMPv4 프로토콜

### ICMP : Internet Control Message Protocol

- host와 host, router와 router, host와 router 간 네트워크 계층 제어 정보를 교환한다.
- 네트워크 계층 제어 정보란?
    - error reporting : 통신 불가능한(unreachable) host, network, port, protocol
    - echo request/reply : ping을 사용하여 검증한다.
- 같은 네트워크 계층 프로토콜이지만 ICMP가 IP 위에 있다. 즉, ICMP 프로토콜 메시지를 IP datagram으로 encapsulate 한다.
- ICMP 메시지는 type, code, 문제가 된 ip datagram의 first 8bytes 필드로 구성된다.
- 예를 들어, 도착지로 가는 경로에 있던 라우터에서 목적지로 데이터를 포워드하려는데 목적지 네트워크가 다운됨. → source에게 자기가 전달 못한 datagram의 first 8 byte를 담고 타입, 코드 담은 뒤 source로 돌려보낸다.

<img width="532" alt="image" src="https://user-images.githubusercontent.com/47748246/144727063-4e561326-506a-4805-8e00-19c431a48016.png">


# ARP

### MAC 주소와 IP 주소

- MAC 주소(2계층)는 하드웨어 생산 업체가 임의적으로 할당한 주소이고, NIC에 종속된 주소이다.
- IP 주소 (3계층)는 우리가 직접 할당하거나 DHCP를 이용해 자동으로 할당된다.
- 실제로 통신은 IP 주소를 기반으로 일어나고, MAC 주소는 상대방의 주소를 자동으로 알아내 통신한다.
- 상대방의 MAC 주소를 알아내기 위해 사용되는 프로토콜이 ARP 이다 .

### ARP란?

- 상대방의 MAC 주소를 알아내기 위한 프로토콜
    - ARP 브로드캐스트를 이용해 로컬 네트워크 전체에 MAC 주소를 질의한다.
    - ARP 브로드캐스트를 받은 목적지는 ARP 프로토콜을 이용해 자신의 MAC 주소를 응답한다.
    - 이 작업이 완료되면 출발지, 목적지 둘 다 상대방에 대한 MAC 주소를 학습하고 이후 패킷이 정상적으로 인캡슐레이션되어 상대방에게 전달될 수 있다.

<img width="682" alt="image" src="https://user-images.githubusercontent.com/47748246/144727074-98e13a4b-5f82-4cdf-a1fb-60bd29c29b83.png">


- ARP 캐시 테이블 : 패킷 네트워크에서는 큰 데이터를 잘라 전송하므로 여러 개의 패킷을 전송해야한다. 하지만 패킷을 보낼때마다 ARP 브로드캐스를 수행하면 네트워크 통신의 효율성이 크게 저하되므로 메모리에 이 정보를 일정 시간 ARP 테이블에 저장해 놓는다.
