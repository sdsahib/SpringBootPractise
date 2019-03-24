package com.fortinet.resturant;

import com.fortinet.resturant.Service.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SpringBootApplication
public class ResturantApplication implements CommandLineRunner {
	@Resource
    UploadService uploadService;

	public static void main(String[] args) {
		SpringApplication.run(ResturantApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
	}

}
