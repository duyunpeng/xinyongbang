package xinyongbang.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import xinyongbang.application.chat.IChatAppService;
import xinyongbang.application.friend.IFriendAppService;
import xinyongbang.application.friend.representation.FriendRepresentation;
import xinyongbang.listener.command.Push;
import xinyongbang.listener.command.Receive;
import xinyongbang.listener.response.BaseResponse;

import javax.websocket.Session;
import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengyi on 2016/1/14.
 */
public class XXRunnable implements Runnable {

    public static int PORT = 8585;

    private static byte[] buffer = new byte[1024 * 1024];

    public static DatagramSocket serviceSocket = null;

    private DatagramPacket packet = null;

    private InetSocketAddress socketAddress = null;

    private String orgIp;

    public static Map<String, SocketAddress> user_ip;

    public void run() {
        try {
            user_ip = new HashMap<String, SocketAddress>();
            WebSocket.sessionMap = new HashMap<String, Session>();
            UdpServerSocket();
            while (true) {
                receive();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造函数，绑定主机和端口.
     *
     * @throws Exception
     */
    public void UdpServerSocket() {
        try {
            socketAddress = new InetSocketAddress(PORT);
            serviceSocket = new DatagramSocket(socketAddress);
            System.out.println("服务端启动!启动端口为:" + PORT);
        } catch (Exception e) {
            System.out.println("端口占用:尝试更换端口");
            PORT++;
            UdpServerSocket();
        }
    }

    public final String getOrgIp() {
        return orgIp;
    }

    /**
     * 设置超时时间，该方法必须在bind方法之后使用.
     *
     * @param timeout 超时时间
     * @throws Exception
     */
    public final void setSoTimeout(int timeout) throws Exception {
        serviceSocket.setSoTimeout(timeout);
    }

    /**
     * 获得超时时间.
     *
     * @return 返回超时时间.
     * @throws Exception
     */
    public final int getSoTimeout() throws Exception {
        return serviceSocket.getSoTimeout();
    }

    /**
     * 绑定监听地址和端口.
     *
     * @param host 主机IP
     * @param port 端口
     * @throws SocketException
     */
    public final void bind(String host, int port) throws SocketException {
        socketAddress = new InetSocketAddress(host, port);
        serviceSocket = new DatagramSocket(socketAddress);
    }


    /**
     * 接收数据包，该方法会造成线程阻塞.
     *
     * @return 返回接收的数据串信息
     * @throws IOException
     */
    public final String receive() throws IOException {
        packet = new DatagramPacket(buffer, buffer.length);
        serviceSocket.receive(packet);
        orgIp = packet.getAddress().getHostAddress();
        String info = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
        System.out.println("接收信息：" + info);

        long startTime = System.currentTimeMillis();

        Receive receive = JSON.parseObject(info, Receive.class);
        if (null != receive) {
            String content = receive.getContent();
            switch (receive.getType()) {
                // 保持连接
                case 1:
                    if (user_ip.containsKey(content)) {
                        user_ip.replace(content, packet.getSocketAddress());
                    } else {
                        user_ip.put(content, packet.getSocketAddress());
                    }
                    break;
                // 断开连接
                case 2:
                    if (user_ip.containsKey(content)) {
                        user_ip.remove(content);
                    }
                    break;
                // 发送消息
                case 3:
//                    SendChat sendChat = JSON.parseObject(content, SendChat.class);
//
//                    chatAppService.send(sendChat);
//
//                    BaseResponse response_3 = new BaseResponse(3, System.currentTimeMillis() - startTime, content);
//
//                    if (user_ip.containsKey(sendChat.getToUserId())) {
//                        DatagramPacket send = new DatagramPacket(buffer, buffer.length);
//                        send.setSocketAddress(user_ip.get(sendChat.getToUserId()));
//                        send.setData(JSON.toJSONString(response_3).getBytes(Charset.forName("UTF-8")));
//                        serviceSocket.send(send);
//                    }
//                    if (WebSocket.sessionMap.containsKey(sendChat.getToUserId())) {
//                        WebSocket.sessionMap.get(sendChat.getToUserId()).getBasicRemote().sendText(JSON.toJSONString(response_3));
//                    }

                    break;

                // 发送好友请求
                case 4:
//                    AddFriend addFriend = JSON.parseObject(content, AddFriend.class);
//
//                    BaseResponse response_4 = new BaseResponse(4, System.currentTimeMillis() - startTime, content);
//
//                    try {
//                        friendAppService.addFriend(addFriend);
//
////                        DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
////                        add_return.setSocketAddress(user_ip.get(addFriend.getUserId()));
////                        add_return.setData((4 + addFriend.getUserId()).getBytes(Charset.forName("UTF-8")));
////                        serviceSocket.send(add_return);
//
//                        if (user_ip.containsKey(addFriend.getToUserId())) {
//                            DatagramPacket add = new DatagramPacket(buffer, buffer.length);
//                            add.setSocketAddress(user_ip.get(addFriend.getToUserId()));
//                            add.setData(JSON.toJSONString(response_4).getBytes(Charset.forName("UTF-8")));
//                            serviceSocket.send(add);
//                        }
//                        if (WebSocket.sessionMap.containsKey(addFriend.getToUserId())) {
//                            WebSocket.sessionMap.get(addFriend.getToUserId()).getBasicRemote().sendText(JSON.toJSONString(response_4));
//                        }
//
//                    } catch (SocketException e) {
//
//                        DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
//                        add_return.setSocketAddress(user_ip.get(addFriend.getUserId()));
//                        add_return.setData(JSON.toJSONString(response_4).getBytes(Charset.forName("UTF-8")));
//                        serviceSocket.send(add_return);
//                    }

                    break;

                // 回复好友请求
                case 5:
//                    ReceiveAddFriend receiveAddFriend = JSON.parseObject(content, ReceiveAddFriend.class);
//
//                    BaseResponse response_5 = new BaseResponse(5, System.currentTimeMillis() - startTime, content);
//
//                    FriendRepresentation friend = null;
//                    try {
//                        friendAppService.receive(receiveAddFriend);
//
//                        friend = friendAppService.searchByID(receiveAddFriend.getFriendId());

//                        DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
//                        add_return.setSocketAddress(user_ip.get(receiveAddFriend.getUserId()));
//                        add_return.setData((5 + receiveAddFriend.getUserId()).getBytes(Charset.forName("UTF-8")));
//                        serviceSocket.send(add_return);

//                        if (user_ip.containsKey(friend.getUser().getId())) {
//                            DatagramPacket receive_add = new DatagramPacket(buffer, buffer.length);
//                            receive_add.setSocketAddress(user_ip.get(friend.getUser().getId()));
//                            receive_add.setData(JSON.toJSONString(response_5).getBytes(Charset.forName("UTF-8")));
//                            serviceSocket.send(receive_add);
//                        }
//
//                        if (WebSocket.sessionMap.containsKey(friend.getUser().getId())) {
//                            WebSocket.sessionMap.get(friend.getUser().getId()).getBasicRemote().sendText(JSON.toJSONString(response_5));
//                        }

//                    } catch (SocketException e) {
//                        DatagramPacket add_return = new DatagramPacket(buffer, buffer.length);
//                        add_return.setSocketAddress(user_ip.get(friend.getUser().getId()));
//                        add_return.setData(JSON.toJSONString(response_5).getBytes(Charset.forName("UTF-8")));
//                        serviceSocket.send(add_return);
//                    }
                    break;
            }
        }
        return info;
    }

    /**
     * 推送数据
     *
     * @param userName
     * @param push
     */
    public static void send(String userName, Push push) {
        DatagramPacket send = new DatagramPacket(buffer, buffer.length);
        send.setSocketAddress(XXRunnable.user_ip.get(userName));
        send.setData(JSON.toJSONString(push).getBytes(Charset.forName("UTF-8")));
        try {
            System.out.println("推送消息,账号:" + userName + "内容：" + JSON.toJSONString(push));
            XXRunnable.serviceSocket.send(send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将响应包发送给请求端.
     *
     * @param info 回应报文
     * @throws IOException
     */
    public final void response(String info) throws IOException {
        System.out.println("客户端地址 : " + packet.getAddress().getHostAddress()
                + ",端口：" + packet.getPort());
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet
                .getAddress(), packet.getPort());
        dp.setData(info.getBytes());
        serviceSocket.send(dp);
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    /**
     * 设置报文的缓冲长度.
     *
     * @param bufsize 缓冲长度
     */
    public final void setLength(int bufsize) {
        packet.setLength(bufsize);
    }

    /**
     * 获得发送回应的IP地址.
     *
     * @return 返回回应的IP地址
     */
    public final InetAddress getResponseAddress() {
        return packet.getAddress();
    }

    /**
     * 获得回应的主机的端口.
     *
     * @return 返回回应的主机的端口.
     */
    public final int getResponsePort() {
        return packet.getPort();
    }

    /**
     * 关闭udp监听口.
     */
    public final void close() {
        try {
            serviceSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
