# NoSQL

## NoSQL 이란?

NoSQL은 RDBMS의 형태가 아닌 비관계형 모델을 이용해 데이터를 저장하는 것을 말한다. 기존 관계형 데이터 베이스의 한계를 극복할 수 있고 새로운 형태의 확장성을 갖는다.

### 특징

1. 관계형 모델을 사용하지 않으며 테이블간의 조인기능이 없음.
2. 직접 프로그래밍을 하는 등의 비SQL 인터페이스를 통한 데이터 엑세스
3. 대부분 여러 대의 데이터베이스 서버를 묶어(클러스터링) 하나의 데이터베이스를 구성
4. 관계형 데이터베이스에서 지원하는 Data처리 완결성(ACID) 미보장
5. 데이터 스키마와 속성들을 다양하게 수용 동작 정의(Schema-less)
6. 데이터베이스의 중단 없는 서비스와 자동 복구 기능지원
7. 확장성, 가용성, 높은 성능

### 종류

#### Key-Value Database

Key, Value의 쌍으로 데이터가 저장된다. NoSQL 데이터베이스 중 가장 단순한 구조이다. 때문에 **저장과 조회**라는 가장 간단한 원칙에 충실하다.

Key값은 고유한 값으로 유지되어야하며, 값에 모든 데이터 타입을 허용한다. 그래서 개발자들이 데이터 입력 단계에 검증 로직을 제대로 구현하는 것이 중요하다.

Key-Value Database는 데이터를 자주 읽고 쓴느 애플리케이션에 적합하다.

- Redis
- Riak
- Oracle Berkely
- AWS DynamoDB

##### 활용사례

1. 성능 향상을 위한 관계형 데이터베이스에서의 캐싱
2. 장바구니 같은 웹 애플리케이션에서 일시적인 속성 추적
3. 모바일 애플리케이션용 사용자 데이터 정보와 구성 정보 저장
4. 이미지나 오디오 파일 같은 대용량 객체 저장

#### Document Database

Key-value Database와 같이 데이터 저장에 Key-Value Type을 사용한다. 하지만, Document Database는 값을 문서의 형태로 저장한다. 여기서 문서는 보통 JSON이나 XML 표준 형식을 말한다.

문서 자체가 스키마가 되며 문서별로 다른 필드를 가질 수 있다. 따라서 개발자가 애플리케이션에서 데이터를 입력하는 단계를 개발 시 컬럼과 필드의 관리가 제대로 이루어지도록 보장하는 것이 중요하다.

```
{
"_id": "10006546",
"listing_url": "https://www.airbnb.com/rooms/10006546",
"name": "Ribeira Charming Duplex",
"summary": "Fantastic duplex apartment with three bedrooms, located in the historic area of Porto, Ribeira (Cube)...",
"house_rules": "Make the house your home...",
"property_type": "House",
"calendar_last_scraped": {
    "$date": {
       "$numberLong": "1550293200000"
        }
    },
"amenities": [
    "TV",
    "Cable TV",
    "Wifi",
    "Kitchen",
    "Paid parking off premises",
    "Smoking allowed",
    "Microwave"
    ]
}
```

- MongoDB
- CouchDB
- Couchbase

##### 활용사례

1. 데용량 데이터를 읽고 쓰는 웹 사이트용 백엔드 지원
2. 제품처럼 다양한 속성이 있는 데이터 관리
3. 다양한 유형의 메타데이터 추적
4. JSON 데이터 구조를 사용하는 애플리케이션
5. 비정규화된 중첩 구조의 데이터를 사용하는 애플리케이션

#### Graph Database

Nodes, Relationship, Key-Value 데이터 모델을 지원하며 RDF와 속성 그래프 같은 두 가지 유형을 꼽을 수 있다.

**RDF**
자원을 기술하는 형태로 Attributes와 Relationship으로 표현된다. Entity가 가지는 특성이 Attributes로 표현되고 Entity 와 Entity 사이의 관계를 표현하는 것이 Relationship이다.

**속성 그래프**
데이터 간의 관계를 모델링하는데 사용되며 이 관계를 기반으로 데이터 분석 작업이 이루어진다. 속성 그래프에는 주제에 대한 자세한 정보를 포함하는 꼭짓점과 이 꼭짓점 간 관계를 나타내는 엣지를 갖고있다.

- Neo4J
- OrientDB

##### 활용사례

1. 소셜 미디어 분석
2. 신용카드 사기 분석
