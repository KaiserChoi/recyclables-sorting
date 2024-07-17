package hkmu.alin3.greenstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScans(
        @ComponentScan(basePackages = {
                "hkmu.alin3.greenstar.service",
                "hkmu.alin3.greenstar.dao",
                "hkmu.alin3.greenstar.model",
                "hkmu.alin3.greenstar.controller"}))
@Configuration

public class GreenStarApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreenStarApplication.class, args);
    }
}
