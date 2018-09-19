package org.chenmin.open.screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenshotApplication {

    public static void main(String[] args) {
        WebDriverManager.phantomjs().useMirror().setup();
        SpringApplication.run(ScreenshotApplication.class, args);
    }
}
