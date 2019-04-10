package demo.sdk.utils;

import java.util.Random;

/**
 * 随机验证码
 * Created by :Guozhihua
 * Date： 2017/6/23.
 */
public class AuthCodeUtil {

    public  static  String generateAuthCode(int size) {
        String authCode = "";
        Random random = new Random();
        for(int i = 0 ; i < size; i++){
            authCode += random.nextInt(9);
        }
        return authCode;
    }
}
