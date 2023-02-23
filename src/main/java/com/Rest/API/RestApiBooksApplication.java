package com.Rest.API;

import com.Rest.API.Entitiy.User;
import com.Rest.API.dao.BookRepository;
import com.Rest.API.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RestApiBooksApplication implements CommandLineRunner {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(RestApiBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1=new User();
		user1.setUserName("monu");
		user1.setPassword(bCryptPasswordEncoder.encode("monu"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);

		User user2=new User();
		user2.setUserName("rahul");
		user2.setPassword(bCryptPasswordEncoder.encode("rahul"));
		user2.setRole("ROLE_NORMAL");
		this.userRepository.save(user2);
	}
}



