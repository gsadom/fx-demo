package com.santander.pricehandler.service;

import com.santander.pricehandler.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PriceConsumer {

    public static Logger logger = LoggerFactory.getLogger(PriceConsumer.class);
    private static final double MARGIN = 0.1d;
    //used static method for simplicity - no JMS, REST or PUB/SUB implementation
    public static void priceListener(Price price){
        logger.info("Received new message with ID: " + price.getId());
        price.setBid(addMargin(price.getBid(), true));
        price.setAsk(addMargin(price.getAsk(), false));
        PriceWithMarginProducerService.publishPriceWithMargin(price);
    }

    public static double addMargin(double price, boolean isBid){
        return isBid?price-price*MARGIN/100:price+price*MARGIN/100;
    }

}
