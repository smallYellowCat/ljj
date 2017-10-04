package com.hzdz.ls.common;


import com.alibaba.fastjson.JSONObject;
import com.hzdz.ls.db.entity.Sign;
import com.hzdz.ls.db.entity.TicketJson;
import com.hzdz.ls.db.entity.TokenJson;
import com.hzdz.ls.db.entity.WxParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.Format;
import java.util.Formatter;
import java.util.UUID;


public class WxUtil {
        private static String APPID = "wx71d16fc0868f6dab";
        private static String APPSECRET = "9eb17ac0b44777f2c6671bf634c6bbcc";

        // 获取Sign
        public static Sign getSign(String url){
            try {
                long tokenTimeLong = Long.parseLong(WxParams.tokenTime);
                long tokenExpiresLong = Long.parseLong(WxParams.tokenExpires);
                long differ_token = (System.currentTimeMillis() - tokenTimeLong) / 1000;
                if (WxParams.token == null || differ_token > (tokenExpiresLong - 1800)){
                    TokenJson tokenJson = getAccess_token();
                    if (tokenJson != null){
                        WxParams.token = tokenJson.getAccess_token();
                        WxParams.tokenTime = System.currentTimeMillis()+"";
                        WxParams.tokenExpires = tokenJson.getExpires_in()+"";
                    }
                }
            }catch (Exception e){
                TokenJson tokenJson = getAccess_token();
                if (tokenJson != null){
                    WxParams.token = tokenJson.getAccess_token();
                    WxParams.tokenTime = System.currentTimeMillis()+"";
                    WxParams.tokenExpires = tokenJson.getExpires_in()+"";
                }
            }
            try {
                long ticketTimeLong = Long.parseLong(WxParams.ticketTime);
                long ticketExpiresLong = Long.parseLong(WxParams.ticketExpires);
                long differ_ticket = (System.currentTimeMillis() - ticketTimeLong) /1000;
                if (WxParams.ticket == null ||  differ_ticket > (ticketExpiresLong - 1800)) {
                    TicketJson ticketJson = getTicket(WxParams.token);
                    if (ticketJson != null) {
                        WxParams.ticket = ticketJson.getTicket();
                        WxParams.ticketTime = System.currentTimeMillis()+"";
                        WxParams.ticketExpires = ticketJson.getExpires_in()+"";
                    }
                }
            }catch (Exception e){
                TicketJson ticketJson = getTicket(WxParams.token);
                if (ticketJson != null) {
                    WxParams.ticket = ticketJson.getTicket();
                    WxParams.ticketTime = System.currentTimeMillis()+"";
                    WxParams.ticketExpires = ticketJson.getExpires_in()+"";
                }
            }

            Sign sign = signHandle(WxParams.ticket, url);

            return sign;
        }

        // 获取token
        private static TokenJson getAccess_token(){
            String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID, APPSECRET);
            try {
                String result = doGet(url);
                JSONObject rqJsonObject = JSONObject.parseObject(result);
                TokenJson token = (TokenJson) JSONObject.toJavaObject(rqJsonObject, TokenJson.class);
                return token;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        //获取ticket
        private static TicketJson getTicket(String token){
            String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",token);
            try {
                String result = doGet(url);
                JSONObject rqJsonObject = JSONObject.parseObject(result);
                TicketJson ticket = (TicketJson) JSONObject.toJavaObject(rqJsonObject, TicketJson.class);
                //JSONObject rqJsonObject = JSONObject.fromObject(result);
                //TicketJson ticket = (TicketJson) JSONObject.toBean(rqJsonObject,TicketJson.class);
                return ticket;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // 与微信服务器交互
        private static String doGet(String url) throws Exception{
            URL localUrl = new URL(url);
            URLConnection connection = localUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/text");

            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            if (httpURLConnection.getResponseCode() > 300){
                throw new Exception("连接微信服务器失败，错误代码："+httpURLConnection.getResponseCode());
            }
            try {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                while((tempLine = reader.readLine()) != null){
                    resultBuffer.append(tempLine);
                }
            } finally {
                if (reader != null){
                    reader.close();
                }
                if (inputStreamReader != null){
                    inputStreamReader.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }
            return resultBuffer.toString();
        }

        // 处理数据，拼装成Sign对象
        private static Sign signHandle(String jsapi_ticket, String url){
            Sign sign = new Sign();
            String noncestr = create_noncestr();
            String timestamp = create_timestamp();
            String signature = "";
            String string1 = "jsapi_ticket=" + jsapi_ticket
                            +"&noncestr=" + noncestr
                            +"&timestamp=" + timestamp
                            +"&url=" + url;
            try {
                MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                crypt.reset();
                crypt.update(string1.getBytes("UTF-8"));
                signature = byteToHex(crypt.digest());
            } catch (Exception e){
                e.printStackTrace();
            }

            sign.setAppid(APPID);
            sign.setTimestamp(timestamp);
            sign.setNonceStr(noncestr);
            sign.setJsapi_ticket(jsapi_ticket);
            sign.setSignature(signature);
            return sign;
        }

        private static String create_noncestr(){
            return UUID.randomUUID().toString();
        }

        private static String create_timestamp(){
            return Long.toString(System.currentTimeMillis() / 1000);
        }

        private static String byteToHex(final byte[] hash){
            Formatter formatter = new Formatter();
            for (byte b : hash){
                formatter.format("%02x", b);
            }
            String result = formatter.toString();
            formatter.close();
            return result;
        }
}
