package udp;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;
import xinyongbang.listener.command.Receive;

import java.io.IOException;
import java.net.*;

/**
 * Created by YJH on 2016/4/22.
 */
public class UdpTest {

    @Test
    public void testClientReceive() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket ds = null;
                byte[] bytes = new byte[1024 * 1024];
                DatagramPacket rec = new DatagramPacket(bytes, bytes.length);
                try {
                    ds = new DatagramSocket(9999);
                    while (true) {
                        ds.receive(rec);
                        String msg = new String(rec.getData(), rec.getOffset(), rec.getLength());
                        System.out.println(msg);
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    @Test
    public void testClient() throws IOException {
        DatagramSocket ds = ds = new DatagramSocket(9999);  //实例化套间字，指定自己的port

        Receive receive = new Receive();
        receive.setType(1);
        receive.setContent("15823634833");

        byte[] buf = JSONObject.toJSONString(receive).getBytes();  //数据
        InetAddress destination = destination = InetAddress.getByName("192.168.1.168");  //需要发送的地址
        DatagramPacket dp = new DatagramPacket(buf, buf.length, destination, 8585);
        //打包到DatagramPacket类型中（DatagramSocket的send()方法接受此类，注意10000是接受地址的端口，不同于自己的端口！）
        ds.send(dp);  //发送数据
        ds.close();
    }

}
