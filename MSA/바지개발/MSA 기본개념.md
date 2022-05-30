# MSA 기본개념

![Untitled](https://user-images.githubusercontent.com/90545926/169692766-39500da8-4413-4295-891f-18f6f819bcfd.png)

- 시스템을 여러개의 독립된 서비스로 나눠서, 이 서비스를 조합함으로써 기능을 제공하는 아키텍쳐 패턴

## MSA 관심 증가 이유

- 클라우드 환경 도래
- 복잡한 비즈니스 로직
- 기존 모놀리틱 구조의 한계

## Monolitic vs MicroService Architecture

![Untitled](https://user-images.githubusercontent.com/90545926/169692767-940d0f0d-6d75-446e-b7c7-676b140e2eb5.png)

### Monolitic

- 단일 Container에 여러 기능을 통합하여 구성
- 단단한 결합을 통하여 Service를 연결
- 전체 시스템이 하나의 Project로 구성되어 개발 및 빌드를 통합적으로 진행하여야 함
- 특정 서비스 및 Database에 치명적인 오류가 발생 할 경우 전체 서비스에 장애 발생
- 시스템 다중화 시 전체 시스템에 대한 다중화를 진행해야 함
- 장점 : 트랜잭션 처리 이슈가 적음, 인프라 구성이 단순, 배포가 간편

### MicroService Architecture

- 독립된 배포 단위
- 각 서비스는 쉽게 교체 가능
- 각 서비스는 기능 중심으로 구성됨 EX) FrontEnd, 추천, 정산, 상품, 주문 등
- 각 서비스에 적합한 프로그래밍 언어, 데이터베이스, 환경으로 만들어짐
- 서비스는 크기가 작고, 상황에 따라 경계를 정하고, 자율적으로 개발되고, 독립적으로 배포되고, 분산되고, 자동화된 프로세스로 구축되고 배포
- auto scaling : 운영자에 의해서 수작업으로 처리되는 것이 아니라 cup, 메모리, 네트워크, 데이터베이스 사용량이나 조건에 따라서 자동으로 처리
- 각각의 MicroService에서 발생하는 장애가 전체 시스템 장애로 확장되지 않음
- 

## MSA 마인드맵

![Untitled](https://user-images.githubusercontent.com/90545926/169692768-945903fb-53c2-459a-86d9-f97c2745d350.png)

# MSA 구조

![Untitled](https://user-images.githubusercontent.com/90545926/169692769-816ec3c6-8e7b-44e0-8886-781101965911.png)

미국의 정보기술 연구 및 자문 회사, 가트너에서 정의한 MSA

## Inner Architecture

- 어플리케이션 내부의 서비스를 어떻게 설계하는지에 관한 것

## Outer Architecture

### External Gateway

- 전체 서비스 외부로부터 들어오는 접근을 처리
- 사용자 인증과 권한 정책 관리 수행
- API Gateway가 핵심적인 역할을 담당

### Service Mash

- MSA 내부에서 마이크로 서비스 간의 통신을 담당(연결, 관리, 관찰, 추적)
- Service Discovery, Routing, 트래픽 관리 등을 진행

### Backing Services

- 어플리케이션이 실행될 때 네트워크를 통해 사용할 수 있는 모든 서비스(DB, Cacehe System, SMTP Service 등)를 말함
- Message Queue를 이용해 비동기 방식으로 통신

### Telemetry

- 서비스를 모니터링 하고, 여러 이슈에 대응

### CI/CD Automation

- 어플리케이션 개발 단계를 자동화해 보다 짧은 주기로 고객에게 배포