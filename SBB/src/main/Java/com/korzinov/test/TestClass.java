package com.korzinov.test;

import com.korzinov.bo.UserBo;
import com.korzinov.entities.UserEntity;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestClass {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring-config.xml");
        ctx.refresh();
        UserBo userBo = ctx.getBean("UserBo",UserBo.class);
        UserEntity user = new UserEntity();

        user.setFirstName("sjkbfsd");
        user.setLastName("ffsdgdsfg");
        user.setUserName("fgsdfgfds");
        user.setPassword("dsfgdfg");
        user.setEmail("sfgsd");
//        user.setUserId(3);
        user.setEnabled((byte)1);

        userBo.createUser(user);
    }
}
