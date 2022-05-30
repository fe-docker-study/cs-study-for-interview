# Service Discovery

- 네트워크 상의 서비스 제공하는 디바이스와 서비스를 자동으로 감지하는 서비스
- service discovery와 분산 configuration 관리는 마이크로서비스 아키텍처에서 중요한 부분
- 모든 서비스는 시작 후 다른 서비스가 접근 할 수 있는 하나의 중앙 장소에 자신을 등록하고, 각 서비스는 디스커버리 서버에 등록된 다른 서비스의 목록을 얻을 수 있음.
- 유연한 키-값 저장소에 특정 키와 값을 저장

# Service discovery - Eureka

- 서비스 디스커버리로 제공되는 유레카는 클라이언트와 서버로 구분

### 서버

- 서버는 독립적인 스프링부터 애플리케이션으로 설정되고 실행되는데, 각 서버의 상태를 다른 서버에 복제해 설정하고 배포함으로써 가용성이 높음
- 서버는 전용 스프링 부트 애플리케이션으로 실행
- 서버 API는 등록된 서비스의 목록을 수집하기 위한 API와 새로운 서비스를 네트워크 위치 주소와 함께 등록하기 위한 API로 구성됨

### 클라이언트

- 유레카 클라이언트를 프로젝트에 포함시키려면 spring-cloud-starter-eureka 스타터를 사용
- 클라이언트는 항상 애플리케이션의 일부로 원격의 디스커버리 서버에 연결하는 일을 담당
- 클라이언트는 마이크로서비스 애플리케이션에 의존성을 포함해 사용함
- 클라이언트는 애플리케이션 시작 후 등록과 종료 전 등록 해제를 담당하고 유레카 서버로부터 주기적으로 최신 서비스 목록을 받아옴
- 연결되면 서비스 이름과 네트워크 위치를 담은 등록 메시지를 보냄
- 현재 마이크로서비스가 다른 마이크로 서비스의 종단점을 호출해야할 경우 클라이언트는 서버로부터 등록된 서비스 목록을 담은 최신의 configuration을 가져옴

## 서버 ↔클라이언트

- 클라이언트는 자신을 등록하고 호스트, 포트, 상태 정보 URL, 홈페이지 URL을 보냄 → 서버는 서비스의 각 인스턴스로부터 생존신호(heartbeat) 메시지를 받음 → 설정된 기간 동안 하트비트 메시지를 받지 못하면 레지스트리에서 서비스가 삭제됨
- 클라이언트는 서버로부터 데이터를 가져와서 캐싱하고 주기적으로 변경사항을 점검
  - @EnableDiscoveryClient 어노테이션을 메인 클래스에 추가해 활성화할 수 있음(spring-cloud-commons에 존재)
  - 컨설,유레카, 주키퍼 등 다수의 클라이언트 구현체가 클래스 경로상에 있을 경우 @EnableEurekaClient 어노테이션을 사용할 수 있음(spring-cloud-netflix에 존재하고 유레카만을 위해 존재)

## 서버 측에서 유레카 서버 실행

### pom.xml

- 프로젝트에 의존성 추가

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

### DiscoveryserviceApplication.java

- 메인 애플리케이션 클래스에 유레카 서버 등록

  @EnableEurekaServer : Eureka를 사용하기 위해서는 서버의 자격으로 등록, 서비스 디스커버리로서 프로젝트를 기동시킴

```java
package com.example.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryserviceApplication.class, args);
    }

}
```

### application.yml(or application.properties 하나만 작성)

- 서버 스타터에 클라이언트 의존성도 포함되어 있기 때문에 configuration에서 discovery client를 false로 설정해 비활성화 시킴 (디스커버리 인스터스를 고가용성 모드로 동작할 경우 디스커버리 인스턴스 사이의 peer-to-peer에 유용하고, 단일 인스턴스로 실행할 경우에는 시작 시에 에러로그만 찍을 뿐 유용하지 않음)

```yaml
server:
  port: 8761 #유레카는 웹서비스의 성격으로 기동됨에 있어서 포트번호가 필요

spring:
  application:
    name: discoveryservice # 마이크로 서비스의 고유한 ID 설정
  cloud:
    config:
      uri: <http://127.0.0.1:8888>
      name: ecommerce

eureka:
  client:
    register-with-eureka: false # defalut 값은 true 서버로서 기동만 하기 위해 false로 설정
    fetch-registry: false
```

### 실행

- 스프링 클라우드 애플리케이션을 시작(IDE에서 메인 클래스 실행 or 메이븐으로 빌드)

- 서버가 완전히 실행되면 간단한 UI 대시보드가 http://localhost:8761에서 서비스 됨

- /eurake/* 경로로 HTTP API 메서드를 호출할 수 있음

  - 서버목록 :  http://localhost:8761/eureka/apps

    

## 클라이언트 측에서 유레카 활성화

### pom.xml

- 프로젝트에 의존성 추가

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka</artifactId>
</dependency>
```

### ClientApplication.java

- @EnableDiscoveryClient 어노테이션을 메인 클래스에 추가해 활성화할 수 있음(spring-cloud-commons에 존재)
- 컨설,유레카, 주키퍼 등 다수의 클라이언트 구현체가 클래스 경로상에 있을 경우 @EnableEurekaClient 어노테이션을 사용할 수 있음(spring-cloud-netflix에 존재하고 유레카만을 위해 존재)

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

### application.yml(or application.properties 하나만 작성)

- 디스커버리 서버주소가 기본 호스트와 포트에서 서비스 중이면 클라이언트에 설정할 필요없음. 그러나 다를 경우 설정해줘야함
- 디스커버리 서버 네트워크 주소는 EUREKA_URL, 클라이언트 리스닝 포트는 FORT 환경변수로 정의, 디스커버리 서버에 등록될 어플리케이션 이름은 [spring.application.name](http://spring.application.name) 속성 사용