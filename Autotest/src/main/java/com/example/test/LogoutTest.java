
package com.example.test;

import com.example.test.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = ChromeDriverManager.getDriver();

        try {
            // 로그인 페이지 접속
            driver.get("https://the-internet.herokuapp.com/login");
            Thread.sleep(1000);

            // 로그인
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            Thread.sleep(1000);
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(1000);

            // 로그아웃 버튼 클릭 (로그인 성공 시에만 보임)
            driver.findElement(By.cssSelector("a[href='/logout']")).click();
            Thread.sleep(1000);

            // 로그아웃 메시지 확인
            String msg = driver.findElement(By.id("flash")).getText();
            System.out.println("Logout result: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // 필요시 주석 해제
        }
    }
}