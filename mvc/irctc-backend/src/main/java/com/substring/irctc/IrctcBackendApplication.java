package com.substring.irctc;

import com.substring.irctc.entity.Role;
import com.substring.irctc.repositories.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class IrctcBackendApplication  implements CommandLineRunner {


	private static final Logger logger= LoggerFactory.getLogger(IrctcBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IrctcBackendApplication.class, args);
	}


	@Autowired
	private RoleRepo roleRepo;


	@Override
	public void run(String... args) throws Exception {


		logger.trace("Initializing roles...");
		logger.debug("Initializing roles...");
		logger.info("Initializing roles...");
		logger.warn("Initializing roles...");
		logger.error("Initializing roles...");



		if (!roleRepo.existsByName("ROLE_ADMIN"))
		{
			Role adminRole = new Role();
			adminRole.setId(UUID.randomUUID().toString());
			adminRole.setName("ROLE_ADMIN");
			roleRepo.save(adminRole);
		}




		if (!roleRepo.existsByName("ROLE_NORMAL")) {
			Role userRole = new Role();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setName("ROLE_NORMAL");
			roleRepo.save(userRole);
		}

		System.out.println("Roles initialized successfully.");


	}
}
