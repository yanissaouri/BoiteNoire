package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Generator")
public class DataGenerator implements CommandLineRunner {

   @Override
    public void run(String... args) {
       System.out.println("test");
       
   }
}
