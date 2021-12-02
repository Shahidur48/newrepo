package edu.baylor.cs.se.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

    private WebDriver driver;

    @Before
    public void setup(){
        String pathDriver = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathDriver);
        driver = new ChromeDriver();
    }

    @Test
    public void socketTeam(){
        driver.get("http://localhost:8080/index.html");
        driver.findElement(By.id("connect")).click();
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("edit-button")).click();
        driver.findElement(By.xpath("//*[@id=\"status\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"status\"]")).sendKeys("Pending");

    }

    public void close(){
        driver.close();
    }
}
