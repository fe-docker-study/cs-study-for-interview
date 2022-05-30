# actuator

- 어플리케이션을 관리하고 제반 정보를 제공하는 공통 기능을 쉽게 적용하기 위해서 사용
- Spring boot의 component
- 어플리케이션을 모니터링하고 상호작용하기 위해 내장된 수많은 종단점(RESTful API)를 제공

### 중요한 actuator 종단점

- /bean : 어플리케이션에 초기화된 모든 스프링 빈의 목록을 표시
- /env : 스프링의 설정 가능한 환경 속성 목록을 표시(예: OS 환경 변수 및 컨피규레이션 파일의 속성 목록)
- /health :  애플리케이션의 상태정보 표시
- /info : 애플리케이션의 임의 정보 표시 (예: build-info, properties나 [git.properties](http://git.properties) 파일의 정보 표시)
- /loggers : 어플리케이션의 로거 컨피규레이션 정보를 표시하고 수정
- /metrics : 어플리케이션의 메트릭 정보를 표시(예: 메모리 사용량, 실행 중인 스레드 수, REST 메서드의 응답 시간)
- /trace : 트레이스(trace) 정보 표시(기본으로 마지막 100개의 HTTP 요청)

## Actuator 의존성 추가

### pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>2.4.4</version>
</dependency>
```

### application.yml

```xml
management:
  endpoints:
    web:
      exposure:
        include: health,metrics
```

## 애플리케이션 정보 /info API

- 기본적으로 어떤 정보도 노출하지 않는데 이것을 변경하려면 InfoContributor 빈을 변경하거나 자신만의 빈을 작성해야함
- EnvironmentInfoContributor는 환경 정보를 노출함
- GitInfoContributor는 클래스 경로에 존재하는 git.properties파일을 찾아서 브랜치 이름이나 커밋ID와 같은 커밋정보를 노출함
- BuildInfoContributor는 META-INF/build-info.properties 파일의 정보를 모아서 API로 노출. 이러한 깃과 빌드 정보는 어플리케이션이 빌드될 때 자동으로 수집돼 노출됨

### pom.xml

- git-commit-id-plugin을 추가
- build-info.properties 파일이 생성되면 /Info API가 달라짐

```xml
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <executions>
            <execution>
                <goals>
                    <goal>build-info</goal>
                    <goal>repackage</goal>
                </goals>
                <configuration>
                    <addintionalProperties>
                      <java.target>${maven.compiler.target}</java.target>
                      <time>${maven.build.timestamp}</time>
                    </addintionalProperties>
                </configuration>
            </execution>
        </executions>
    </plugin>
    <plugin>
        <groupId>pl.project13.maven</groupId>
        <artifactId>git-commit-id-plugin</artifactId>
        <configuration>
        <failOnNoGitDirectory>false</failOnNoGitDirectory>
        </configuration>
    </plugin>           
</plugins>
```

## 상태정보 /health API

## 매트릭스 /metrics

- 마이크로서비스의 힙과 힙이 아닌 메모리의 사용량을 알 수 있음
- 로딩된 클래스의 개수, 활성화된 스레드의 수, API 메서드의 평균 실행 시간 등 수많은 정보를 보여줌
- 자신만의 메트릭을 생성할 경우 스프링 부트 액추에이터에서 제공하는 CounterService와 GuageService를 사용
  - CounterService : 값의 증가와 감소, 초기화를 위한 메서드를 제공
  - GaugeService : 단순한 현재의 값을 전달
- 스프링 부트 어플리케이션에 의 해 생성되는 모든 메트릭은 분석하거나 표시할 수 있는 저장소로 전달할 수 있음. 이런 정보는 Redis, Open TSDB, Statsd, InfluxDB 등에 저장할 수 있음