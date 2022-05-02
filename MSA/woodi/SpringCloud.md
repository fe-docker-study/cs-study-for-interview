## discoveryservice - Service discovery

- spring cloud Netflix Eureka
    - 얘가 하는 역할을 service discovery라고 한다.
- service discovery : 외부에서 다른 서비스들이 마이크로 서비스를 검색하기 위해서 사용하는 서비스 (전화번호부 같은거) → 어떤 서비스가 어느 위치에 있는지 key value 형태로 저장
    - 등록 기능
    - 검색 기능

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/42b5289e-46bb-44a7-a968-a9298d6d85d5/Untitled.png)

- Naming Server
- 여기에 microservice 서버, gateway 서버 등을 등록해 놓고 위치를 검색한다.

```java
package com.example.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer    // eurekaServer의 자격으로 뜨도록 한다. 
public class DiscoveryserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryserviceApplication.class, args);
    }

}
```

```yaml
server:
  port: 8761

spring:
  application:
    name: discoveryservice
  cloud:
    config:
      uri: http://127.0.0.1:8888
      name: ecommerce

// 안하면 default 값으로 true가 세팅됨.
// 이는 자기 자신을 eureka client로 등록한다는 뜻 -> 쓸모 없다. 그래서 false로 해준다.
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

## apigateway-service - Spring Cloud Gateway

1. **API Gateway Service의 역할과 특징**
    - 인증 및 권한 부여
    - 서비스 검색 통합
    - 응답 캐싱
    - 정책, 회로 차단기 및 Qos 다시 시도
    - 속도 제한
    - 부하 분산 : 서버의 요청 정보를 분산하기 위한 용도 (LoadBalancing)
    - 로깅, 추적, 상관 관계
    - 헤더 , 쿼리 문자열 및 청구 변환
    - IP 허용 목록에 추가
    
2. **API Gateway Service를 구현하기 위한 방법 : Netflix Riboon과 Zuul**
    - Spring Cloud에서의 MSA간 통신
        - RestTemplate
        - Feign Client : 마이크로 서비스 이름으로 자신이 가진 api 처럼 자유롭게 호출 가능
    
    - 로드 밸런서를 어디에 구축할 것인가?
        
        1) 초창기 : Netflix Ribbon
        
        - Client side Load Balancer : 클라이언트 측 내부에 로드밸런서 구축 - 마이크로 서비스 이름으로 호출
        - spring boot 2.4에서 maintenence 상태 → 지금은 거의 사용 안함
    
    2) Netflix zuul
    
    - 기능
        - Routing
        - API gateway
    - spring boot 2.4에서 maintenence 상태 → 지금은 거의 사용 안함 → spring Cloud gateway로 대체

## config-service - Configuration service

- 분산 시스템에서 서버, 클라이언트 **구성에 필요한 설정 정보(application.yaml)**를 **외부 시스템에서 관리**
    - ex) gateway ip
    - Spring cloud config server를 이용해서 환경 설정에 대한 정보를 외부의 저장소(e.g. git)에다가 저장해 놓음. 다른 microservices들은 이 환경 설정 정보를 참고함.
    - 따라서 환경 설정 정보가 변경 될 경우, microservices들을 변경할 필요가 없다 . 그냥 Spring cloud config server만 수정하면 됨
- 하나의 중앙화된 저장소에서 구성 요소 관리 가능
- 각 서비스를 다시 빌드하지 않고, 바로 적용 가능
- 애플리케이션 배포 파이프라인을 통해 DEV-UAT-PROD 환경에 맞는 구성 정보 사용

```java
package com.example.configservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import java.util.Enumeration;
import java.util.Properties;

@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
```
