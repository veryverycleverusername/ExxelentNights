package org.hotelman;

import org.hotelman.setup.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelmanApp {
    public static void main(String[] args) {
        SpringApplication.run(HotelmanApp.class, args);
        //DataInitializer init = new DataInitializer();
    }
}
