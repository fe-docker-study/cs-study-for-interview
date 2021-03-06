- 메시징 플랫폼의 2가지 종류
    1. 메시지 브로커
    2. 이벤트 브로커

- 메시지 브로커는 이벤트 브로커 역할을 할 수 없지만, 이벤트 브로커는 메시지 브로커로 동작할 수 있음

### 메시지 브로커

- 대규모 메시지 기반 미들웨어 아키텍처에서 사용됨.
    - 미들웨어 : 서비스하는 애플리케이션들을 보다 효율적으로 연결하는 소프트웨어. 
    예) 메시징 플랫폼, 인증 플랫폼, 데이터베이스 같은 것들을 미들웨어라고 할 수 있다.
- 메시지를 받아서 적절히 처리하고 나면, 즉시 또는 짧은 시간 내에 삭제되는 구조이다.
- 예 : 레디스 큐, RabbitMQ

### 이벤트 브로커

- 레코드 (= 이벤트 또는 메시지라고도 불림)를 딱 하나만 보관하고, 인덱스를 통해 개별 액세스를 관리한다.
- 업무상 필요한 시간동안 이벤트를 보존할 수 있다.
- 메시지를 처리하고나서도 삭제하지 않는다.
    - 이벤트 브로커는 서비스에서 나오는 이벤트를 마치 데이터베이스에 저장하듯이, 이벤트 브로커의 큐에 저장한다.
        
        → 딱 한번 일어난 이벤트 데이터를 브로커에 저장함으로서 단일 진실 공급원으로 사용할 수 있다. 
        
        → 장애가 발생했을 때, 장애가 일어난 지점부터 재처리할 수 있다. 
        
        → 많은 양의 실시간 스트림 데이터를 효과적으로 처리할 수 있다. 
        
- 예 : Kafka, AWS의 키네시스
- 이벤트 브로커로 클러스터를 구축하면 이벤트 기반 마이크로 서비스 아키텍처로 발전하는데 아주 중요한 역할을 할 뿐만 아니라, 메시지 브로커로서도 사용할 수 있다.
