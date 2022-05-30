## Configuration

- 모놀리틱 아키텍쳐에서는 어플리케이션 configuration을 팻JAR파일 안에 제공 → 변경 시 다시 컴파일하거나 다시 배포해야함
- 스프링 부트가 지원하는 다른 방식은 팻JAR 외부의 파일 시스템에 저장된 명시적 configuration을 사용한다고 가정 → 다시 배포할 필요는 없지만 마이크로서비스가 많을 경우 파일 시스템에 저장된 명시적인 파일을 기반으로 한 configuration을 관리하기 어려움
- 스프링 클라우드 컨피그는 분산 시스템에서 외부 configuration을 위해 서버측과 클라이언트 측을 지원하는 전체환경에 걸쳐 어플리케이션을 위한 외부 속성을 관리하는 중앙의 단일 장소를 사용

## 스프링 클라우드 컨피그

- 스프링 클라우드 컨피그는 분산시스템에서 외부 컨피규레이션을 위해 서버 측과 클라이언트 측을 지원함
- 이 솔루션을 사용하면 전체 환경에 걸쳐 애플리케이션을 위한 외부 속성을 관리하는 중앙의 단일 장소가 생김
- 서버는 단지 HTTP와 자원기반 API 인터페이스를 노출하는 것으로 구현
- JSON이나 YAML, 속성 형태로 속성 파일을 반환, 추가로 반환된 속성 값에 대해 복호화와 암호화를 수행
- EnvironmentRepository의 기본 구현은 git을 백엔드로 사용
- 마이크로서비스의 설정값이 변경되었을때 서버 재시작없이 동적으로 적용
- 배포될 때 배포대상 환경(개발, 테스트, 운영 등)에 맞게 적용
- stateless하게 만들어 쉽게 scaling과 재시작 할 수 있음

## 스프링 클라우드 컨피그 구성

### Config Server

설정파일을 가져옴

### Config Client

설정파일을 사용

### 설정파일

중앙의 단일 저장소

![Untitled](https://user-images.githubusercontent.com/90545926/169692960-d70c0e6c-2811-48bf-a26a-43a85b061dfb.png)

## 서버 측 애플리케이션

### pom.xml

- 컨피그 서버는 스프링 부트 애플리케이션으로 실행

```xml
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-config-server</artifactId>
  </dependency>
```

### application.yml

- msa_with_spring_cloud\config-service\src\main\resources\application.yml

```yaml
server:
  port: 8888 #포트번호, 8888은 클라이언트 측의 spring.cloud.config.uri 속성의 기본값

spring:
  application:
    name: config-service #클라이언트 어플리케이션 이름은 spring.application.name을 통해 주입
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
  profiles:
    active: native #네이티브 프로파일을 활성화
  cloud:
    config:
      server:
        git:
#          uri: file:///Users/dowonlee/Desktop/Work/git-local-repo
          uri: <https://github.com/joneconsulting/spring-cloud-config>
#          basedir: /Users/dowonlee/Desktop/Work/tmp/config-repo
#          username: [username]
#          password: [password]
        native:
          search-locations: file:///Users/dowonlee/Desktop/Work/native-file-repo

management:
  endpoints:
    web:
      exposure:
        include: health, busrefresh
```

### ConfigServiceApplication.java

- 메인 애플리케이션 클래스에 컨피그 서버 활성화

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
```

## 클라이언트 측 어플리케이션 개발

- 클라이언트에서 자동으로 구성되는 컨피그 서버 주소는 http://localhost:8888
- 애플리케이션 이름을 담은 bootstrap.yml을 제공하고 pom.xml에 의존성 포함

### pom.xml

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

### bootstrap.yml

- 8888가 아닌 다른 포트를 서버의 포트로 설정하거나 클라이언트 어플리케이션과 다른 머신에서 실행하면 bootstrap.yml 파일에 현재 주소를 설정해야 함