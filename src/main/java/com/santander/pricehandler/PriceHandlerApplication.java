package com.santander.pricehandler;

import com.santander.pricehandler.util.FileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceHandlerApplication.class, args);
		FileReader.readAndPublishData("./src/main/resources/testData.csv");
	}

}
