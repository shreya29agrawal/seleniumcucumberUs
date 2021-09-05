package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailComposePage {

	@FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
	private WebElement compose;

	@FindBy(name = "to")
	private WebElement recId;

	@FindBy(xpath = "//div[@aria-label='Send ‪(Ctrl-Enter)‬']")
	private WebElement send;

	@FindBy(xpath = "//div[@class='Ar Au']//div")
	private WebElement body;

	@FindBy(name = "subjectbox")
	private WebElement subject;

	private WebDriver driver;
	WebDriverWait myWait;

	public GmailComposePage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		myWait = new WebDriverWait(driver, 20);
	}

	public String composeMail(String recId) {

		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='T-I T-I-KE L3']")));

		Actions actions = new Actions(driver);

		// Call moveToElement() method of actions class to move mouse cursor to
		// a WebElement compose.
		actions.moveToElement(compose).click().build().perform();

		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));

		this.recId.sendKeys(recId);

		String msgSubject = " Incubyte ";

		subject.sendKeys(msgSubject);

		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Ar Au']//div")));

		body.sendKeys(" Automation Qa test for Incubyte");

		send.click();

		return msgSubject;

	}

}
