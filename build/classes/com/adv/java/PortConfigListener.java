package com.adv.java;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.catalina.startup.Tomcat;

public class PortConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String port = System.getenv("PORT");
        if (port == null) port = "8080"; // fallback for local dev

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.parseInt(port));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing needed here
    }
}
