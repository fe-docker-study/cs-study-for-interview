### 토픽

![image](https://user-images.githubusercontent.com/47748246/177091813-942e8375-fe93-4acc-b027-3be6b6c87cfd.png)

- Topic
    - 데이터 베이스의 테이블
    - 파일 시스템의 폴더

### 파티션

![image](https://user-images.githubusercontent.com/47748246/177091852-b09c2078-553f-48a8-95ae-1a722cfb64b6.png)

- 하나의 Topic은 여러 개의 partition으로 구성될 수 있음
- partition은 큐 형태. producer가 파티션에 데이터 추가하면, consumer는 파티션에서 데이터를 오래된 것부터 하나씩 가져간다.
- 단, 이때 consumer가 데이터를 가져가더라도, 파티션에 있는 데이터는 삭제되지 않는다.

![image](https://user-images.githubusercontent.com/47748246/177091877-117384e0-0b33-4970-b0a5-d0ec48264846.png)

- 이때 **다른 컨슈머 그룹의 컨슈머**가 파티션에 있는 데이터를 처음부터 가져갈 수 있다.
    - 단, `auto.offset.reset = earliest` 여야 한다.

- 예 : 같은 click_log에 대해
    - 클릭 로그를 분석하고 시각화 하기 위해 ES (엘라스틱 서치)에 저장하기도 하고
    - 클릭 로그를 백업하기 위해 하둡에 저장할 수도 있다.
![image](https://user-images.githubusercontent.com/47748246/177091918-21690c08-c39d-4945-bc17-f6a221ebf4b6.png)

### 파티션이 2개 이상인 경우

![image](https://user-images.githubusercontent.com/47748246/177091986-63ea0f2b-e36b-415e-8630-d6a60b6505a6.png)


- 새로운 데이터가 추가될 때 어느 파티션에 들어가는가?
    1. 키가 null 이고, 기본 파티셔너 사용할 경우 
        
        → 라운드 로빈으로 할당
        
    2. 키가 있고, 기본 파티셔너 사용할 경우
        
        → 키의 Hash 값을 구하고, 특정 파티션에 할당
        

- 주의 : 파티션은 늘릴 수 있지만 줄일 수는 없음
- 왜 파티션을 늘리는 걸까?
    
    : 파티션을 늘리면 컨슈머의 개수를 늘려서 데이터 처리를 분산시킬 수 있다. 
    
- 파티션의 데이터(record)는 언제 삭제될까 → 옵션에 따라 다르다
    - [log.retention.ms](http://log.retention.ms) : 최대 record 보존 시간
    - log.retention.byte : 최대 record 보존 크기 (byte)
