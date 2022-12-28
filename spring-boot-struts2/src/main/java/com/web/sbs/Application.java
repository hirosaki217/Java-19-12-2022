package com.web.sbs;

import com.google.gson.Gson;
import com.web.sbs.model.User;
import com.web.sbs.repository.UserRepository;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
@MappedTypes(User.class)
@MapperScan("com.web.sbs.repository")
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String...args) throws Exception {
//        userRepository.addUser(new User("hiros13@gmail.com", "hieu", "ADMIN",true , "12345"));
        System.out.println(userRepository.findAll().size());
    }

    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
