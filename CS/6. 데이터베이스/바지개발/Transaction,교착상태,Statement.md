# Transaction

데이터베이스의 상태를 변화시키기 위해서 수행하는 논리적인 작업 단위를 뜻함

![Untitled](https://user-images.githubusercontent.com/90545926/152763542-b538eaff-8d06-4fc7-b4c4-1f7a64453501.png)

- 여러 개의 명령어의 집합이 정상적으로 처리되면 정상 종료

- 하나의 명령어라도 잘못되면 전체 취소

- 트랜잭션을 쓰는 이유는 데이터의 일관성을 유지하면서 안정적으로 데이터를 복구하기 위함

  

## 트랜잭션(Transaction)의 특징 : ACID

데이터베이스 트랜잭션이 안전하게 수행된다는 것을 보장하기 위한 특징

- 원자성(Atomicity) : 트랜잭션이 DB에 모두 반영되거나, 혹은 전혀 반영되지 않아야 함

- 일관성(Consistency) : 트랜잭션의 작업 처리 결과는 항상 일관성이 있어야 함

- 독립성(Isolation) : 둘 이상의 트랜잭션이 동시에 병행 실행되고 있을 때, 어떤 트랜잭션도 다른 트랜잭션 연산에 끼어들 수 없음

- 영속성(Durability) : 트랜잭션이 성공적으로 완료 되었으면 결과는 영구적으로 반영되어야 함

  

## 트랜잭션 상태

![Untitled](https://user-images.githubusercontent.com/90545926/152763545-4c271d27-77b8-46a6-95f0-e1017bc0cf63.png)

- 활성(Active) : 트랜잭션이 정상적으로 실행중인 상태
- 부분 완료(Partially Committed) : 트랜잭션의 마지막 연산까지 실행했지만, Commit 연산이 실행되기 직전의 상태
- 완료(Committed) : 트랜잭션이 성공적으로 종료되어 Commit 연산을 실행한 후의 상태
- 실패(Failed) : 트랜잭션 실행에 오류가 발생하여 중단된 상태
- 철회(Aborted) : 트랜잭션이 비정상적으로 종료되어 Rollback 연산을 수행한 상태



## 트랜잭션 명령어

### 트랜잭션을 실행할 때 사용하는 명령어

- SQL Server / PostgreSQL :  'BEGIN TRANSACTION' 명령을 사용
- MySQL :  'START TRANSACTION'을 사용
- Oracle / DB2 : 트랜잭션을 시작하는 명령은 따로 없음 (SQLite에서는 'BEGIN'을 사용)

### 트랜잭션 제어 명령어

#### Commit

- 모든 작업들을 정상적으로 처리하겠다고 확정하는 명령어로 처리과정을 DB에 영구 저장하는 것
- COMMIT을 수행하면 하나의 트랜잭션 과정을 종료하는 것
- COMMIT을 수행하면 이전 데이터가 완전히 UPDATE 됨

![Untitled](https://user-images.githubusercontent.com/90545926/152763547-8c0073ca-f207-4796-a384-1c609dc48df9.png)



#### ROLLBACK

- ROLLBACK은 작업 중 문제가 발생되어 트랜잭션의 처리과정에서 발생한 변경사항을 취소하는 명령어
- 트랜잭션이 시작되기 이전의 상태로 되돌림. 즉 마지막 COMMIT을 완료한 시점으로 다시 돌아감

![Untitled](https://user-images.githubusercontent.com/90545926/152763554-4bda36ce-3009-45c2-bfb0-9a6a74ed3442.png)

#### SAVEPOINT

- savepoint를 사용하면 전체가 아닌 특정 부분에서 트랜잭션을 취소시킬 수 있음
- 취소하려는 지점을 savepoint로 명시한 뒤 rollback to  세이브포인트 이름; 을 실해하면 해당 savepoint 지점까지 처리한 작업이 rollback 됨

```sql
--savepoint 지정방법
SAVEPOINT [세이브포인트이름];

-- rollback 형태
ROllBACK TO [세이브포인트이름];
```

#### MSSQL 예시

```sql
-- 트랜잭션을 시작
BEGIN TRAN

DECLARE @id INT
SET @id = 10
INSERT Scores VALUES (@id, 10, 80, GETDATE())

IF NOT EXISTS (SELECT * FROM STAT WHERE Id=@id)
BEGIN
   -- 에러면 트랜잭션 취소
   ROLLBACK TRAN
   PRINT 'ERROR : No STAT data'
   RETURN
END 

UPDATE Stat SET Total=Total+80 WHERE Id=@id

-- 트랜잭션 완료
COMMIT TRAN
```

```sql
-- 트랜잭션을 시작
BEGIN TRAN

DECLARE @id INT
SET @id = 10

-- Scores 테이블에 입력
INSERT Scores VALUES (@id, 10, 80, GETDATE())

-- Save Point 설정
SAVE TRAN ScoreSavePoint

-- UPDATE 실행
UPDATE Stat SET Total=Total+80 WHERE Id=@id

-- 만약 UPDATE 실패하면 
-- 지정된 Save Point로 롤백
IF @@ERROR<>0 
BEGIN
    PRINT 'ERROR in UPDATE Stat Table'
    ROLLBACK TRAN ScoreSavePoint
END    

-- 로그 테이블 추가
INSERT Log Values(GETDATE())

-- 트랜잭션 완료
COMMIT TRAN
```



# 교착상태

- 여러 개의 트랜잭션(Transaction)들이 실행을 하지 못하고 서로 무한정 기다리는 상태를 의미

- 데이터베이스는 트랜잭션들의 '동시성'을 제어하기 위한 기법으로 로킹(Locking)을 사용. 이러한 로킹은 다중 트랜잭션 환경에서 데이터베이스의 일관성과 무결성을 유지하게 하지만 그 부작용으로 교착 상태를 일으킬 수 있음.
- 두 세션이 각각 Lock을 설정한 리소스를 서로 액세스하려고 마주보며 진행하는 상황일 때 둘 중 하나가 뒤로 물러나지 않으면 영영 풀릴 수 없음
- Oracle의 경우 하나의 Transaction을 Rollback하고 Alert파일에 기록됨
- SQL Server라면 업데이트 잠금(Update Lock)을 사용함으로써 교착상태 발생 가능성을 줄일 수 있음

### 순환 교착

두 세션이 필요한 리소스를 얻기 위해 서로 상대방이 Lock을 풀기를 기다리는 상태

### 변환 교착

잠금모드가 공유잠금(shared Lock)에서 업데이트 잠금(Update Lock) 혹은 배타적 잠금(Exclusive  Lock)으로 전환될 때 발생하는 문제로서, 채번(일련번호 매기는 일)과 관련해서 발생하는 경우가 많음

##### 예시 )

1. 세션 A가 트랜잭션을 건 후 어떤 Row에 공유잠금(shared  Lock) 을 건 상태

2. 세션 B도 트랜잭션을 건 후 그 Row에 공유잠금을 걸음(공유잠금끼리는 서로 호환 가능)

3. 이 상태에서 세션 A는 그 Row에 Update를 시도. 그러나 이 Row는 세션 B에서 공유잠금을 걸었으므로 세션 A는 배타적잠금을 걸기 위해 세션 B가 공유잠금을 풀어주기를 기다림

4. 이때, 세션 B도 그 Row에 Update를 시도

   

## 해결방법

### 예방 기법

- 각 트랜잭션이 실행되기 전에 필요한 데이터를 모두 로킹(Locking) 해주는 것
- 예방 기법은 데이터가 많이 필요하면 사실상 모든 데이터를 전부 로킹해주어야 하므로 트랜잭션의 병행성을 보장하지 못함 뿐만 아니라 몇몇 트랜잭션은 계속해서 처리를 못하게 되는 기아 상태가 발생할 수 있음

### 회피기법

- 자원을 할당할 때 시간 스탬프(Time Stamp)를 사용하여 교착상태가 일어나지 않도록 회피하는 방법

#### Wait-Die 방식

다른 트랜잭션이 데이터를 점유하고 있을 때 기다리거나(Wait) 포기(Die)하는 방식

1. 트랜잭션 Ti가 Tj에 의해 로킹된 데이터를 요청

2. Ti가 먼저 들어온 트랜잭션이라면 기다림 
3. 그러나 Ti가 나중에 들어온 트랜잭션이라면 포기(Die)하고, 나중에 다시 요청

#### Wound-Wait 방식

다른 트랜잭션이 데아터를 점유하고 있을 때 빼앗거나(Wound) 기다리는(Wait) 방식

- 트랜잭션 Ti가 Tj에 의해 로킹된 데이터를 요청
- Ti가 먼저 들어온 트랜잭션이라면 데이터를 선점(Wound)
- 그러나 Ti가 나중에 들어온 트랜잭션이라면 기다림(Wait)



#### 시간 스탬프(Time Stamp)

데이터베이스 병행제어를 위해 데이터 항목에 타임스탬프를 부여하여 직렬가능성을 보장하는 기법

1. 트랜잭션에서 읽기 또는 쓰기 작업이 정상적으로 완료되면 타임스탬프를 기록
2. 트랜잭션에서 읽기 또는 쓰기 작업을 수행하려고 하는 경우 타임스탬프를 확인
3. 트랜잭션 수행 도중에 타임스탬프가 갱신된 경우 트랜잭션 작업을 중단

|                                      | 데이터 X를 읽는 경우 | 데이터 X에 쓰는 경우 |
| ------------------------------------ | -------------------- | -------------------- |
| 최근에 데이터 X를 읽은 시간 이후라면 | 수행                 | 수행                 |
| 최근에 데이터 X를 쓴 시간 이후라면   | 수행                 | 수행                 |
| 아직 데이터 X를 읽고 있는 상황이라면 | 수행                 | 포기                 |
| 아직 데이터 X를 쓰고 있는 상황이라면 | 포기                 | 포기                 |

#### 탐지방법

- 교착 상태가 발생했는 지 알아내기 위해 사용하는 알고리즘은 Union-Find 알고리즘
- 그래프 형태로 표현이 가능하며, 사이클(Cycle)이 존재하는지의 여부로 교착 상태가 발생했는지 확인할 수 있음

![Union-Find 과정](https://user-images.githubusercontent.com/90545926/152763557-706a6998-b2fb-4e37-981e-687481fbb227.png)

Union-Find 과정

예시)

![트랜잭션 처리 순서](https://user-images.githubusercontent.com/90545926/152763559-c87150a4-ac45-44f9-b0bc-56de4765e883.png)

트랜잭션 처리 순서



![수행순서 (8)의 대기그래프](https://user-images.githubusercontent.com/90545926/152763560-d5f7437a-2581-40f7-a82f-c9cd30d97991.png)

수행순서 (8)의 대기그래프

수행순서 (8)의 대기 그래프에서 T1, T3, T4가 사이클을 이루고 있다는 점에서 교착 상태임을 확인할 수 있음



# Statement vs PreparedStatement

- PreparedStatement 와 Statement의 가장 큰 차이점은 캐시(cache) 사용여부

- 동일한 쿼리를 반복적으로 수행한다면 PreparedStatment가 DB에 훨씬 적은 부하를 주며, 성능도 좋음

  

## Statement

매번 쿼리를 수행할 때마다  쿼리 문장 분석 > 컴파일 > 실행의 단계를 거침

- 단일로 사용될 때 빠른 속도
- 쿼리에 인자를 부여할 수 없음
- 매번 컴파일을 수행해야 함

```java
public void insertBookBatch(Book book) {
    StringBuilder sb = new StringBuilder("BEGIN BATCH ")
      .append("INSERT INTO ").append(TABLE_NAME)
      .append("(id, title, subject) ")
      .append("VALUES (").append(book.getId()).append(", '")
      .append(book.getTitle()).append("', '")
      .append(book.getSubject()).append("');")
      .append("INSERT INTO ")
      .append(TABLE_NAME_BY_TITLE).append("(id, title) ")
      .append("VALUES (").append(book.getId()).append(", '")
      .append(book.getTitle()).append("');")
      .append("APPLY BATCH;");
 
    String query = sb.toString();
    session.execute(query);
}
```



## PreparedStatement

처음 한 번만 쿼리 문장 분석 > 컴파일 > 실행의 단계를 거친 후 캐시에 담고, 동일한 쿼리가 수행될 시 재사용함

- 여러번 수행될 때 빠른 속도
- 쿼리에 인자를 부여할 수 있음
- 처음 프리컴파일 된 후, 이후에는 컴파일을 수행하지 않음

```java
public static void main(String[] args) {

		Session session = Connection.connect();		
		PreparedStatement preparedStatement = session.prepare("insert into user (id, name, age) values (?, ?, ?)");

		try {
			BoundStatement boundStatement = preparedStatement.bind(UUIDs.timeBased(), "Hector", 34);
			ResultSet rs = session.execute(boundStatement);
			System.out.println(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Connection.close();
	}
```



## PreparedStatement를 사용하는 이유

- 사전 컴파일을 한 번만 하므로 동적 SQL의 반복 실행에 대해 더 빠름
- 데이터베이스 statement 캐싱으로 DB 실행 성능 향상(미리 만들어진 접근 계획을 재사용하므로서 데이터베이스에 대한 로드를 줄여 줌)

- PreparedStatement 는 바이너리 통신 프로토콜로 더 적은 대역폭으로 더 빠르게 DB 서버를 호출할 수 있음

- 따옴표 및 기타 특수 문자의 내장 이스케이프를 통해 SQL 주입 공격을 자동으로 방지
- 쿼리 코드와 매개변수 값을 분리해 가독성을 높임

- cache는 데이터베이스가 확장된 것으로 모든 애플리케이션이 유사한 파라메터화된 sql을 사용하면 하나의 애플리케이션이 다른 애플리케이션에 의해 사용된 prepared statements를 이용하므로 캐시 스키마의 효율성이 증대됨
- 애플리케이션이 이전에 사용했던 prepared statements 호출을 재사용해서 JDBC driver에 대한 호출의 수를 감소시켜 성능의 향상

### 예시 ) 속도비교 - Goldilocks 데이터베이스

데이터 건수가 1,000,000인 테이블 PERFORMANCE 조회

```java
//Statement 사용
s_sTime = System.currentTimeMillis();
Statement stmt = con.createStatement();
for(i = 0; i < 1000000; i++){
    stmt.executeQuery("SELECT C1, C2, C3 FROM PERFORMANCE WHERE C1 = " + i );
}
s_eTime = System.currentTimeMillis();
System.out.println("Statement Milli Time : " + (s_eTime-s_sTime));

//Statement Milli Time : 124750
```

```java
//PreparedStatement
s_sTime = System.currentTimeMillis();
PreparedStatement pstmt = con.prepareStatement("SELECT C1, C2, C3 FROM PERFORMANCE WHERE C1 = ?");
for(i = 0; i < 1000000; i++){
    pstmt.setInt(1, i);
    pstmt.executeQuery();
}
s_eTime = System.currentTimeMillis();
System.out.println("PreparedStatement Milli Time : " + (s_eTime-s_sTime));

//PreparedStatement Milli Time : 25825
```



## Mybatis

### PreparedStatement: #

```sql
--mybatis mapper
    SELECT ID FROM test WHERE ID = #{ID}
--Oracle로 넘어온 쿼리
    SELECT ID FROM test WHERE ID = ?
--실제 수행 쿼리
    SELECT ID FROM test WHERE ID = 'admin'
```

### Statement : $

```sql
-- mybatis mapper
	SELECT ID FROM test WHERE num= ${num}
--Oracle로 넘어온 쿼리
	SELECT ID FROM test WHERE num = 77
--실제 수행 쿼리
	SELECT ID FROM test WHERE num = 77
```