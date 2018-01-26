package com.lamdevops.context.loadcontainer;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestLoadContext {

    /**
     * Load Beans.xml from absolutePath
     */
    @Test
    public void fileSystemXmlApplicationContext() {
        String beansFile = "C:\\Users\\lam.nm\\Documents\\spring-core\\src\\main\\java\\com\\lamdevops\\context\\loadcontainer\\Beans.xml";
       ApplicationContext context = new FileSystemXmlApplicationContext(beansFile);

        HelloWorld hello = (HelloWorld) context.getBean("helloWorld");
        System.out.println(hello.getMessage());
    }

    /**
     * Load Beans.xml from sources
     */
    @Test
    public void classXmlApplicationContext() {

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        HelloWorld hello = (HelloWorld) context.getBean("helloWorld");
        System.out.println(hello.getMessage());
    }
}
