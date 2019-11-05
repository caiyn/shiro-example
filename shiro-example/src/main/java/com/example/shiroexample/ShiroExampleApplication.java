package com.example.shiroexample;

import com.example.shiroexample.pojo.po.UserPO;
import com.example.shiroexample.utils.CSVUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootApplication
public class ShiroExampleApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ShiroExampleApplication.class, args);
        File file = new File("D:\\data\\user.csv");
        try {
            List<UserPO> userPOList = CSVUtil.csv2bean(new InputStreamReader(new
                    FileInputStream(file)),UserPO.class,false,',');
            System.out.println(userPOList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
