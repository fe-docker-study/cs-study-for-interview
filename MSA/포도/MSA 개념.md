## 마이크로서비스 아키텍처(MSA)
MSA란 마이크로 서비스 아키텍처(Micro Service Architecture)의 약자로 단일 프로그램을 각 컴포넌트 별로 나누어 작은 서비스의 조합으로 구축하는 방법이다.  

## MSA의 등장배경
### Monolithic Architecture
소프트웨어의 모든 구성요소가 한 프로젝트에 통합되어있는 형태로 이전부터 구현되어 온 형태이다. 간단하고, 유지보수가 용이하기 때문에 소규모 프로젝트에 합리적이다. 하지만, 일정 규모 이상의 서비스 혹은 수백 명의 개발자가 투입되는 프로젝트에서는 아래와 같은 한계가 생기게 된다.
- 서비스/프로젝트가 커지면 커질수록, 영향도 파악 및 전체 시스템 구조의 파악에 어려움이 있다.
- 빌드 시간 및 테스트시간, 그리고 배포시간이 기하급수적으로 늘어난다.
- 서비스를 부분적으로 scale-out하기 힘들다.
- 부분의 장애가 전체 서비스의 장애로 이어지는 경우가 발생한다.

### MSA
- 각각의 서비스의 크기는 작지만, 서비스 자체는 하나의 모놀리틱 아키텍쳐와 유사한 구조를 가진다.
- 각각의 서비스는 독립적으로 배포가 가능해야 한다.
- 각각의 서비스는 다른 서비스에 대한 의존성이 최소화 되어야 한다.
- 각 서비스는 개별 프로세스로 구동되며, REST와 같은 가벼운 방식으로 통신되어야 한다.

### MSA의 장점
- 배포(deployment) 관점: 서비스 별 개별 배포 가능하기 때문에 배포 시 전체 서비스의 중단이 없다. 요구사항을 신속하게 반영하여 빠르게 배포할 수 있다.
- 확장(scaling) 관점 : 특정 서비스에 대한 확장성이 용이하다. 클라우드 사용에 적합한 이키텍처이다.
- 장애(failure) 관점 : 부분적 장애에 대한 격리가 수월하기 때문에 장애가 전체 서비스로 확장될 가능성이 적다.
- 신기술의 적용이 유연하고, 서비스를 polyglot하게 개발/운영 할 수 있다.

### MSA의 단점
- 성능 : 서비스 간 호출 시 API를 사용하기 때문에, 통신 비용이나, Latency가 늘어난다.
- 테스트 / 트랜잭션 : 서비스가 분리되어 있기 때문에 테스트와 트랜잭션의 복잡도가 증가하고, 많은 자원을 필요로 한다.
- 데이터 관리 : 데이터가 여러 서비스에 걸쳐 분산되기 때문에 한번에 조회하기 어렵고, 데이터의 정합성 또한 관리하기 어렵다.

## Software Architecture
### Antifragile
+ Auto scaling : 자동 확장성을 갖는 특징
+ Microservices : 전체 서비스를 구축하고 있는 개별적인 모듈을 세분화함
+ Chaos engineering : 시스템이 예측하지 못한 상황이어도 견딜 수 있게 함
+ Continuous deployments : 배포 파이프 라인 (지속적 통합, 지속적 배포)