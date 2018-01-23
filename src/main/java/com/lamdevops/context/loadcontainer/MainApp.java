package com.lamdevops.context.loadcontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        String beansFile = "C:\\Users\\lam.nm\\Documents\\spring-core\\src\\main\\java\\com\\lamdevops\\context\\loadcontainer\\Beans.xml";
        //ApplicationContext context = new FileSystemXmlApplicationContext(beansFile);
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        HelloWorld hello = (HelloWorld) context.getBean("helloWorld");
        System.out.println(hello.getMessage());
    }
}
