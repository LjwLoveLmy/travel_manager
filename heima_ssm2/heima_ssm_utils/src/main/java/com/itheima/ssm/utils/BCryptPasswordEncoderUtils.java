package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户密码加密测试
 */
public class BCryptPasswordEncoderUtils {
    //声明一个静态的变量，这样就可以直接调用这个变量，静态方法也能直接调用
private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){

        //可以直接使用BCryptPasswordEncoder这个类进行密码加密的实现
       return bCryptPasswordEncoder.encode(password);

    }

    public static void main(String[] args) {
        String password="123456";
        String pwd = encodePassword(password);
        System.out.println(pwd);
        //$2a$10$3bB/iUh8UliFiMygEFWNx.NI/SeypBzW7TqKcKCBuxCZTPV/hXDy2
        //$2a$10$p9m2ilt4QeeJ9iroGwJg6eVfwlpCnVvtve2G4xnbbUmmBY8psZ2Ay
        //这里可以发现两次经过加密后的密码都不相同，这样可以提高安全性
    }
}
