package com.example.demo.Generators;

import com.example.demo.Constants.Const;
import com.example.demo.Entity.Users;
import com.example.demo.Enum.UsersCategory;
import com.example.demo.Repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Component
@Profile("generator")
public class DataGenerator implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final EventGenerator eventGenerator;
    private final Random random = new Random(67);

    public DataGenerator(UsersRepository usersRepository, EventGenerator eventGenerator){
        this.usersRepository = usersRepository;
        this.eventGenerator = eventGenerator;
    }

    private String randomRegion() {
        String[] regions = {"marseille", "lyon", "paris", "toulouse", "nantes"};
        return regions[random.nextInt(regions.length)];
    }

    private Instant randomPastDate() {
        long now = Instant.now().toEpochMilli();
        long yearAgo = now - 365L * 24 * 60 * 60 * 1000;
        return Instant.ofEpochMilli(yearAgo + (long) (random.nextDouble() * (now - yearAgo)));
    }


   @Override
    public void run(String... args) {
       List<Users> users = new ArrayList<>();

       for (int i = 0; i < Const.getTotalUser(); i++){
           double roll = random.nextDouble();
           UsersCategory category;

           if (roll < Const.getHEAVY()){
               category = UsersCategory.HEAVY;

           } else if (roll < Const.getMEDIUM()) {
               category = UsersCategory.MEDIUM;
           }
           else {
               category = UsersCategory.LIGHT;
           }

           Users user = new Users();
           user.setName("user" + i);
           user.setEmail("user" + i + "@test.com");
           user.setRegion(randomRegion());
           user.setCreatedAt(randomPastDate());
           user.setUsersCategory(category);
           users.add(user);
       }

       usersRepository.saveAll(users);
       System.out.println(users.size() + " utilisateurs générés.");

       eventGenerator.generate(users);
       
   }
}
