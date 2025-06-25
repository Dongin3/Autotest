package com.example.test;

import com.example.test.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = ChromeDriverManager.getDriver();

        try {
            driver.get("https://the-internet.herokuapp.com/login");
            Thread.sleep(1000); // 1�� ���

            driver.findElement(By.id("username")).sendKeys("tomsmith");
            Thread.sleep(1000); // 1�� ���

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            Thread.sleep(1000); // 1�� ���

            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(1000); // 1�� ���

            String msg = driver.findElement(By.id("flash")).getText();
            Thread.sleep(1000); // 1�� ���

            System.out.println("Login result: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // �׽�Ʈ�� ������ ũ�� â�� �������� ����
        }
    }
}