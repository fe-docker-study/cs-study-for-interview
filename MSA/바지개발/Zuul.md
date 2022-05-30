## Spring Cloud Zuul

- JVM 기반의 라우터이며, 서버 측 부하분산과 일부 필터링을 수행.
- 넷플릭스에서는 zuul을 인증이나 부하 평균 분해, 정적 응답 처리, 부하 테스트에 사용
- Zuul 서버는 마이크로서비스 아키텍쳐에서 전체 시스템의 진입점을 제공하는 API Gateway
- API Gateway는 API 요청자인 Client와 API 제공자인 backend service를 연결해 안전한 API 유통과 Client 요청별로 유연하게 대처
- API Gateway가 필요한 이유
  - 인증/인가
  - L/B & Routing
    - frontend의 요청을 라우팅함으로 점진적으로 레러시 시스템을 신규시스템으로 교체하거나 트래픽 일부만 새로운 서비스로 라우팅하는데 활용할 수 있음
  - Logging
  - Circuit Break
- 2018년 12월부터 기능 개선 없이 유지만함
- Spring boot 2.4.X부터는 zuul, hystrix가 더 이상 제공되지 않고, Spring cloud gateway사용을 권고

![Untitled](https://user-images.githubusercontent.com/90545926/169693020-ca315514-000d-4952-9c2e-8f17f92421f4.png)

- 보통 들어오는 요청을 ‘pre’filter에서 인증/인가처리
- routing filters에서 L/B, routing, circuit break를 처리 (L/B는 ribbon, routing은 zuul core, circuit break는 hystrix 라이브러리 사용)
- post filter에서 요청과 응담에 대한 Logging을 처리

### pom.xml

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

### ZuulServiceApplication.java

- @EnableZuulProxy 어노테이션 추가

```java
package com.example.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }

}
```

### application.yml

- 라우트는 url속성을 사용해 서비스의 네트워크 주소를 정적으로 구성
- 각 서비스는 단일 라우트의 path 컨피규레이션 속성을 사용해 경로를 설정
- http://localhost:8000/first-service/1 → http://localhost:8081/1
- 주울에서 유레카를 사용하는 서비스 디스커버리를 활성화하려면 spring-cloud-starter-eureka 스타터를 프로젝트 의존성에 포함하고, 어플리케이션의 메인 클래스에 @EnableDiscoveryClient 어노테이션을 사용해 클라이언트를 활성해야함. 디스커버리에 등록된 서비스의 목록만 가지고 올수 있음.

```xml
server:
  port: 8000

spring:
  application:
    name: my-zuul-service

zuul:
  routes:
    first-service:
      path: /first-service/**
      url: <http://localhost:8081>
    second-service:
      path: /second-service/**
      url: <http://localhost:8082>
```