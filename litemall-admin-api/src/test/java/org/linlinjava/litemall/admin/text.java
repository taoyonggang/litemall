package org.linlinjava.litemall.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.admin.config.QrcodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class text {
    @Autowired
    private QrcodeProperties myProps;


    @Test
    public void propsTest() {
       String url =  myProps.getImgageUrl();
    System.out.println("simpleProp: " + myProps.getImgageUrl());
    }
}
