# Socket 통신

Server와 Client가 특정 포트를 통해 실시간으로 **양방향 통신**을 하는 방식. Socket 연결은 TCP/IP 프로토콜을 기반으로 맺어진 네트워크 연결방식이다.  
WebSocket도 결국엔 http 프로토콜 위에서 동작하면서 4계층의 TCP에 의존하기 때문에 HTTP의 포트인 80(ws), 443(wss) 과 호환되도록 설계되었다.  
기존에 사용하던 HTTP 통신방식은 단방향 통신으로 실시간으로 통신하는 것처럼 보이기 위해서는 polling 같은 기법을 이용하였다. 하지만, http는 단방향 통신을 위해 만들어진 통신방식이기 때문에 header가 무거워 서버에 부하를 줄 수 밖에 없는 통신방식이다.

## 특징

1. 양방향 통신
   데이터 송수신을 동시에 처리할 수 있는 통신방법. 클라리언트와 서버가 서로 원할 때 데이터를 주고 받을 수 있으니까.
2. 실시간 네트워킹
   웹 환경의 데이터들은 연속된 데이터를 빠르게 보여주거나 여러 단말기에 빠르게 데이터를 교환하는 실시간 처리가 가능해야함.
3. 메세지에 포함될 수 있는 형식은 텍스트와 바이너리이다.

## 연결과정

TCP 3-way handshake를 통해 논리적인 접속을 성립한다. 이는 양 쪽 모두 데이터를 전송할 준비가 되었다는 것을 보장하기 위함이다.

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRa47w%2FbtqzOLoiZi1%2FBW955Cw40ZUiaDaj5Ocyl0%2Fimg.png)

기본적으로 과정을 3개로 나눌 수 있다.

1. Opening Handshake
   클라이언트에서 <U>핸드쉐이크 요청</U>을 전송하고 응답으로 <U>핸드쉐이크 응답</U>을 받는다. (응답 코드 101, 이는 '프로토콜 전환'을 서버가 승인했음을 알리는 것)  
    _ Upgrade : 프로토콜을 전환하기 위해 사용하는 헤더. 웹소켓 요청시 반드시 'websocket'이라는 값을 가지며, 이 값이 없거나 다른 값이면 cross-protocol attack이라고 간주하여 웹소켓 접속을 중지시킨다.
   _ Connection : 현재의 전송이 완료된 후 네트워크 접속을 유지할 것인지에 대한 정보. 웹소켓 요청 시에 반드시 'Upgrade'라는 값을 가진다. 역시 이 값이 아니면 접속 중지.
   _ Sec-WebSocket-Key : 유효한 요청인지 확인하기 위한 키 값
   _ Sec-WebSocket-Protocol : 사용하고자 하는 하나 이상의 웹 소켓 프로토콜 지정. 필요한 경우에만 사용.
   _ Sec-WebSocket-Version : 클라이언트가 사용하고자 하는 웹소켓 프로토콜 버전.
   _ Origin : 모든 브라우저는 보안을 위해 이 헤더를 보낸다. (Cross-Site WebSocket Hijacking 같은 공격을 피하기 위해). 대부분의 어플리케이션은 이 헤더가 없는 요청을 거부하며 이러한 이유로 CORS 정책이 만들어졌다.

2. Data Transfer
   1번 과정과 같이 웹소케 연결이 수립되면, 데이터 전송이 시작된다. 여기서부터는 `메세지`라는 개념으로 데이터를 주고받는데 이 메세지는 하나의 '프레임'으로 구성되어있다. 핸드 쉐이크가 끝난 시점부터 서버와 클라이언트는 서로 살아있는지 확인하기 위해 heartbeat 패킷을 보내주며(보내는 주기를 클라이언트, 서버 둘 다에 설정해줄 수 있다.) 주기적으로 ping을 보내 체크한다.

3. Close Handshkae
   클라이언트와 서버 모두 커넥션 종료를 하기 위한 컨트롤 프레임을 전송할 수 있다. 이 컨트롤 프레임은 Closing Handshake를 시작하라는 특정한 시퀀스를 포함한 데이터를 가지고있다. 어느 쪽이든 커넥션을 종료한다는 프레임을 보내면 다른 한 쪽에서는 이에 대한 응답으로 Close 프레임을 전송하게 된다.

## 개발 방법

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbGPgWc%2FbtqzOL2VFc2%2FkKklzXDAGZPbHfGFh6jHE1%2Fimg.png)

### 클라이언트

위와 같이 client a와 client b는 웹환경에서 client c는 모바일 환경에서 동작하고 있다고 가정했을 때, 이 3개의 애플리케이션은 웹서버d와 웹 소켓을 이용해 통신이 가능하다. 모두 "ws://jocker.com/say"로 접속 요청을 하고 있다. 이렇게되면 클라이언트와 서버는 열려진 웹소켓을 통해 메세지를 주고받을 수 있게 된다.

- chrome, edge 등 대부분의 브라우저 : WebSocket API
- 위 API를 제공하지 않는 경우 : SockJS 라이브러리 (HTTP Streaming이나 HTTP Long Polling 등의 기술들을 통해 WebSocket과 동일하게 동작하도록 해줌)
- android : WebSocketClient 라이브러리

```js
//WebSocket API
{
    var uri = "ws://jocker.com/say"
    var ws = new WebSocket(uri);
    ws.onopen = function() {
        ..
    }
}
```

### 서버

```java
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final ChatWebSocketHandler chatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/ws/chat").setAllowedOrigins("*");  // 웹소켓에 접속하기 위한 endpoint
        registry.addHandler(chatWebSocketHandler, "/ws/chat").setAllowedOrigins("*").withSockJS();
    }
}
```
