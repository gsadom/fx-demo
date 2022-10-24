package com.santander.pricehandler.service;

import com.santander.pricehandler.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PriceWithMarginProducerService {

    private static final WebClient apiClient = WebClient.create("http://localhost:8080");
    public static Logger logger = LoggerFactory.getLogger(PriceConsumer.class);

    //no error handling logic for POST, web server with listener not implemented anywhere
    public static void publishPriceWithMargin(Price price) {
        apiClient
                .post()
                .uri("/marginPrices")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(price),Price.class);
        logger.info("Successfully published price with margin for ID: " + price.getId());
    }
}
