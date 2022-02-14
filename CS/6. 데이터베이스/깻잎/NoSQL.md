# NoSQL 데이터베이스

## 1. 정의
"non SQL" or "non relational”
전통적인 관계형 데이터베이스보다 덜 제한적인 일관성 모델을 이용하는 데이터의 저장 및 검색을 위한 매커니즘을 제공한다. 단순 검색 및 추가 작업을 위한 매우 최적화된 key-value 저장 공간으로, 레이턴시와 스루풋과 관련하여 상당한 성능 이익을 내는 것이 목적이다.

>  레이턴시(latency) : 자극과 반응 사이의 시간이며, 더 일반적인 관점에서는 관찰되는 시스템에서의 어떠한 물리적 변화에 대한 원인과 결과 간의 지연 시간이다.
>  
>  스루풋(throughput) 또는 처리율(處理率) : 통신에서 네트워크 상의 어떤 노드나 터미널로부터 또 다른 터미널로 전달되는 단위 시간당 디지털 데이터 전송으로 처리하는 양을 말한다.
   
   
## 2. 특징
1. NoSQL은 RDBMS와는 달리 데이터 간의 관계를 정의하지 않는다
2. RDBMS에 비해 훨씬 더 대용량의 데이터를 저장할 수 있다
3. 분산형 구조
4. 고정되지 않은 테이블 스키마
   
   
## 3. 유형
![nosql](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcgJiay%2FbtrkgIMiwAx%2FOlvxwwPkHQSNezbxIW3CxK%2Fimg.png)
* Document   
![nosql](https://media.vlpt.us/images/hanblueblue/post/8e22dd6f-e1b1-4b5a-93d5-1ab1418a907d/image.png)
   - 데이터 저장방식은 키-값 데이터베이스와 비슷하나, 값을 문서로 저장한다는 차이점이 있다.
   - 여기서 말하는 문서란 반정형화된 엔티티로 XML, JSON 같은 형식이 이에 속한다. 워드나 엑셀 등의 문서 파일이 아니라는 점.
      + 여러 속성을 저장할 수 있다.
      + 고정된 스키마로 저장할 필요가 없으므로 높은 유연성을 지닌다.
      + 문서에 하나의 값이 아닌 여러개의 속성을 지닐 뿐 아니라, 또 다른 문서를 중첩해서 입력할 수 있다.
      + 질의어를 통해 데이터를 찾을 수 있다.
* Graph   
![nosql](https://media.vlpt.us/images/hanblueblue/post/4f8e6404-915b-4dc8-8b79-29ac88a6d0a2/image.png)
   - 많은 객체와 각 연결 관계를 표현하는데 용이한 방법.
   - SNS, 교통망, 전략망 등 연결 관계가 복잡한 형태의 데이터를 다루는데 사용되어진다.
   - 노드 Node와 관계 Relationship 또는 정점 vertext과 엣지 edge 라는 형태로 데이터를 표현하며, 노드는 구별할 수 있는 식별자와 여러 가지 속성을 가지고 관계는 두 노드 간의 연결을 말하며 연결에 대한 속성을 포함한다.
      + 노드와 관계 모두 각각의 속성을 가지고 있다.
      + 가까운 관계에 있는 노드들을 잇기 위한 목적으로 설계되었다.
      + 관계형 데이터베이스로는 연산이 복잡한 작업을 그래프 데이터베이스로 대체할 수 있다.
* Key-Value   
![nosql](https://media.vlpt.us/images/hanblueblue/post/4e92389a-f1c9-4b7c-bff9-275d2eff3c2d/image.png)
   - 프로그램을 하다보면 자주 만나게 되는 Key-value 형태를 가지는 데이터베이스다.
   - 데이터를 식별하기 위해서 key값을 알면 연결된 데이터를 확인할 수 있는 구조로 비교적 간단한 형태의 데이터베이스에 적합하다.
      + 기본적인 패턴으로 Key,value가 하나의 묶음으로 저장되는 구조로 단순한 구조이기에 속도가 빠르며 분산 저장 시 용이하다.
      + Key 안에 (Column, Value) 형태로 된 여러개의 필드를 갖는다.
      + 값에 모든 데이터 타입을 허용하며, 그래서 개발자들이 데이터 입력 단계에서 검증 로직을 제대로 구현하는 것이 중요하다.
      + 테이블간 조인을 고려하지 않으므로 RDB(Relational Database)에서 관리하는 외부키나, 컬럼별 constraints등이 필요 없다.
* Wide Column
![nosql](https://media.vlpt.us/images/hanblueblue/post/1ab12c52-c4f9-4fdc-a273-194000706244/image.png)
   - NoSQL 중 가장 복잡한 유형이다.
      + 컬럼 Column 은 이름과 값을 가질 수 있다.
      + 하나의 로우 row 는 여러 개의 컬럼이 모여서 이루어지며, 각각의 로우는 다른 컬럼을 가질 수 있다.
      + 연관된 컬럼이 여러개가 있다면 묶어서 '컬럼 패밀리'로 만들어서 사용한다.
      + 문서 데이터베이스와 비슷하게 고정된 스키마를 사용하지 않는다.
      + 로우 별로 다른 데이터 형태를 가지므로 관계형 데이터에비으와 달리 조인이 불가능하다.
      + 질의방법은 일반적인 SQL과 비슷하고, CREATE, COLUMNFAMILY 등 이에 특화된 기능도 존재한다.

