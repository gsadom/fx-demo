package com.santander.pricehandler.util;

import com.santander.pricehandler.model.Price;
import com.santander.pricehandler.service.PriceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;


public final class FileReader {
    public static Logger logger = LoggerFactory.getLogger(FileReader.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
    //consume CSV and feed service
    public static void readAndPublishData(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            logger.error("File with data doesn't exist");
            throw new RuntimeException(e);
        }


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            int counter = 0;
            Price price = new Price();

            //no error handling on purpose, this is not essence of task - assuming proper data
            while (st.hasMoreTokens()){
                String current = st.nextToken();
                switch (counter) {
                    case 0:
                        price.setId(Integer.parseInt(current));
                        break;
                    case 1:
                        price.setTicker(current);
                        break;
                    case 2:
                        price.setBid(Double.parseDouble(current));
                        break;
                    case 3:
                        price.setAsk(Double.parseDouble(current));
                        break;
                    case 4:
                        price.setTimestamp(LocalDateTime.parse(current, formatter));
                        break;
                    default : logger.error("Unexpected data found");
                }
                counter++;
            }
            PriceConsumer.priceListener(price);


        }
        scanner.close();

    }
}
