package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager {

    // chromedriver ��θ� ��Ȯ�� ����!
    private static final String DRIVER_PATH = "C:\\drivers\\chromedriver.exe";

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        return new ChromeDriver();
    }
}