package com.example.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartToPaymentPDTest {

    private WebDriver driver;

    @BeforeAll
    void setupClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        // 테스트 후 브라우저를 닫고 싶지 않으면 주석 처리
        // driver.quit();
    }

    @Test
    void cartToPaymentFlow() throws InterruptedException {
        driver.get("https://www.samsung.com/uk/lifestyle-tvs/the-terrace/lst7d-the-terrace-55-inch-neo-qled-4k-outdoor-smart-tv-black-qe55lst7datxxu/");

        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait0.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".truste-button1")));

        WebElement TrustBtn = driver.findElement(By.cssSelector(".truste-button1"));
        TrustBtn.click();

        WebElement movePDBtn = driver.findElement(By.cssSelector(".pd-buying-price__cta"));
        movePDBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".order-summary")));

        Thread.sleep(1000);

        List<WebElement> btns = driver.findElements(By.cssSelector(".order-summary__btn"));
        WebElement realBtn = null;
        for (WebElement btn : btns) {
            if (btn.isDisplayed()) {
                realBtn = btn;
                break;
            }
        }
        if (realBtn != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", realBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", realBtn);
        } else {
            Assertions.fail("Checkout 버튼이 보이지 않음");
        }

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait2.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Enter your email address to checkout']")
            )
        );
        emailInput.sendKeys("test12@gmail.com");

        WebElement guestCheckoutBtn = driver.findElement(
            By.xpath("//button[span[text()=' Guest checkout ']]")
        );
        guestCheckoutBtn.click();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(500);

        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement matSelect = wait3.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("mat-select.mat-mdc-select"))
        );
   
     // 스크롤 맞추기 (이미 해도 한 번 더 해도 무방)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", matSelect);
        // 아주 잠깐 대기 (애니메이션/렌더링)
        Thread.sleep(300);
        // 클릭 시도 (JS로 하면 더 안전)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", matSelect);

        WebElement mrOption = wait3.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option[.//span[text()='Mr']]")
            )
        );
        mrOption.click();

        WebElement firstNameInput = driver.findElement(By.name("firstName"));
        firstNameInput.sendKeys("Dongin");

        WebElement lastNameInput = driver.findElement(By.name("lastName"));
        lastNameInput.sendKeys("Choi");

        WebElement phoneInput = driver.findElement(By.name("phone"));
        phoneInput.sendKeys("1234567788");

        WebElement PrivacyRadio = driver.findElement(By.cssSelector(".mdc-checkbox"));
        PrivacyRadio.click();

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
        firstDropdownOption.click();

        wait.until(driver1 -> {
            WebElement line1 = driver1.findElement(By.cssSelector("input[aria-label='line1']"));
            String value = line1.getAttribute("value");
            String valid = line1.getAttribute("aria-invalid");
            return value != null && !value.trim().isEmpty() && "false".equals(valid);
        });

        By continueBtnSelector = By.cssSelector("button.customer-details--continue-btn");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(500);

        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait5.until(
            ExpectedConditions.elementToBeClickable(continueBtnSelector)
        );
        js.executeScript("arguments[0].click();", continueBtn);

        WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement OrdercontinueBtn = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.delivery-details--continue-btn")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", OrdercontinueBtn);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrdercontinueBtn);
    }
}