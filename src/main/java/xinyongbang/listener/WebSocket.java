package xinyongbang.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import xinyongbang.application.chat.IChatAppService;
import xinyongbang.application.friend.IFriendAppService;
import xinyongbang.application.friend.representation.FriendRepresentation;
import xinyongbang.listener.command.Receive;
import xinyongbang.listener.response.BaseResponse;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@ServerEndpoint(value = "/socket", configurator = SpringConfigurator.class)
public class WebSocket {

    private byte[] buffer = new byte[1024 * 1024];

    public static Map<String, Session> sessionMap;

    @Autowired
    private IChatAppService chatAppService;

    @Autowired
    private IFriendAppService friendAppService;

    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        try {

            Receive receive = JSON.parseObject(message, Receive.class);
            if (null != receive) {
                String content = receive.getContent();
                switch (receive.getType()) {
                    // 保持连接
                    case 1:
//                        userService.updateStatus(content, 1);
                        if (sessionMap.containsKey(content)) {
                            sessionMap.replace(content, session);
                        } else {
                            sessionMap.put(content, session);
                        }
                        break;
                    // 断开连接
                    case 2:
                        if (sessionMap.containsKey(content)) {
                            sessionMap.remove(content);
                        }
                        break;
                    // 发送个人消息
                    case 3:
//                        SendChat sendFriendMessage = JSON.parseObject(content, SendChat.class);

//                        chatAppService.send(sendFriendMessage);

//                        BaseResponse response_3 = new BaseResponse(3, System.currentTimeMillis() - startTime, content);

//                        if (XXRunnable.user_ip.containsKey(sendFriendMessage.getToUserId())) {
//                            DatagramPacket send = new DatagramPacket(buffer, buffer.length);
//                            send.setSocketAddress(XXRunnable.user_ip.get(sendFriendMessage.getToUserId()));
//                            send.setData(JSON.toJSONString(response_3).getBytes(Charset.forName("UTF-8")));
//                            XXRunnable.serviceSocket.send(send);
//                        }
//                        if (sessionMap.containsKey(sendFriendMessage.getToUserId())) {
//                            sessionMap.get(sendFriendMessage.getToUserId()).getBasicRemote().sendText(JSON.toJSONString(response_3));
//                        }

                        break;

                    // 发送好友请求
                    case 4:

//                        BaseResponse response_4 = new BaseResponse(4, System.currentTimeMillis() - startTime, content);

//                        AddFriend addFriend = JSON.parseObject(content, AddFriend.class);

//                        try {
//                            friendAppService.addFriend(addFriend);

//                            if (XXRunnable.user_ip.containsKey(addFriend.getUserId())) {
//                                DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
//                                add_return.setSocketAddress(XXRunnable.user_ip.get(addFriend.getUserId()));
//                                add_return.setData(JSON.toJSONString(response_4).getBytes(Charset.forName("UTF-8")));
//                                XXRunnable.serviceSocket.send(add_return);
//                            }

//                            if (XXRunnable.user_ip.containsKey(addFriend.getToUserId())) {
//                                DatagramPacket add = new DatagramPacket(buffer, buffer.length);
//                                add.setSocketAddress(XXRunnable.user_ip.get(addFriend.getToUserId()));
//                                add.setData(JSON.toJSONString(response_4).getBytes(Charset.forName("UTF-8")));
//                                XXRunnable.serviceSocket.send(add);
//                            }
//                            if (sessionMap.containsKey(addFriend.getToUserId())) {
//                                sessionMap.get(addFriend.getToUserId()).getBasicRemote().sendText(JSON.toJSONString(response_4));
//                            }

//                        } catch (SocketException e) {
//                            session.getBasicRemote().sendText(JSON.toJSONString(response_4));
//                            e.printStackTrace();
//                        }

                        break;

                    // 回复好友请求
                    case 5:
//                        ReceiveAddFriend receiveAddFriend = JSON.parseObject(content, ReceiveAddFriend.class);
//
//                        BaseResponse response_5 = new BaseResponse(5, System.currentTimeMillis() - startTime, content);
//
////                        try {
//                            friendAppService.receive(receiveAddFriend);
//
//                            FriendRepresentation friend = friendAppService.searchByID(receiveAddFriend.getFriendId());
//
////                            if (XXRunnable.user_ip.containsKey(receiveAddFriend.getUserId())) {
////                                XXRunnable.user_ip.get(receiveAddFriend.getUserId());
////                                DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
////                                add_return.setSocketAddress(XXRunnable.user_ip.get(receiveAddFriend.getUserId()));
////                                add_return.setData((5 + receiveAddFriend.getUserId()).getBytes(Charset.forName("UTF-8")));
////                                XXRunnable.serviceSocket.send(add_return);
////                            }
//
//                            if (XXRunnable.user_ip.containsKey(friend.getUser().getId())) {
//                                DatagramPacket receive_add = new DatagramPacket(buffer, buffer.length);
//                                receive_add.setSocketAddress(XXRunnable.user_ip.get(friend.getUser().getId()));
//                                receive_add.setData(JSON.toJSONString(response_5).getBytes(Charset.forName("UTF-8")));
//                                XXRunnable.serviceSocket.send(receive_add);
//                            }
//                            if (sessionMap.containsKey(friend.getUser().getId())) {
//                                sessionMap.get(friend.getUser().getId()).getBasicRemote().sendText(JSON.toJSONString(response_5));
//                            }

//                        } catch (SocketException e) {
//                            session.getBasicRemote().sendText(JSON.toJSONString(response_5));
//                            e.printStackTrace();
//                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose(Session session) {

        for (Map.Entry entry : sessionMap.entrySet()) {
            if (entry.getValue().equals(session)) {
                String key = (String) entry.getKey();
                sessionMap.remove(key);
//                userService.updateStatus(key, 0);
            }
        }
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
