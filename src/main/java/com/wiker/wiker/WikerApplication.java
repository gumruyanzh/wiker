package com.wiker.wiker;

import com.wiker.wiker.service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class WikerApplication implements CommandLineRunner{

	@Autowired
	private ScrapperService scrapperService;


	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(WikerApplication.class);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {

		if (args.length > 0) {
			scrapperService.scrapData(args[0]);
		} else {
			scrapperService.scrapData("https://en.wikipedia.org/wiki/YouTube");
		}

		exit(0);
	}
}
