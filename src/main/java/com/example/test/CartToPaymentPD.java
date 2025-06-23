
package com.example.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartToPaymentPD {

    public static void main(String[] args) {

        // 드라이버 경로 설정 필요시 추가 (예: System.setProperty)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
           
            // 2. 상품 상세 페이지 이동 
            driver.get("https://www.samsung.com/uk/lifestyle-tvs/the-terrace/lst7d-the-terrace-55-inch-neo-qled-4k-outdoor-smart-tv-black-qe55lst7datxxu/");

            Thread.sleep(1000); // 페이지 로딩 대기
            
         // trustarc 허용
            WebElement TrustBtn = driver.findElement(By.cssSelector(".truste-button1"));
            TrustBtn.click();
            System.out.println("쿠키허용버튼 클릭");

         // 카트 페이지 이동 버튼 클릭
            WebElement movePDBtn = driver.findElement(By.cssSelector(".pd-buying-price__cta"));
            movePDBtn.click();
            System.out.println("카트 페이지로 이동 버튼 클릭");
            
            Thread.sleep(3000); // 페이지 로딩 대기


         // 강제 스크롤 내리기 (불필요하다면 제거 가능, 남겨도 무방)
            ((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'scroll';");
            Thread.sleep(1000);

            // 버튼 찾기
            WebElement CheckOutBtn = driver.findElement(By.cssSelector(".order-summary__btn"));

            // 버튼을 화면 중앙에 오도록 스크롤 고정
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", CheckOutBtn
            );
            Thread.sleep(1000); // 스크롤 애니메이션 대기

            // 버튼 클릭
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", CheckOutBtn);
            System.out.println("Checkout 버튼 클릭 완료");
           
            // 이메일입력
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder='Enter your email address to checkout']")
                )
            );
            emailInput.sendKeys("test12@gmail.com");
            
            // "Guest checkout" 클릭
            WebElement guestCheckoutBtn = driver.findElement(
            	    By.xpath("//button[span[text()=' Guest checkout ']]")
            	);  
            guestCheckoutBtn.click();
            
            // Title > MR. 넣기
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement matSelect = wait2.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("mat-select.mat-mdc-select")
                )
            );
            matSelect.click();

         // "Mr" 옵션이 나타날 때까지 대기 후 클릭
         WebElement mrOption = wait2.until(
             ExpectedConditions.elementToBeClickable(
                 By.xpath("//mat-option[.//span[text()='Mr']]")
             )
         );
         mrOption.click();
         
         //이름 넣기
         WebElement firstNameInput = driver.findElement(By.name("firstName"));
         firstNameInput.sendKeys("Dongin");
         
         //성 넣기
         WebElement lastNameInput = driver.findElement(By.name("lastName"));
         lastNameInput.sendKeys("Choi");
         
         //휴대폰 정보 넣기
         WebElement phoneInput = driver.findElement(By.name("phone"));
         phoneInput.sendKeys("1234567788");
         
         //T&C 버튼 클릭
         WebElement PrivacyRadio = driver.findElement(By.cssSelector(".mdc-checkbox"));
         PrivacyRadio.click();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          //  driver.quit(); 크롬좀 끄지마라..
        }
    }
}
