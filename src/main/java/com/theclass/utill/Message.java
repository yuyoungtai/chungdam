package com.theclass.utill;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Message {
    //문자 발송
    public String sendSMS(String receiver, String msg) throws Exception {
        final String encodingType = "utf-8";
        final String boundary = "____boundary____";
        String sms_url = "https://apis.aligo.in/send/";
        Map<String, String> sms = new HashMap<String, String>();

        sms.put("user_id", "openwedding"); // SMS 아이디
        sms.put("key", "qx4hrrwwhm25v5mu25j9up2l2t3xxkmu"); // 인증키

        sms.put("msg", msg); // 메세지 내용
        sms.put("receiver", receiver); // 수신번호
        sms.put("sender", "16611623"); // 발신번호
        sms.put("testmode_yn", "N"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.setBoundary(boundary);
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Charset.forName(encodingType));

        for (Iterator<String> i = sms.keySet().iterator(); i.hasNext();) {
            String key = i.next();
            builder.addTextBody(key, sms.get(key), ContentType.create("Multipart/related", encodingType));
        }

        HttpEntity entity = builder.build();

        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(sms_url);
        post.setEntity(entity);

        HttpResponse res = client.execute(post);

        String result = "";
        if (res != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
            String buffer = null;
            while ((buffer = in.readLine()) != null) {
                result += buffer;
            }
            in.close();
            System.out.println(result);
        }
        return result;
    }
}
