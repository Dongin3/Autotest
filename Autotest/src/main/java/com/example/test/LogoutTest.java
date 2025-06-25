
package com.example.test;

import com.example.test.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = ChromeDriverManager.getDriver();

        try {
            // �α��� ������ ����
            driver.get("https://the-internet.herokuapp.com/login");
            Thread.sleep(1000);

            // �α���
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            Thread.sleep(1000);
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(1000);

            // �α׾ƿ� ��ư Ŭ�� (�α��� ���� �ÿ��� ����)
            driver.findElement(By.cssSelector("a[href='/logout']")).click();
            Thread.sleep(1000);

            // �α׾ƿ� �޽��� Ȯ��
            String msg = driver.findElement(By.id("flash")).getText();
            System.out.println("Logout result: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // �ʿ�� �ּ� ����
        }
    }
}