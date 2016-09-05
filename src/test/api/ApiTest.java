package api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.group.command.NewGroupCommand;
import xinyongbang.core.api.ApiConstant;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.common.CharsetConstant;
import xinyongbang.core.enums.ChatType;
import xinyongbang.core.util.CoreHttpUtils;
import xinyongbang.core.util.CoreStringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/17 0017.
 */
public class ApiTest {

    @Test
    public void apiTest() {
        String url = "http://192.168.2.101:8899/app/api/login";
        List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
        parameterList.add(new BasicNameValuePair("key", "xin-yong-bang-admin"));
        parameterList.add(new BasicNameValuePair("secret", "xin-yong-bang-admin"));

        LoginCommand command = new LoginCommand();
        command.setUserName("15823634844");
        command.setPassword("123123");
        String json = JSON.toJSONString(command);
        parameterList.add(new BasicNameValuePair("json", json));

        RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                .setConnectionRequestTimeout(20000)
                .setConnectTimeout(20000)
                .build();

        String result = null;
        try {
            result = CoreHttpUtils.post(url,
                    CharsetConstant.UTF8_CHARSET,
                    null,
                    requestConfig,
                    null,
                    null,
                    parameterList.toArray(new NameValuePair[parameterList.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (CoreStringUtils.isEmpty(result)) {
            System.out.println("响应结果为空, 非法");
            return;
        }

        result = CoreStringUtils.unicodeToString(result);

        System.out.println(result);

        ApiResponse response = JSON.parseObject(result, ApiResponse.class);

    }

    @Test
    public void sendChat() {
        String url = "http://192.168.1.168:8899/app/api/chat/new";
        List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
        parameterList.add(new BasicNameValuePair("key", "xin-yong-bang-admin"));
        parameterList.add(new BasicNameValuePair("secret", "xin-yong-bang-admin"));

        NewChatCommand command = new NewChatCommand();
        command.setReceiveUser("15823634833");
        command.setChatType(ChatType.TEXT);
        command.setContent("你来啊哈哈哈撒旦撒旦！");
        String json = JSON.toJSONString(command);
        parameterList.add(new BasicNameValuePair("json", json));

        RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                .setConnectionRequestTimeout(20000)
                .setConnectTimeout(20000)
                .build();

        String result = null;
        try {
            result = CoreHttpUtils.post(url,
                    CharsetConstant.UTF8_CHARSET,
                    null,
                    requestConfig,
                    null,
                    null,
                    parameterList.toArray(new NameValuePair[parameterList.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (CoreStringUtils.isEmpty(result)) {
            System.out.println("响应结果为空, 非法");
            return;
        }

        result = CoreStringUtils.unicodeToString(result);

        System.out.println(result);

        ApiResponse response = JSON.parseObject(result, ApiResponse.class);
    }

    @Test
    public void tianlula() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String url = "http://127.0.0.1:8899/app/api/group/new";
                    List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
                    parameterList.add(new BasicNameValuePair("key", "xin-yong-bang-admin"));
                    parameterList.add(new BasicNameValuePair("secret", "xin-yong-bang-admin"));

                    NewGroupCommand command = new NewGroupCommand();
                    command.setGroupName("天噜啦啊啊");
                    String json = JSON.toJSONString(command);
                    parameterList.add(new BasicNameValuePair("json", json));

                    RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                            .setConnectionRequestTimeout(20000)
                            .setConnectTimeout(20000)
                            .build();

                    String result = null;
                    try {
                        result = CoreHttpUtils.post(url,
                                CharsetConstant.UTF8_CHARSET,
                                null,
                                requestConfig,
                                null,
                                null,
                                parameterList.toArray(new NameValuePair[parameterList.size()]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (CoreStringUtils.isEmpty(result)) {
                        System.out.println("响应结果为空, 非法");
                        return;
                    }

                    result = CoreStringUtils.unicodeToString(result);

                    System.out.println(result);

                    ApiResponse response = JSON.parseObject(result, ApiResponse.class);
                    System.out.println(response.getData());
                }
            }
        }).run();
    }

    @Test
    public void testMd5() {
        String s = "110456杨佳豪500241199704171236146373112965483cfe19cdda94d02896356c7865320bd";
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            System.out.println(new String(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void urlConnection() {
        String url = "http://211.149.154.85:8110/app/api/login";
        String pa = "{userName:'15823634844',password:'123123'}";
        ApiResponse result = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            // 不需要cookie可以删掉
//            conn.setRequestProperty("Cookie", ProjectSetting.setting.getString("sCookie", ""));

            // Send data
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            // pa为请求的参数
            pw.print("key=xin-yong-bang-admin&secret=xin-yong-bang-admin&json=" + pa);
            pw.flush();
            pw.close();

            // Get the response!
            int httpResponseCode = conn.getResponseCode();
            if (httpResponseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP response code: " + httpResponseCode +
                        "\nurl:" + url);
            }

            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String response;
            String readLine;
            while (null != (readLine = br.readLine())) {
                builder.append(readLine);
            }
            inputStream.close();
            response = builder.toString();
            // 如果为json可以这样处理
//            if (!response.isEmpty()) {
//                result = JSON.parseObject(response, ApiResponse.class);
//                if (result.noLogin()) {
//                    ProjectSetting.cache.getBoolean("logined", false);
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    context.startActivity(intent);
//                    return null;
//                }
//            } else {
//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "连接网络失败，请检查网络", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
            //保存cookie
            Map<String, List<String>> map = conn.getHeaderFields();
            List<String> cookies = map.get("set-cookie");
            if (null != cookies && 0 < cookies.size()) {
                String s = "";
                for (String cookie : cookies) {
                    if (s.isEmpty()) {
                        s = cookie;
                    } else {
                        s += ";" + cookie;
                    }
                }
                if (s.length() > 0) {
                    System.out.println(s);
                }
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDownload() {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.2.101:8899/download/0000000054c345530154c708584b0000";

                    RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                            .setConnectionRequestTimeout(20000)
                            .setConnectTimeout(20000)
                            .build();

                    String result = null;
                    Map<String, String> headMap = new HashMap<String, String>();
                    headMap.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
                    try {
                        result = CoreHttpUtils.post(url,
                                CharsetConstant.UTF8_CHARSET,
                                null,
                                requestConfig,
                                headMap,
                                null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (CoreStringUtils.isEmpty(result)) {
                        System.out.println("线程" + Thread.currentThread().getName() + "响应结果为空");
                    }
                }
            }).run();
        }
    }

    @Test
    public void testDownload1() {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.2.101:8899/download/0000000054c345530154c708584b0000";

                    RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                            .setConnectionRequestTimeout(20000)
                            .setConnectTimeout(20000)
                            .build();

                    String result = null;
                    Map<String, String> headMap = new HashMap<String, String>();
                    headMap.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
                    try {
                        result = CoreHttpUtils.post(url,
                                CharsetConstant.UTF8_CHARSET,
                                null,
                                requestConfig,
                                headMap,
                                null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (CoreStringUtils.isEmpty(result)) {
                        System.out.println("线程" + Thread.currentThread().getName() + "响应结果为空");
                    }
                }
            }).run();
        }
    }
}
