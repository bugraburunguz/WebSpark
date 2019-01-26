package main;// Code with ❤

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.setPort;

//┌───────────────────────────────┐
//│ Created by Buğra Emin BÜRÜNGÜZ│
//│ ───────────────────────────── │
//│ BugraBurunguz@AndroidEdu.IO   │
//│ ───────────────────────────── │
//│ 26.01.2019 - 04:32             │
//└────────────────────────────────
public class MainScreen {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(MainScreen.class);
        logger.info("Hi guys!!");
        setPort(8080);
    }
}
