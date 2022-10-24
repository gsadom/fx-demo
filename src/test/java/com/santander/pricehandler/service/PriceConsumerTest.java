package com.santander.pricehandler.service;

import com.santander.pricehandler.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PriceConsumerTest {

    @Test
    void priceListener() {
    }

    @Test
    void addMarginForBid() {
        double bidPriceWithMargin = PriceConsumer.addMargin(10d, true);
        assertEquals(9.99d,bidPriceWithMargin,0.01d);
    }

    @Test
    void addMarginForAsk() {
        double bidPriceWithMargin = PriceConsumer.addMargin(22.25d, true);
        assertEquals(22.22775d,bidPriceWithMargin,0.01d);
    }
}