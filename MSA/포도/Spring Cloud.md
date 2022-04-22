# Spring Cloud
스프링 클라우드를 이용하여 애플리케이션을 구축하려고 할 때 기본적으로 구성해야 하는 내용
+ Centralized configuration management : 유지보수가 쉬워짐
    - Spring Cloud Config Server 
+ Location transparency : 서비스의 등록과 위치 정보 확인, 검색
    - Naming Server (Eureka)
+ Load Distribution (Load balancing) : 요청 정보 분산
    - Ribbon (Client Side) 
    - Spring Cloud Gateway
+ Easier REST Clients
    - FeignClient
+ Visibility and monitoring : 외부 모니터링, 로그 추적
    - Zipkin Distributed Tracing
    - Netflix API gateway
+ Fault Tolerance : 장애를 빠르게 복구
    - Hystrix  
<br>

## Service Discovery
MSA와 같은 분산 환경은 서비스 간의 원격 호출로 구성이 된다. 원격 서비스 호출은 IP 주소와 포트를 이용한다. 클라우드 환경이 되면서 서비스가 오토스케일링 등에 의해 동적으로 생성되거나 컨테이너 기반의 배포로 인해 서비스의 IP가 동적으로 변경되는 일이 많아졌다.  
따라서 서비스 클라이언트가 서비스를 호출할 때 서비스의 위치(IP주소와 포트)를 알아낼 수 있는 기능이 필요하다. 이를 Service Discovery라고 한다.

외부에서 다른 서비스들이 micro service를 검색하기 위해 사용되는 개념이다.

### Client side discovery
Service discovery 기능을 구현하는 방법 중 하나로 service 클라이언트가 service registry에서 서비스의 위치를 찾아 호출하는 방식
+ 상대적으로 간단하다.
+ 클라이언트가 사용 가능한 서비스를 알기 때문에 알맞는 로드밸런싱 방법을 선택할 수 있다.  
<br>

### Server side discovery
Service discovery 기능을 구현하는 방법 중 하나로 호출이 되는 서비스 앞에 proxy 서버(로드밸런서)를 넣는 방식
+ 클라이언트에서 Discovery 기능을 분리할 수 있다.  
<br>

### Spring Cloud Netfilx Eureka
서비스 디스커버리 서버로 인스턴스의 상태를 동적으로 관리하는 서버이다.   
새로운 인스턴스는 시작할 때 Eureka 서버에 IP, 호스트 주소, 포트 정보 등을 스스로 전송한다. Eureka 서버는 받은 정보를 가지고 일정한 간격으로 상태를 체크하면서 해당 인스턴스를 관리한다. 인스턴스가 새로 실행될 때마다 자신의 정보를 서버에 동적으로 등록한다.  
<br>

#### Eureka 예제
1. 서버 설정  
    Eureka 서버로 설정하기 위해서는 내장 was가 실행되는 코드에 ```@EnableEurekaServer```라는 어노테이션을 추가하여야 한다.

    yaml 파일은 아래와 같이 설정한다.
    ```yaml
    server:
    port: # 포트번호 지정

    spring: 
    application:
        name: # 마이크로 서비스의 이름 지정 (변경 가능)

    eureka:
    client:
        register-with-eureka:  
        fetch-registry: 
        # 유레카 서버에 서비스를 등록하는 옵션으로 true/false로 지정
    ```
2. 마이크로 서비스 생성  
    마이크로 서비스의 경우 client로 설정해 주는 어노테이션인 ```@EnableDiscoveryClient```을 추가하여야 한다.
    
    yaml 파일은 아래와 같이 설정한다.
    ```yaml
    server:
    port: # 포트번호 지정

    spring: 
    application:
        name: # 이름 지정

    eureka:
    client:
        register-with-eureka: true 
        fetch-registry: true
        service-url: # server의 위치
          defaultZone: # Eureka server의 위치값(localhost/127.0.0.1/ip 사용)
    ```