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