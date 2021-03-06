# OAuth 2.0

- OAuth 2.0은 다양한 플랫폼 환경에서 권한 부여를 위한 산업 표준 프로토콜

- 제 3의 앱이 자원의 소유자인 서비스 이용자를 대신하여 서비스를 요청할 수 있도록 자원 접근 권한을 위임하는 방법

  

## 사용사례

- 인스타그램은 SNS 친구 찾기를 위해 페이스북의 오픈 API를 사용하여 자원 소유자의 친구 목록에 접근하며, 이때 OAuth 2.0 기반으로 인증 및 권한 승인

- 금융고객(자원소유자)의 뱅킹 ID/PASSWORD를 핀테크 애플리케이션에 직접적으로 제공하지 않고, 접근 토큰(Access Token)*을 기반으로 계좌 이체 등에 대한 권한을 위임

  

## OAuth2.0 보안이점

- 자원 소유자가 클라이언트에 비밀번호를 제공할 필요가 없으며, 피싱 등의 위협 감소

- 클라이언트 개발자는 자원 소유자의 비밀번호에 대한 안전한 저장 및 노출을 고려하지 않아도 됨

- 자원 소유자의 모든 권한이 아닌, 클라이언트에게 반드시 필요한 권한만 제한적으로 제공 가능

- 자원에 대한 클라이언트의 접근을 각 클라이언트별로 차단 및 권한 취소가 가능하며, 권한의 범위를 제어 가능 (비밀번호 방식의 경우, 클라이언트의 권한 취소를 위해서는 비밀번호를 변경해야하며, 접근 권한을 재부여할 클라이언트에게 비밀번호 제공 필요)

  

## OAuth1.0 vs OAuth2.0

![Untitled](https://user-images.githubusercontent.com/90545926/152669477-5af353b0-4cae-4f1f-ae00-10adb2c27723.png)

- 기존 서비스 제공자(Service Provider)를 자원 및 권한 서버로 분리하여 다수의 서비스 제공자(서버)로 구성, 웹 서비스에서 발생 가능한 권한 동기화 문제 개선
- 오픈 API 요청시, 클라이언트 인증 방법으로 서명 대신, HTTPS를 의무화하여 서버 및 클라이언트 개발 편의성 개선
- 다양한 유형의 클라이언트와 이를 고려한 권한 승인 방법을 정의
- 접근 토큰 재발급을 위한 재발급 토큰(Refresh Token)을 도입함으로서, 접근 토큰의 유효기간 단축 및 보안성 개선 (만약 Access Token의 유효기간이 과도하게 길가면 노출이 될 가능성이 높고, 공격자에 의해 장기간 악용 가능)



## 참여자

- Resource Owner(자원 소유자) : 일반적인 사용자, 보호자원에 대한 접근권한을 부여할 수 있는 개체
- Client : 제 3의 서비스, 리소스에 접근하려고 하는 주체로 서비스 제공자에게 보호자원을 요청하고 관련서비스를 제공하는 애플리케이션  ex)구글, 페이스북 아이디로 로그인이 가능한 서비스
- Resource Server(자원 서버) : API 서버,  리소스를 가지고 있는 서버 ex)정보를 제공하는 서버로 구글, 페이스북 등
- Authentication Server(권한 서버) : 클라이언트가 보호된 자원에 대한 제한된 접근을 할 수 있도록 자원접근 권한을 위임 및 관리하는 서버 ex) 실제로 로그인 서비스를 제공하는 서버. 구글, 페이스북 등



## 클라이언트 등록

- OAuth 2.0에서는 API 요청을 한 클라이언트를 식별 및 검증하기 위해, 먼저 권한 서버에 클라이언트를 등록하도록 정의
- 일반적으로 권한 서버(또는 API 개발자 사이트)에서 제공하는 HTML 양식을 통해 개발자(사)가 직접 등록 (승인된 리디렉션  URI : 자원 소유자가 클라이언트의 권한을 승인한 경우 접근토큰 등의 자격증명 정보가 반환될 URI)
- 등록이 완료되면, 권한 서버는 클라이언트 자격증명 정보 중 하나인 client_id, client_secret를 발급하며, 클라이언트 개발자는 이 자격증명을 애플리케이션 개발 시 포함



## 클라이언트 유형

- 웹 애플리케이션 : 웹 서버 상에서 실행되는 애플리케이션으로 자원 소유자는 HTML 및 웹 브라우저 또는 별도의 웹 클라이언트를 통해 접속
- 이용자 에이전트 애플리케이션 : 웹 브라우저 등 이용자의 에이전트 상에서 실행되는 애플리케이션으로 클라이언트 측 스크립트(자바 스크립트 등) 및 웹 브라우저 확장 프로그램 형태 등으로 배포
- 네이티브 애플리케이션 : PC, 스마트폰 등과 같은 이용자 단말에 직접 설치 및 실행되는 애플리케이션



## 권한 승인 개요

![Untitled](https://user-images.githubusercontent.com/90545926/152669493-4c5041db-5743-4ca5-961e-d3aebefaf1ee.png)

① 클라이언트가 자원 소유자에게 자원에 대한 접근 권한을 직/간접 요청 ( Redirect URI를 통해 자원 소유자를 권한 서버로 리다이렉션 시키며, 권한서버가 클라이언트를 식별할 수 있도록 식별 정보를 함께 전달)

② 클라이언트에게 자원에 대한 접근 권한을 승인(4가지 권한 승인 방법 정의)

③ 클라이언트가 권한 서버에게 접근 토큰을 요청

④ 권한 서버는 클라이언트를 인증 및 부여 받은 권한을 검증하고, 검증된 경우 클라이언트에게 접근 토큰을 발급(권한 승인 방법에 따라, 선택적으로 접근 토큰의 유효기간 만료 시 접근 토큰 재발급을 위한 Refresh Token 추가 발급)

⑤ 클라이언트가 자원 서버에게 접근 토큰으로 인증 및 자원을 요청

⑥ 자원 서버는 접근 토큰을 검증하고, 검증된 경우 서비스 제공

## 권한 승인 방법

### 권한 코드 승인 (Authorization Code Grant)

![Untitled](https://user-images.githubusercontent.com/90545926/152669478-63718550-8d01-42af-b6df-ee43655a8eca.png)

- 클라이언트 유형
  - 서버 웹 애플리케이션
  - 네이티브 애플리케이션(백엔드 서버 사용)
- 장기적 접근
  - 재발급 토큰을 통해 지원
- 보안성
  - 이용자 웹 브라우저를 통해 접근 토큰이 노출되지 않도록 권한 코드 사용
  - 클라이언트는 권한 코드를 사용하여 권한서버에게 직접 접근 토큰을 요청
  - 중요 자격증명 정보가 서버에 저장되기 때문에 다른 권한 승인 방법에 비해 안전함

### 암묵적 승인 (Implicit Grant)

![응답 타입이 token 일 경우 암시적 승인 타입에 해당, 권한 코드 교환없이 토큰을 바로 받음](https://user-images.githubusercontent.com/90545926/152669479-08d11600-8d31-44f7-b2f2-e25facf7dfbb.png)

응답 타입이 token 일 경우 암시적 승인 타입에 해당, 권한 코드 교환없이 토큰을 바로 받음

- 클라이언트 유형
  - 이용자 에이전트 애플리케이션
- 장기적 접근
  - 재발급 토큰 미지원으로 인해 접근 토큰의 유효 기간이 짧으면 장기적 접근 불가
- 보안성
  - 접근 토큰이 웹 브라우저에 전달 및 저장되기 때문에 웹 브라우저, 클라이언트, 이용자에 대한 신뢰가 높은 경우에 적합
  - 접근 토큰 유출을 고려하여, 접근 토큰의 유효 기간에 대한 적절한 조절이 필요함

### 자원 소유자 비밀번호 자격증명 승인 (Resource Owner Password Credentials Grant)

![클라이언트가 암호를 사용해 토큰에 대한 사용자 자격 증명을 교환](https://user-images.githubusercontent.com/90545926/152669481-d5250f79-5e66-4076-abb1-c414bf8a4651.png)

클라이언트가 암호를 사용해 토큰에 대한 사용자 자격 증명을 교환

- 클라이언트 유형
  - 모든 클라이언트 유형 가능
  - 일반적으로 API 제공 업체가 배포한 애플리케이션
- 장기적 접근
  - 재발급 토큰을 통해 지원
- 보안성
  - 자원 소유자의 비밀번호가 애플리케이션에 노출되고, 피싱 위험이 존재
  - 접근 토큰 발급 요청 시에만 비밀번호가 사용되기 때문에 클라이언트에 인증정보를 저장할 필요 없음

### 클라이언트 자격증명 승인 (Client Credentials Grant)

![클라이언트 자신이 관리하는 리소스, 혹은 권한 서버에 해당 클라이언트를 위해 제한된 리소스 권한이 설정되어 있는 경우 사용, 즉 클라이언트 웹 서버자체가 resource owner인 경우](https://user-images.githubusercontent.com/90545926/152669482-a3bf53ad-2639-45b5-81d6-7890ddd01c33.png)

클라이언트 자신이 관리하는 리소스, 혹은 권한 서버에 해당 클라이언트를 위해 제한된 리소스 권한이 설정되어 있는 경우 사용, 즉 클라이언트 웹 서버자체가 resource owner인 경우

- 클라이언트 유형
  - 클라이언트가 데이터를 소유하고 있거나, 접근 위임이 이미 허용된 경우
- 장기적 접근
  - 재발급 토큰 미지원
  - 클라이언트 인증만으로 즉시 접근 토큰 재발급 가능
- 보안성
  - 클라이언트 인증만으로 접근 토큰을 발급하기 때문에 안전한 인증 방식 사용 및 인증 정보의 규칙적인 변경이 필요함

## 권한 코드 승인

![Untitled](https://user-images.githubusercontent.com/90545926/152669483-d196bd7f-0e64-4ad3-b519-a744b2965421.png)

① 클라이언트가 웹 브라우저에게 리다이렉트 URI를 전달하여 권한 서버의 인증 및 권한 확인 웹 페이지로 리다이렉션 시킴 (권한 서버가 클라이언트를 식별할 수 있도록 client_id를 함께 전달)

② 권한 서버의 자원 소유자에 대한 인증 방식을 통해 인증 및 자원에 대한 접근 권한 승인 요청(일반적인 웹 서비스에서는 ID/PASSWORD 방식을 통해 자원 소유자 인증)

③ 권한 서버는 웹 브라우저를 다시 클라이언트에게로 리다이렉션시키며, 이때 자원 소유자로부터 자원에 대한 접근 권한을 승인 받았음을 나타내는 권한코드를 클라이언트에게 전달 (클라이언트 등록 시, 권한 서버에 제출한 URI, 또는 ① 단계에서 전달한 리다이렉트 URI 사용)

④ 클라이언트는 접근 토큰을 요청하기 위해, 권한 코드와 함께 클라이언트 인증을 위한 client_id 및 client_secret을 권한 서버에 전달

⑤ 권한 서버는 접근 토큰을 클라이언트에게 반환(접근 토큰에 유효 기간이 존재하는 경우, 기간 만료까지 남은 시간(expires_in)과 재발급 토큰(refresh_token) 정보도 함께 반환)

- 접근 토큰 재발급 : 접근 토큰의 유효 기간이 만료되어 재발급 받을 경우, 위 단계의 반복 없이 재발급 토큰과 client_id 및 client_secret으로 권한 서버에 재발급 요청

### 페이코 예시

![페이코 OAuth2.0 인증과정](https://user-images.githubusercontent.com/90545926/152669484-958627a9-e4a8-4bce-b47d-9a404842072c.png)

페이코 OAuth2.0 인증과정

![페이코 OAuth2.0 프로세스](https://user-images.githubusercontent.com/90545926/152669470-f66de50a-b4ee-4a4d-a1db-5b9a1b82acd3.png)

페이코 OAuth2.0 프로세스



## 보안취약점

### CSRF

```
1. 기존 계정과 SNS 계정 연동 요청
2. 요청 SNS 로그인 페이지 출력 (Client ID 값이 포함된 로그인 페이지)
3. ID/PW 를 통해 SNS 계정에 로그인
4. 로그인 성공 시 인증 서버로부터 Authorization code를 발급 받음 (Authentication Server -> 사용자)
5. 발급 받은 code 값과 state 값을 Client 서버로 전송 (사용자 -> Client Server)
6. code 값과 state 값 검증 후 Client 서버에 로그인 되어있는 계정과 SNS 계정이 연동됨
```

state 값은 CSRF token 역할을 하는데, 만약 state 값에 대해 검증이 누락되어 있거나 미흡할 경우 사용자 계정을 탈취

5번 과정에서 Client 서버로 전송하는 내용을 추출하여 만든 CSRF 공격 페이지에 사용자(피해자)가 접근하면, 사용자(피해자) 계정과 공격자 계정이 연동되며, 공격자의 SNS 계정을 통해 사용자(피해자) 계정으로 로그인을 할 수 있음

### Covert Redirect

- redirect_uri 파라미터 값에 대해 검증이 누락되거나 미흡할 경우 발생하는 취약점

정상적인 경로라면 사용자(Resource Owner)가 로그인 성공 후 발급받은 Authorization code를 Client로 전달하나 공격자가 클라이언트와 권한 서버간의 권한승인 요청을 가로채서 변조된 Redirect URI를 보내 로그인을 유도. 4번 단계에서 사용자(피해자)가 Redirect URI 값이 변조된 URL로 로그인할 경우, Authorization code 값이 공격자 서버로 전달되어 공격자는 사용자(피해자)의 계정을 탈취

![Untitled](https://user-images.githubusercontent.com/90545926/152669472-7a8a6ac2-a01e-4e4d-81f5-166c5ddf2520.png)