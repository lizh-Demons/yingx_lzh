package com.baizhi;


import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoEasyTest {

    @Test
    void test() {
//        goEasy.publish({
//                channel: "my_channel", //替换为您自己的channel
//                message: "Hello, GoEasy!" //替换为您想要发送的消息内容
//});
//        GoEasy goEasy = new GoEasy();
        // 参数：服务器地址    ,  AppKey:commonKey
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-3fd7fdb3827b46eb92d10e879932cbfe");

        //参数：管道标识，发送内容
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
