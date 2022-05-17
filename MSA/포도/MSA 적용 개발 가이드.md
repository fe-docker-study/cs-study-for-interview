# MSA 적용 개발 가이드

## MSA (마이크로 서비스 아키텍처)

### 1. MSA 정의

마이크로서비스(microservice) : 애플리케이션을 느슨히 결함된 서비스의 모임으로 구조화하는 서비스 지향 아키텍처(SOA) 스타일의 일종인 소프트웨어 개발 기법으로 모듈성을 개선시키고 애플리케이션의 이해, 개발, 테스트를 더 쉽게 해 준다.



![image-20220517111535428](C:\test\a\cs-study-for-interview\MSA\포도\모놀리식과_마이크로서비스_아키텍처_비교.png)

위 그림처럼 각각의 마이크로 서비스는 자체 비즈니스 계층과 데이터베이스를 가지고 있다. 따라서 수정 시에도 다른 마이크로 서비스에 영향을 주지 않는다.
일반적으로 마이크로 서비스는 HTTP, REST 같은 널리 채택된 lightweight 프로토콜 또는 JMS, AMQP와 캍은 메시징 프로토콜을 사용하여 서로 통신한다.



마이크로서비스 아키텍처의 특징

+ 애플리케이션 로직을 각자 책임이 명확한 작은 컴포넌트들로 분해하고, 이를 조합해 솔루션을 제공한다.
+ 각 컴포넌트는 작은 책임 영역을 담당하고 완전히 상호 독립적으로 배포된다. 
+ 마이크로서비스는 몇 가지 기본 원칙에 기반을 두며, 서비스 소비자와 서비스 제공자 사이의 데이터 교환을 위해 HTTP와 JSON 같은 경량 통신 프로토콜을 사용한다.
+ 애플리케이션은 항상 기술 중립적 프로토콜을 사용해 통신하므로 서비스 구현 기술과는 무관하다. 따라서 마이크로서비스 기반의 애플리케이션을 다양한 언어와 기술로 구축할 수 있다.



### 2. MSA 목적

MSA는 다음과 같은 장점들을 통해 시스템에 대한 개발 및 운영 복잡성을 효율적으로 낮출 수 있다.

+ Microservice는 독립적으로 구성될 수 있으며, 상호 독립적으로 구축 및 운영될 수 있다.
+ 특정 서비스만 집중할 수 있고, 코드 규모가 작아 효율적인 유지보수가 가능하다.
+ Restful API와 같이 lightweight한 통신을 통해 효과적인 상호 연계가 가능하다.
+ 독립적인 서비스 단위 확장(scale-out)을 지원하기 때문에 효율적으로 시스템 자원을 활용할 수 있다.
+ 개별로 서비스 배포가 가능하기 때문에 수시로 필요에 따라 배포할 수 있다.



### 3. 12-Factor App 방법론

12-Factor App은 다음과 같은 특징을 가진 SaaS(Software As A Service) 앱을 만들기 위한 방법론이다.

+ 선언적 형식으로 설정을 자동화하여 새로운 개발자가 프로젝트에 참여하는 데 드는 시간과 비용을 최소화한다.
+ OS에 따라 달라지는 부분을 명확하게 하고, 실행환경 사이의 이식성을 극대화한다.
+ 클라우드 플랫폼 배포에 적합하고, 서버와 시스템의 관리 부담을 없앤다.
+ 개발환경과 운영환경의 차이를 최소화하여 민첩성을 극대화하고 지속적인 배포가 가능하다.
+ 툴, 아키텍처, 개발방식을 크게 바꾸지 않고 서비스를 확장(scale-up)할 수 있다.



12-Factor 방법론은 어떤 프로그래밍 언어로 작성된 앱에도 적용할 수 있고, 백엔드 서비스(데이터베이스, 큐, 메모리 캐시 등)와 다양한 조합으로 사용할 수 있다.



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



### 4. Service Mesh

Service Mesh : 각 마이크로서비스 간통신을 최적화하고 다운 타임을 방지하며 전체 서비스를 관리하기 위한 Outer Architecture로 복잡한 내부 네트워크의 추상화를 통해 서비스 간의 통신이 빠르고, 안정적이며, 신뢰성을 보장한다.



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



Service Mesh 적용 방안

- Kubernetes의 Istio 솔루션 사용
- Spring Cloud 기반 Service Mesh 구축



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



마이크로서비스 필요조건에 따른 범위

![image-20220517111346189](C:\test\a\cs-study-for-interview\MSA\포도\마이크로서비스_필요조건에_따른_범위.png)



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



## Spring Cloud 기반 마이크로서비스 이해

### 1. Spring Boot

컴포넌트 레벨에서의 마이크로서비스 아키텍처로 설정 간소화 및 독립 서비스를 지원한다.



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



스프링 부트 프로덕션 스타터

| 이름                         | 설명                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| spring-boot-starter-actuator | 애플리케이션을 모니터링하고 관리 할 수 있는 프로덕션 준비 기능을 제공하는 Spring Boot Actuator 사용을 위한 스타터 |



스프링 부트 테크니컬 스타터

| 이름                              | 설명                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| spring-boot-starter-jetty         | 내장 서블릿 컨테이너로 Jetty를 사용하기위한 스타터 대안 : springboot-starter-tomcat |
| spring-boot-starter-log4j2        | 로깅을 위해 Log4j2를 사용하기위한 스타터 대안 : spring-boot-starter-logging |
| spring-boot-starter-logging       | Logback을 이용한 로깅 스타터 기본 로깅 스타터                |
| spring-boot-starter-reactor-netty | 임베디드 Reactive HTTP 서버로 Reactor Netty를 사용하기위한 스타터 |
| spring-boot-starter-tomcat        | 임베디드 서블릿 컨테이너로 Tomcat을 사용하기위한 스타터      |
| spring-boot-starter-undertow      | Undertow를 임베디드 서블릿 컨테이너로 사용하기위한 스타터 대안 : spring-boot-starter-tomcat |



### 2. Spring Cloud

시스템 레벨에서의 마이크로 서비스 아키텍처로 컴포넌트들 간의 효율적인 분산 서비스를 지원한다.



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



## Spring Cloud 기반 마이크로 서비스 활용



