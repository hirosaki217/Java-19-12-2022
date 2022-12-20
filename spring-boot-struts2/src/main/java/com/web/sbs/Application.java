package com.web.sbs;

import com.web.sbs.model.User;
import com.web.sbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String...args) throws Exception {
//        userRepository.addUser(new User("hiros2@gmail.com", "hieu", "ADMIN",true , "12345"));
        System.out.println(userRepository.findAll().size());
    }
}
