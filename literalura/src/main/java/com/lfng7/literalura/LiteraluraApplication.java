package com.lfng7.literalura;

import com.lfng7.literalura.ui.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication {
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
		Menu menu = new Menu();
        try {
            menu.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
