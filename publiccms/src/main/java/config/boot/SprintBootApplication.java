package config.boot;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import config.initializer.AdminInitializer;
import config.initializer.ApiInitializer;
import config.initializer.InitializationInitializer;
import config.initializer.ResourceInitializer;
import config.initializer.WebInitializer;
import config.spring.CmsConfig;

/**
 *
 * SprintBootApplication
 * 
 */
@Configuration
@Import(CmsConfig.class)
public class SprintBootApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	System.out.println("main方法"); 
        SpringApplication.run(SprintBootApplication.class, args);
    }

    /**
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8080);// 设置端口
        factory.setDisplayName("PublicCMS");// 设置显示名称
        factory.setSessionTimeout(20, TimeUnit.MINUTES);// 设置session超时时间
        System.out.println("servletContainer方法"); 
        return factory;
    }

    /**
     * @return
     */
    @Bean
    public ServletContextInitializer webInitializer() {
    	 System.out.println("webInitializer方法"); 
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new WebInitializer().onStartup(servletContext);
            }

        };
    }

    /**
     * @return
     */
    @Bean
    public ServletContextInitializer adminInitializer() {
    	System.out.println("adminInitializer方法"); 
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new AdminInitializer(true).onStartup(servletContext);
            }
        };
    }

    /**
     * @return
     */
    @Bean
    public ServletContextInitializer apiInitializer() {
    	System.out.println("apiInitializer方法");
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new ApiInitializer().onStartup(servletContext);
            }
        };
    }

    /**
     * @return
     */
    @Bean
    public ServletContextInitializer installationInitializer() {
    	System.out.println("installationInitializer方法");
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new InitializationInitializer().onStartup(servletContext);
            }
        };
    }

    /**
     * @return
     */
    @Bean
    public ServletContextInitializer resourceInitializer() {
    	System.out.println("resourceInitializer方法");
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new ResourceInitializer().onStartup(servletContext);
            }
        };
    }
}