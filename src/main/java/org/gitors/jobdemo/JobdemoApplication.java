package org.gitors.jobdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JobdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(JobdemoApplication.class, args);
    }
}
