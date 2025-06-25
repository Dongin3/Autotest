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
    	System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
           
            // 2. 상품 상세 페이지 이동 
            driver.get("https://www.samsung.com/uk/lifestyle-tvs/the-terrace/lst7d-the-terrace-55-inch-neo-qled-4k-outdoor-smart-tv-black-qe55lst7datxxu/");

            WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(15)); // 페이지 로딩 대기
            
            wait0.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".truste-button1")));
            System.out.println("truste-button1 영역 로딩됨");

            
         // trustarc 허용
            WebElement TrustBtn = driver.findElement(By.cssSelector(".truste-button1"));
            TrustBtn.click();
            System.out.println("쿠키허용버튼 클릭");

         // 카트 페이지 이동 버튼 클릭
            WebElement movePDBtn = driver.findElement(By.cssSelector(".pd-buying-price__cta"));
            movePDBtn.click();
            System.out.println("카트 페이지로 이동 버튼 클릭");
            

         // Checkout 버튼 나타날 때까지 대기
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

         // Checkout 영역 기다리기 (예: div.order-summary 전체)
         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".order-summary")));
         System.out.println("order-summary 영역 로딩됨");

         // 1초 대기 후 버튼 찾기 시도
         Thread.sleep(1000);

         List<WebElement> btns = driver.findElements(By.cssSelector(".order-summary__btn"));
         System.out.println("Checkout 버튼 개수: " + btns.size());

         WebElement realBtn = null;
         for (WebElement btn : btns) {
             if (btn.isDisplayed()) {
                 realBtn = btn;
                 break;
             }
         }

         if (realBtn != null) {
             ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].scrollIntoView({block: 'center'});", realBtn);
             Thread.sleep(500);
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", realBtn);
             System.out.println("Checkout 클릭 완료");
         } else {
             System.out.println("보이는 Checkout 버튼을 찾지 못했습니다.");
         }
          
            // 이메일입력
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement emailInput = wait2.until(
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
            
            //  페이지가 이상하게 내려가기 때문에 강제로 다시 올려줌
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            
         // Title > MR. 넣기
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement matSelect = wait3.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("mat-select.mat-mdc-select"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", matSelect);
            Thread.sleep(300);
            matSelect.click();

         // "Mr" 옵션이 나타날 때까지 대기 후 클릭
         WebElement mrOption = wait3.until(
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
         
         //우편번호 입력 및 클릭
         WebElement addressInput = driver.findElement(
        		    By.cssSelector("input[placeholder='Enter your address or postcode']")
        		);
        		addressInput.sendKeys("KT16 0PS");
        		
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstDropdownOption = wait4.until(
        		    ExpectedConditions.elementToBeClickable(
        		        By.cssSelector("mat-option[role='option']")
        		    )
        		);

        		// 클릭!
        
        firstDropdownOption.click();
        System.out.println("드롭다운 첫 번째 주소 클릭 완료");		
        
        wait.until(driver1 -> {
            WebElement line1 = driver1.findElement(By.cssSelector("input[aria-label='line1']"));
            String value = line1.getAttribute("value");
            String valid = line1.getAttribute("aria-invalid");
            return value != null && !value.trim().isEmpty() && "false".equals(valid);
        });
        		
        // 1. 버튼 선택자 정의
        By continueBtnSelector = By.cssSelector("button.customer-details--continue-btn");

        // 2. 스크롤 살짝 내리기
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);"); // 화면 아래로 300px 정도 스크롤
        Thread.sleep(500); // 약간의 대기

        // 3. 버튼이 클릭 가능해질 때까지 기다리기
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait5.until(
        ExpectedConditions.elementToBeClickable(continueBtnSelector));

    	// 4. 클릭 (JS로 하면 실패 확률 더 줄어듦)
    	js.executeScript("arguments[0].click();", continueBtn);
    	System.out.println("Continue to delivery options 버튼 클릭 완료");
    	
    	
    	WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement OrdercontinueBtn = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.delivery-details--continue-btn")));

    	// 스크롤 내려 버튼이 하단에 보이도록
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", OrdercontinueBtn);
    	Thread.sleep(500); // 잠깐 대기

    	// JS 클릭 시도
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrdercontinueBtn);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          //  driver.quit(); 크롬창 유지
        }
    }
}
