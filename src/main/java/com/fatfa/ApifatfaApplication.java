package com.fatfa;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fatfa.model.service.IFilesStorageService;

@SpringBootApplication
public class ApifatfaApplication {

	@Resource
	IFilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ApifatfaApplication.class, args);

		// System.err.println();

	}

	public void run(String... arg) throws Exception {
//	    storageService.deleteAll();
		storageService.init();
	}

}
