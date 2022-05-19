# MSA 적용 개발 가이드

## MSA (마이크로 서비스 아키텍처)

### 1. MSA 정의

마이크로서비스(microservice) : 애플리케이션을 느슨히 결함된 서비스의 모임으로 구조화하는 서비스 지향 아키텍처(SOA) 스타일의 일종인 소프트웨어 개발 기법으로 모듈성을 개선시키고 애플리케이션의 이해, 개발, 테스트를 더 쉽게 해 준다.



![image-20220517170651045](https://github.com/fe-docker-study/cs-study-for-interview/blob/main/MSA/%ED%8F%AC%EB%8F%84/%EB%AA%A8%EB%86%80%EB%A6%AC%EC%8B%9D%EA%B3%BC_%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4_%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98_%EB%B9%84%EA%B5%90.png)

위 그림처럼 각각의 마이크로 서비스는 자체 비즈니스 계층과 데이터베이스를 가지고 있다. 따라서 수정 시에도 다른 마이크로 서비스에 영향을 주지 않는다.
일반적으로 마이크로 서비스는 HTTP, REST 같은 널리 채택된 lightweight 프로토콜 또는 JMS, AMQP와 캍은 메시징 프로토콜을 사용하여 서로 통신한다.



마이크로서비스 아키텍처의 특징

+ 애플리케이션 로직을 각자 책임이 명확한 작은 컴포넌트들로 분해하고, 이를 조합해 솔루션을 제공한다.
+ 각 컴포넌트는 작은 책임 영역을 담당하고 완전히 상호 독립적으로 배포된다. 
+ 마이크로서비스는 몇 가지 기본 원칙에 기반을 두며, 서비스 소비자와 서비스 제공자 사이의 데이터 교환을 위해 HTTP와 JSON 같은 경량 통신 프로토콜을 사용한다.
+ 애플리케이션은 항상 기술 중립적 프로토콜을 사용해 통신하므로 서비스 구현 기술과는 무관하다. 따라서 마이크로서비스 기반의 애플리케이션을 다양한 언어와 기술로 구축할 수 있다.

<br>

### 2. MSA 목적

MSA는 다음과 같은 장점들을 통해 시스템에 대한 개발 및 운영 복잡성을 효율적으로 낮출 수 있다.

+ Microservice는 독립적으로 구성될 수 있으며, 상호 독립적으로 구축 및 운영될 수 있다.
+ 특정 서비스만 집중할 수 있고, 코드 규모가 작아 효율적인 유지보수가 가능하다.
+ Restful API와 같이 lightweight한 통신을 통해 효과적인 상호 연계가 가능하다.
+ 독립적인 서비스 단위 확장(scale-out)을 지원하기 때문에 효율적으로 시스템 자원을 활용할 수 있다.
+ 개별로 서비스 배포가 가능하기 때문에 수시로 필요에 따라 배포할 수 있다.

<br>

### 3. 12-Factor App 방법론

12-Factor App은 다음과 같은 특징을 가진 SaaS(Software As A Service) 앱을 만들기 위한 방법론이다.

+ 선언적 형식으로 설정을 자동화하여 새로운 개발자가 프로젝트에 참여하는 데 드는 시간과 비용을 최소화한다.
+ OS에 따라 달라지는 부분을 명확하게 하고, 실행환경 사이의 이식성을 극대화한다.
+ 클라우드 플랫폼 배포에 적합하고, 서버와 시스템의 관리 부담을 없앤다.
+ 개발환경과 운영환경의 차이를 최소화하여 민첩성을 극대화하고 지속적인 배포가 가능하다.
+ 툴, 아키텍처, 개발방식을 크게 바꾸지 않고 서비스를 확장(scale-up)할 수 있다.

<br>

12-Factor 방법론은 어떤 프로그래밍 언어로 작성된 앱에도 적용할 수 있고, 백엔드 서비스(데이터베이스, 큐, 메모리 캐시 등)와 다양한 조합으로 사용할 수 있다.

<br>

12-Factor 방법론

1. 코드베이스

   + 버전 관리되는 하나의 코드베이스로 여러 곳에 배포

2. 종속성

   - 의존 관계를 명시적으로 선언하고 분리
   - 환경에 의존하지 않도록 함

3. 설정

   - 설정 정보는 애플리케이션 코드와 분리하고 환경 변수에 저장

4. 백엔드 서비스

   - 백엔드 서비스를 연결된 리소스로 취급

5. 빌드, 릴리스, 실행

   - 빌드, 릴리스, 실행의 3단계를 엄격하게 분리

6. 프로세스

   - 응용 프로그램을 하나 또는 여러 개의 독립적인 프로세스로 실행

7. 포트 바인딩

   - 포트 바인딩을 통해 서비스를 공개

8. 동시성

   - 프로세스 모델에 따라 수평적 확장

9. 폐기 용이성

   - 빠른 시작이 가능하며, Graceful Shutdown 시 서비스에 영향을 미치지 않도록 하여 안정성 극대화

     *※  Graceful Shutdown : 정상적인 종료로써 소프트웨어 기능으로 컴퓨터를 끄거나 OS가 프로세스를 안전하게 종료하고 연결을 해제하는 작업을 수행할 수 있는 경우이다.*

10. 개발/운영 일치

    - 개발 환경과 운영 환경을 최대한 동일하게 유지
    - CI/CD 환경이 갖춰져 있어야 함

11. 로그

    - 로그를 이벤트 스트림으로 취급함
    - 중앙 집권적인 서비스를 통해 로그 이벤트를 수집하고, 인덱싱하여 분석하는 환경이 가능해야 함

12. 관리 프로세스

    - 관리 작업을 일회성 프로세스로 실행

<br>

### 4. Service Mesh

Service Mesh : 각 마이크로서비스 간통신을 최적화하고 다운 타임을 방지하며 전체 서비스를 관리하기 위한 Outer Architecture로 복잡한 내부 네트워크의 추상화를 통해 서비스 간의 통신이 빠르고, 안정적이며, 신뢰성을 보장한다.

<br>

Service Mesh의 주요 기능

- Configuration Management : 설정 변경 시 서비스의 재빌드와 재부팅 없이 즉시 반영
- Service Discovery : API Gateway가 서비스를 검색하는 매커니즘
- Load Balancing : 서비스 간 부하 분산
- API Gateway : API가 서버 앞단에서 API 엔드포인트 단일화 및 인증, 인가, 라우팅 기능 담당
- Centralized Logging : 서비스별 로그의 중앙집중화 
- Centralized Metrics : 서비스별 메트릭 정보의 중앙집중화 
- Distributed Tracing : 서비스간 호출 추적과 성능, 분석 관리 
- Resilience & Fault Tolerance : 서비스간 장애 전파 차단 
- Auto Scaling & Self Healing : 자동 스케일아웃과 복구 자동화 
- Packaging, Deployment & Scheduling : 패키징, 빌드 및 배포 자동화
- Test Automation : 서비스 테스트 자동화

<br>

Service Mesh 적용 방안

- Kubernetes의 Istio 솔루션 사용
- Spring Cloud 기반 Service Mesh 구축

<br>

Spring Cloud와 Kubernetes의 기술 요소 매핑

| MSA 관심사                         | Spring Cloud                            | Kubernetes                                         |
| ---------------------------------- | --------------------------------------- | -------------------------------------------------- |
| Configuration Management           | Config Server, Consul, Netflix Archaius | Kubernetes ConfigMap & Secrets                     |
| Service Discovery                  | Netflix Eureka, Hashicorp Consul        | Kubernetes Service & Ingress Resources             |
| Load Balancing                     | Netflix Ribbon                          | Kubernetes Service                                 |
| API Gateway                        | Netflix Zuul                            | Kubernetes Service & Ingress Resources             |
| Service Security                   | Spring Cloud Security                   | -                                                  |
| Centralized Logging                | ELK Stack (LogStash)                    | EFK Stack (Fluentd)                                |
| Centralized Metrics                | Netflix Spectator & Atlas               | Heapster, Prometheus, Grafana                      |
| Distributed Tracing                | Spring Cloud Sleuth, Zipkin             | OpenTracing, Zipkin                                |
| Resilience & Fault Tolerance       | Netflix Hystrix, Turbine & Ribbon       | Kubernetes Health Check & resource isolation       |
| Auto Scaling & Self Healing        | -                                       | Kubernetes Health Check, Self Healing, Autoscaling |
| Packaging, Deployment & Scheduling | Spring Boot                             | Docker/Rkt, Kubernetes Scheduler & Deployment      |
| Job Management                     | Spring Batch                            | Kubernetes Jobs & Scheduled Jobs                   |
| Singleton Application              | Spring Cloud Cluster                    | Kubernetes Pods                                    |

<br>

마이크로서비스 필요조건에 따른 범위

![image-20220517170651045](https://github.com/fe-docker-study/cs-study-for-interview/blob/main/MSA/%ED%8F%AC%EB%8F%84/%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4_%ED%95%84%EC%9A%94%EC%A1%B0%EA%B1%B4%EC%97%90_%EB%94%B0%EB%A5%B8_%EB%B2%94%EC%9C%84.png)

<br>

Kubernetes와 결합된 Spring Cloud의 예

| Capability                   | Spring Cloud 와 쿠버네티스                               |
| ---------------------------- | -------------------------------------------------------- |
| DevOps                       | Self service, multi-environment capabilities             |
| Auto Scaling & Self Healing  | Pod/Cluster Autoscaler, **HealthIndicator**, Scheduler   |
| Resilience & Fault Tolerance | **HealthIndicator, Hystrix**, HealthCheck, Process Check |
| Distributed Tracing          | Zipkin                                                   |
| Centralized Metrics          | Heapster, Prometheus, Grafana                            |
| Centralized Logging          | EFK                                                      |
| Job Management               | **Spring Batch**, Schedulled Job                         |
| Load Balancing               | **Ribbon**, Service                                      |
| Service Discovery            | Service                                                  |
| Configuration Management     | **Spring Cloud Kubernetes**, ConfigMap, Secret           |
| Service Logic                | **전자정부 표준프레임워크 v4.0 (Spring Framework 5.x)**  |
| Application Packaging        | **Spring Boot**                                          |
| Develoyment & Scheduling     | Deployment strategy, A/B, Canary, Scheduler strategy     |
| Process Isolation            | Docker, Pods                                             |
| Environment Management       | Namespaces, Authorizations                               |
| Resource Management          | CPU and memory limits, Namespace resource quotes         |

<br>

## Spring Cloud 기반 마이크로서비스 이해

### 1. Spring Boot

컴포넌트 레벨에서의 마이크로서비스 아키텍처로 설정 간소화 및 독립 서비스를 지원한다.

<br>

스프링 부트 애플리케이션 스타터

| 이름                                        | 설명                                                         |
| ------------------------------------------- | ------------------------------------------------------------ |
| spring-boot-starter                         | 자동 구성 지원, 로깅 및 YAML 을 포함한 핵심 스타터           |
| spring-boot-starter-activemq                | Apache ActiveMQ를 사용한 JMS 메시징 스타터                   |
| spring-boot-starter-amqp                    | Spring AMQP 및 Rabbit MQ 사용을 위한 스타터                  |
| spring-boot-starter-aop                     | Spring AOP 및 AspectJ를 이용한 Aspect 지향 프로그래밍 스타터 |
| spring-boot-starter-artemis                 | Apache Artemis를 사용한 JMS 메시징 스타터                    |
| spring-boot-starter-batch                   | 스프링 배치 사용을 위한 스타터                               |
| spring-boot-starter-cache                   | Spring Framework의 캐싱 지원 사용을 위한 스타터              |
| spring-boot-starter-data-cassandra          | Cassandra 분산 데이터베이스 및 Spring Data Cassandra 사용을 위한 스타터 |
| spring-boot-starter-data-cassandra-reactive | Cassandra 분산 데이터베이스 및 Spring Data Cassandra Reactive 사용을 위한 스타터 |
| spring-boot-starter-data-couchbase          | Couchbase 문서 지향 데이터베이스 및 Spring Data Couchbase 사용을 위한 스타터 |
| spring-boot-starter-data-couchbase-reactive | Couchbase 문서 지향 데이터베이스 및 Spring Data Couchbase Reactive 를 사용하기위한 스타터 |
| spring-boot-starter-data-elasticsearch      | Elasticsearch 검색 및 분석 엔진 및 Spring Data Elasticsearch 사용을 위한 스타터 |
| spring-boot-starter-data-jdbc               | 스프링 데이터 JDBC 사용을 위한 스타터                        |
| spring-boot-starter-data-jpa                | Hibernate 와 함께 Spring Data JPA 를 사용하기위한 스타터     |
| spring-boot-starter-data-ldap               | 스프링 데이터 LDAP 사용을 위한 스타터                        |
| spring-boot-starter-data-mongodb            | MongoDB 문서 지향 데이터베이스 및 Spring Data MongoDB 사용을 위한 스타터 |
| spring-boot-starter-datamongodb-reactive    | MongoDB 문서 지향 데이터베이스 및 Spring Data MongoDB Reactive 사용을 위한 스타터 |
| spring-boot-starter-data-neo4j              | Neo4j 그래프 데이터베이스 및 Spring Data Neo4j 사용을 위한 스타터 |
| spring-boot-starter-data-r2dbc              | Spring Data R2DBC 사용을 위한 스타터                         |
| spring-boot-starter-data-redis              | Spring Data Redis 및 Lettuce 클라이언트와 함께 Redis 키-값 데이터 저장소를 사용하기위한 스타터 |
| spring-boot-starter-data-redis-reactive     | Spring Data Redis 반응 형 및 Lettuce 클라이언트와 함께 Redis 키-값 데이터 저장소를 사용하기위한 스타터 |
| spring-boot-starter-data-rest               | Spring Data REST 를 사용하여 REST 를 통해 Spring Data 저장소를 노출하기위한 스타터 |
| spring-boot-starter-data-solr               | Spring Data Solr 과 함께 Apache Solr 검색 플랫폼을 사용하기위한 스타터 |
| spring-boot-starter-freemarker              | FreeMarker 보기를 사용하여 MVC 웹 애플리케이션 빌드를 위한 스타터 |
| spring-boot-starter-groovy-templates        | Groovy 템플릿 뷰를 사용하여 MVC 웹 애플리케이션 구축을 위한 스타터 |
| spring-boot-starter-hateoas                 | Spring MVC 및 Spring HATEOAS 로 하이퍼 미디어 기반 RESTful 웹 애플리케이션 구축을 위한 스타터 |
| spring-boot-starter-integration             | 스프링 통합 사용을 위한 스타터                               |
| spring-boot-starter-jdbc                    | DB 연결 풀에서 JDBC 를 사용하기 위한 스타터                  |
| spring-boot-starter-jersey                  | JAX-RS 및 Jersey를 사용하여 RESTful 웹 애플리케이션을 빌드하기위한 스타터 대안 : spring-boot-starter-web |
| spring-boot-starter-jooq                    | jOOQ 를 사용하여 SQL 데이터베이스에 액세스하기위한 스타터. spring-boot-starter-data-jpa 또는에 대한 대안 springboot-starter-jdbc |
| spring-boot-starter-json                    | JSON 을 읽고 쓰는 스타터                                     |
| spring-boot-starter-jta-atomikos            | Atomikos를 사용한 JTA 트랜잭션 스타터                        |
| spring-boot-starter-jta-bitronix            | Bitronix를 사용한 JTA 트랜잭션 스타터 2.3.0 부터 사용되지 않음 |
| spring-boot-starter-mail                    | Java Mail 및 Spring Framework 의 이메일 전송 지원을 위한 스타터 |
| spring-boot-starter-mustache                | mustache를 사용하여 웹 애플리케이션을 빌드하기 위한 스타터   |
| spring-boot-starter-oauth2-client           | Spring Security 의 OAuth2 / OpenID Connect 클라이언트 기능을 사용하기위한 스타터 |
| spring-boot-starter-oauth2- resource-server | Spring Security 의 OAuth2 리소스 서버 기능을 사용하기위한 스타터 |
| spring-boot-starter-quartz                  | Quartz 스케줄러 사용을 위한 스타터                           |
| spring-boot-starter-rsocket                 | RSocket 클라이언트 및 서버 구축을 위한 스타터                |
| spring-boot-starter-security                | 스프링 시큐리티 사용을 위한 스타터                           |
| spring-boot-starter-test                    | JUnit, Hamcrest 및 Mockito 를 포함한 라이브러리로 Spring Boot 애플리케이션을 테스트하기위한 스타터 |
| spring-boot-starter-thymeleaf               | Thymeleaf 보기를 사용하여 MVC 웹 애플리케이션 빌드를위한 스타터 |
| spring-boot-starter-validation              | Hibernate Validator 와 함께 Java Bean Validation 을 사용하기위한 스타터 |
| spring-boot-starter-web                     | Spring MVC 를 사용하는 RESTful 애플리케이션을 포함한 웹 구축을 위한 스타터. Tomcat 을 기본 내장 컨테이너로 사용 |
| spring-boot-starter-web-services            | 스프링 웹 서비스 사용을 위한 스타터                          |
| spring-boot-starter-webflux                 | Spring Framework 의 Reactive Web 지원을 사용하여 WebFlux 애플리케이션 구축을 위한 스타터 |
| spring-boot-starter-websocket               | Spring Framework 의 WebSocket 지원을 사용하여 WebSocket 애플리케이션 구축을 위한 스타터 |

<br>

스프링 부트 프로덕션 스타터

| 이름                         | 설명                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| spring-boot-starter-actuator | 애플리케이션을 모니터링하고 관리 할 수 있는 프로덕션 준비 기능을 제공하는 Spring Boot Actuator 사용을 위한 스타터 |

<br>

스프링 부트 테크니컬 스타터

| 이름                              | 설명                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| spring-boot-starter-jetty         | 내장 서블릿 컨테이너로 Jetty를 사용하기위한 스타터 대안 : springboot-starter-tomcat |
| spring-boot-starter-log4j2        | 로깅을 위해 Log4j2를 사용하기위한 스타터 대안 : spring-boot-starter-logging |
| spring-boot-starter-logging       | Logback을 이용한 로깅 스타터 기본 로깅 스타터                |
| spring-boot-starter-reactor-netty | 임베디드 Reactive HTTP 서버로 Reactor Netty를 사용하기위한 스타터 |
| spring-boot-starter-tomcat        | 임베디드 서블릿 컨테이너로 Tomcat을 사용하기위한 스타터      |
| spring-boot-starter-undertow      | Undertow를 임베디드 서블릿 컨테이너로 사용하기위한 스타터 대안 : spring-boot-starter-tomcat |

<br>

### 2. Spring Cloud

시스템 레벨에서의 마이크로 서비스 아키텍처로 컴포넌트들 간의 효율적인 분산 서비스를 지원한다.

<br>

Spring Cloud 컴포넌트

| 서비스                   | 설명                                                         | 컴포넌트                           |
| ------------------------ | ------------------------------------------------------------ | ---------------------------------- |
| Config 서비스            | 별도의 통합된 설정 관리 서비스 제공을 통해 환경 독립적 서비스 제공 | Spring Config                      |
| Service Discovery 서비스 | 서비스에 대한 물리적 위치 정보 대신 논리적 서비스 위치 정보 제공 | Euraka (Spring Cloud Netflix)      |
| Event Bus 서비스         | 분산 메시징 지원을 위한 서비스 연계 지원                     | Spring Cloud Bus (AMQP & RabbitMQ) |
| Circuit Breaker 서비스   | 서비스 간 호출 시, 문제가 있는 서비스에 대한 차단 지원 서비스 | Hystrix (Spring Cloud Netfilx)     |
| Client Load Balancing    | 서비스 호출 시에 분산 형태로 호출할 수 있는 client 적용 서비스 library | Ribbon (Spring Cloud Netflix)      |
| Service Router 서비스    | 서비스 호출 시, routing을 통해 실제 서비스에 위치 제공       | Zuul (Spring Cloud Netflix)        |
| API Gateway 서비스       | Microserivec에 대한 API 관리 및 모니터링 서비스              | Zuul (Spring Cloud Netfilx)        |
| Cluster 서비스           | Service level의 Cluster를 지원하기 위한 서비스 제공          | Spring Cloud Cluster               |
| Security 서비스          | Load balanced 환경에서의 OAuth2 인증 지원 서비스             | Spring Cloud Security              |
| Ployglot 지원 서비스     | non-JVM 프로그래밍 언어 지원을 위한 서비스                   | Spring Cloud Sidecar               |
| Kuberenetes 지원 서비스  | Spring Cloud 애플리케이션을 위한 쿠버네티스 Discovery와 ConfigMaps 지원 서비스 | Spring Cloud Kubernetes            |

<br>

## Spring Cloud 기반 마이크로 서비스 활용

### 1. Catalogs 서비스

상품의 전시 및 화면에 대한 로직을 처리하는 서비스로서 별도의 데이터는 가지고 있지 않지만, 사용자의 입력 및 출력에 대한 서비스를 제공한다. 또한 요청에 따라 각각의 서비스를 호출하여 요청에 응답한다.

<br>

Customers 서비스를 호출하기 위해 별도의 RestTemplate을 적용한다.

*※ RestTemplate : 스프링 3.0부터 지원하는 HTTP 통신에 사용되는 템플릿으로 REST 서비스를 호출하도록 설계되어 HTTP 프로토콜의 메서드(GET, POST, DELETE, PUT)에 맞게 여러 메서드를 제공한다.*

<br>

Hystrix를 적용하여 Customers 서비스에서 호출한 API가 에러를 발생하거나 1초 이상 지연이 되는 경우 별도의 fallback 메소드를 실행하여 장애의 전파를 방지한다.

<br>

Ribbon을 RestTemplate에 적용한다.

<br>

Eureka Client로 등록한다.

<br>

### 2. Customers 서비스

고객 정보를 조회할 수 있는 서비스로 요청에 따라 고객 정보를 반환한다. 

<br>

Eureka Client로 등록한다.

<br>

### 3. Spring Cloud의 컴포넌트 활용

#### Circuit Breaker - Hystrix

분산환경을 위한 장애 및 지연 내성을 갖도록 도와주는 라이브러리로써 Circuit Breaker Pattern 디자인을 적용하여 MSA 애플리케이션의 장애 전파를 방지할 수 있다. 

<br>

Hystirx의 역할

1. 장애 및 지연 내성 
   분산 환경에서 한 개의 서비스가 실패하는 경우 해당 서비스의 실패로 의존성이 있는 타 서비스까지 장애가 전파될 수 있다. 외부에서 호출하는 서비스를 Hystrix로 Wrapping 하면 실패가 전파되는 것을 Fallbacks를 활용하여 미연에 방지하고 빠르게 복구할 수 있도록 도와준다. 또한 각 서비스의 HystrixCommand는 Circuit Breaker Pattern으로 외부에 영향을 받지 않도록 쓰레드와 세마포어 방식으로 분리되어 있다.
2. 실시간 구동 모니터링
   Hystrix를 사용하면 Netflex/Servo를 활용한 metrix 서비스를 사용할 수 있으며 third-party 라이브러리로 Prometheus를 통하여 모니터링을 할 수 있다.
3. 병행성
   Parallel execution을 제공하며 여러 설정 값에 대한 변경을 지원한다. 또한 내부적으로 중복되는 Request 처리를 줄이기 위하여 Request Caching과 일관 처리를 위한 Request Collapsing 기능을 제공한다.

<br>

spring-cloud-netflix-dependencies의 3버전대부터는 maintenance mode로 관리되어 해당 라이브러리에서 사라졌다.

<br>

#### Client Load Balancer - Ribbon

클라이언트에 탑재할 수 있는 소프트웨어 기반의 로드 밸런서이다. MSA에서는 하드웨어적인 L4 Switch가 아니라 소프트웨어적으로 구현된 클라이언트사이드 로드 밸런싱을 주로 사용한다. Ribbon은 분산 처리 방법으로 여러 서버를 라운드 로빈 방식으로 부하 분산 기능을 제공한다.

<br>

Ribbon의 구성 요소

1. Rule : 요청을 보낼 서버를 선택하는 논리
   - Round Robbin : 한 서버씩 돌아가면서 전달 (기본 설정)
   - Avaiable Filtering : 에러가 많은 서버를 제외함
   - Weighted Response Time : 서버 별 응답 시간에 따라 확률 조절
2. Ping : 서버가 살아 있는지 체크하는 논리
   - Static, dynamic 모두 가능
3. ServerList : 로드 밸런싱 대상 서버 목록
   - Configuration을 통해 static하게 설정 가능
   - Eureka 등을 기반으로 dynamic하게 설정 가능

<br>

#### Service Registry - Eureka

동적인 서비스 증설 및 축소를 위하여 필수적으로 필요한 서비스의 자가 등록, 탐색 및 부하 분산에 사용될 수 있는 라이브러리이다. 마이크로 서비스들의 정보를 레지스트리 서버에 등록할 수 있도록 기능을 제공한다.

Ribbon과 결합하여 사용할 수 있으며 이를 통해 서버 목록을 자동으로 관리 및 갱신한다.

<br>

Eureka는 Eureka 서버와 클라이언트로 구성된다.

+ Eureka 서버 : Eureka 클라이언트에 해당하는 마이크로 서비스들의 상태 정보가 등록되어 있는 레지스트리 서버이다.
+ Eureka 클라이언트 : 서비스가 시작될 때 Eureka 서버에 자신의 정보를 등록하고 이후 주기적으로 자신의 가용 상태를 알리며, 일정 횟수 이상의 ping이 확인되지 않으면 Eureka 서버에서 해당 서비스를 제외시킨다.

<br>

EurekaServer 프로젝트 생성

Eureka Client를 등록하는 경우 아래와 같이 Eureka Server에서 확인할 수 있다.

![image-20220517170651045](https://github.com/fe-docker-study/cs-study-for-interview/blob/main/MSA/%ED%8F%AC%EB%8F%84/Eureka_Server.png)

<br>

#### API Gateway - Zuul

MSA에서 언급되는 주요 컴포넌트 중 하나이며, 모든 클라이언트 요청에 대한 end-point를 통합하는 서비스이다. 프록시 서버처럼 동작하며, 인증 및 권한, 모니터링, logging 등의 추가적인 기능도 지원한다.

모놀리틱 아키텍처는 모든 비즈니스 로직이 하나의 서버에 존재하나, 이와 달리 MSA는 도메인 별 하나 이상의 서버가 존재하기 때문에 클라이언트의 입장에서는 다수의 end-point를 알아야 한다. 또한 end-point가 변경되는 경우에는 관리하기 힘들어지는데, 이런 문제를 해결하기 위해 하나로 통합할 수 있는 API Gateway가 필요하다.

<br>

Zull의 주요 기능

- 인증 및 보안 : 클라이언트 요청 시 각 리소스에 대한 인증 요구 사항을 식별하고, 이를 충족하지 못한 경우 해당 요청을 거부하는 기능을 제공한다.
- 모니터링 : 모든 트래픽이 지나기 때문에 의미있는 데이터와 지표를 수집할 수 있는 기능을 제공한다.
- 동적 라우팅 : 필요에 따라 즉시 원하는 백엔드 클러스터로 트래픽을 동적으로 라우팅하는 기능을 제공한다.
- 부하 테스트 : 성능 측정을 위하여 신규 서비스 또는 백엔드에 트래픽을 점진적으로 증가하는 방식으로 부하를 유발할 수 있는 기능을 제공한다.
- 트래픽 드랍 : 각 요청에 대해 용량을 할당하고, 제한된 용량 이상의 요청이 발생한 경우 이를 제한할 수 있는 기능을 제공한다.
- 정적 응답 처리 : 특정 요청에 대해서는 백엔드로 트래픽을 보내는 대신 즉시 API Gateway에서 응답을 처리하는 기능을 제공한다.

<br>

application.yml 설정

**`eureka.instance.prefer-ip-address`**

- 서비스의 호스트 이름이 아닌 IP 주소를 Eureka Server 에 등록하도록 지정한다. (디폴트 false)
- 기본적으로 유레카는 호스트 이름으로 접속하는 서비스를 등록한다. 따라서 DNS 가 지원된 호스트 이름을 할당하는 서버 기반 환경에서는 잘 동작하지만, 컨테이너 기반의 배포에서 컨테이너는 DNS 엔트리가 없는 임의의 생성된 호스트 이름을 부여받아 시작하므로 컨테이너 기반 배포에서는 해당 설정값을 false 로 하는 경우 호스트 이름 위치를 정상적으로 얻지 못한다.

<br>

#### Config 서버

분산 시스템에서 애플리케이션의 서비스, 비즈니스 로직과 연관성 있는 정보들을 애플리케이션과 분리해 외부에서 관리하도록 한 서버이다. 마이크로서비스들의 환경 설정 정보들을 중앙 서버 한 곳에서 관리할 수 있게 해 준다.

<br>

Config 서버가 구동될 때 Environment Repository에서 설정 내용을 가져온다. Environment Repository는 VCS, File System, DB로 구성 가능하다. 보통 Git이나 SVN 같은 VCS 서비스를 많이 사용한다.

각 마이크로 서비스들은 서비스가 구동될 때 Config 서버에서 설정 내용을 내려 받아 애플리케이션이 초기화되고 구동된다.

만약 서비스 운영 중 설정 파일을 변경해야 하는 경우에는 Spring Cloud Bus를 이용하여 모든 마이크로 서비스의 환경 설정을 업데이트 할 수 있다. Spring Cloud Bus는 RabbitMQ, Kafka 같은 경량 메시지 브로커들을 사용할 수 있도록 해 주며, 이 메시지 브로커들을 이용하여 각 서비스들에세 Config 변경 사항을 전파한다.

<br>

#### Polyglot Support - Sidecar

Java 이외의 언어로 작성된 애플리케이션(비 JVM 애플리케이션)을 Spring Cloud와 엮어 동작할 수 있도록 한다.

+ 비 JVM 애플리케이션의 경우 Sidecar가 가동 시 동작 여부를 체크한 후 Eureka 서버에 등록해준다.
+ Sidecar는 주기적으로 상태를 체크하여 Eureka 서버에 알려준다.
+ non-JVM microservice는 Sidecar의 존재를 알지 못한다.
+ Zuul을 통하여 Request를 받을 수 있으며, Ribbon을 통해 로드밸런스 기능이 작동하고, Config 서버로 환경설정을 할 수 있다.

<br>

비 JVM 애플리케이션을 구동하고 Sidecar 애플리케이션을 실행하면 Eureka 서버에 SIDECAR-NONJVM 애플리케이션이 등록된다.	

<br>

#### Centralized Logging - ELK(Elastic Search, Logstash, Kibana)

각각의 마이크로서비스에서 생성된 로그들을 한 곳으로 취합하여 효율적으로 분석하기 위해 사용한다. 각 마이크로서비스에서 생성된 로그파일들을 Logstash에서 수집하여 Elasticsearch로 전송하고, Kibana 웹사이트에서 확인할 수 있다.

<br>

ELK Stack (Elasticsearch, Logstash, Kibana )

1. Elasticsearch : 텍스트, 숫자, 정형 및 비정형, 로그 등의 모든 유형의 데이터를 저장할 수 있는 Lucene 검색 엔진 기반의 NoSQL 데이터베이스이다.
2. Logstash : 로그 파이프라인 도구로 다양한 소스에서 동시에 데이터를 수집하여 Elasticsearch로 전송하고 색인되도록 한다.
3. Kibana : Elasticsearch를 위한 시각화 및 관리 도구로서 애플리케이션 로그를 모니터링할 수 있다.

<br>

#### Centralized Metrics - Prometheus, Grafana

Spring Boot Actuator는 Micrometer를 사용하여 애플리케이션의 Metric 정보를 제공한다. Micrometer를 통해 노출된 애플리케이션의 Metric 정보를 Prometheus를 사용하여 저장하고, Grafana를 활용하여 그래프로 시각화한다.

<br>

의존성 추가 (pom.xml)

```xml
<!-- 메트릭을 Endpoint로 노출 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- Micrometer core dependency -->
<dependency>
	<groupId>io.micrometer</groupId>
    <artifactId>micrometer-core</artifactId>
</dependency>

<!-- Micrometer Prometheus registry -->
<dependency>
	<groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```



application.properties 설정

```properties
management.endpoint.metrics.enabled=true
management.endpoints.web.exposure.include=* # 활성화하고자 하는 endpoint들의 id 작성 가능 ex. prometheus,health,info,metric
management.endpoint.prometheus.enabled=true
management.metrics.export.prometheus.enabled=true
management.server.port= # 설정하지 않으면 애플리케이션과 동일한 8080이 기본이 됨

management.endpoints.web.base-path=/monitor
management.endpoints.web.path-mapping.health=healthcheck # management.endpoints.web.base-path 와 management.endpoints.web.pathmapping.<id>값을 수정하여, 특정 id의 endpoint 경로를 수정 가능
management.endpoints.web.cors.allowed-origins=http://other-domain.com
management.endpoints.web.cors.allowed-methods=GET,POST
```

<br>

#### Distributed Tracing - Sleuth, Zipkin

분산 환경에서의 서비스 간 병목이 발생할 때 기존 방식의 모니터링으로는 추적이 불가능하기 때문에 분산 환경 로그 트레이싱이 필요하다.

<br>

의존성 추가 (pom.xml)

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```



application.yml 설정

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411/
```

마이크로서비스들을 호출하면 로그에 Service Name, Trace Id, Span Id, Export Flag 를 확인할 수 있다. A 마이크로서비스에서 B 마이크로서비스를 호출하는 구조라 할 때 A 마이크로서비스를 호출하면 Trace Id와 Span Id는 동일하며, B 마이크로서비스 로그에서는 Trace Id는 동일하지만, Span Id는 마이크로서비스별로 다름을 확인할 수 있다.

<br>
