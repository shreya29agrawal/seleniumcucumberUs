package com.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLogin extends LoadableComponent<GmailLogin> {

	@FindBy(xpath = "//span[text()='Next']")
	private WebElement next;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	@FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
	private WebElement compose;

	@FindBy(xpath = "//*[@id='message-to-field']")
	private WebElement recId;

	@FindBy(xpath = "//span[text()='Send']")
	private WebElement send;

	@FindBy(xpath = "//img[@class='gb_Ca gbii']")
	private WebElement presentation;

	@FindBy(xpath = "//a[@class='gb_Cb gb_Tf gb_2f gb_Pe gb_3c']")
	private WebElement signout;

	@FindBy(xpath = "//a[text()='Sent']")
	private WebElement sent;

	private WebDriver driver;
	WebDriverWait myWait;

	Actions action;

	public GmailLogin()

	{
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\OM SAI RAM\\workspace\\seleniumcucumberUs\\src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver","C:\\Users\\OM SAI
		// RAM\\workspace\\seleniumcucumberUs\\src\\test\\resources\\geckodriver.exe");
		// driver= new FirefoxDriver();

		PageFactory.initElements(driver, this);
		// this refer current object or page object
		/*
		 * Parameters:driver The driver that will be used to look up
		 * 
		 */

		get(); // to load and isload methode for methode calling
				// abstract methode for loadablecomponent

		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();

		myWait = new WebDriverWait(driver, 10);
	}

	public void gmailloginwithdata(String User_Email, String User_Pwd) {

		username.sendKeys(User_Email);
		next.click();

		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		password.sendKeys(User_Pwd);

		next.click();

	}

	public String afterLoginPage() {
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}

	public void composeMail() {

		GmailComposePage composePage = new GmailComposePage(driver);
		composePage.composeMail("agrawal.shreya29@yahoo.com");
	}

	public void afterMailSend() {

		myWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='TN bzz aHS-bnu']//div[@class='qj ']")));

		WebElement sentIcon = driver.findElement(By.xpath("//div[@class='TN bzz aHS-bnu']//div[@class='qj ']"));

		Actions action = new Actions(driver);
		// Performing the mouse hover action on the target element.
		action.moveToElement(sentIcon).click().build().perform();

		// sent.click();

		List<WebElement> msg = driver.findElements(By.xpath("//*[contains(text(),'Automation Qa test for Incubyte')]"));

		if (!msg.isEmpty()) {
			System.out.println("Wowww.. Email sent sucessfully!!!");

		} else {
			System.out.println("Mail was not Sent");
		}

	}

	public void logOut() {
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='gb_Ca gbii']")));

		presentation.click();
		signout.click();
	}

	public String afterLogout() {

		return driver.getTitle();
	}

	public void closeBrowser() {
		driver.quit();
	}

	@Override
	protected void load() {
		driver.get("https://www.gmail.com");

	}

	@Override
	protected void isLoaded() throws Error {
		String expectedTitle = "Gmail";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		assertEquals(actualTitle, expectedTitle, "Internet connection was not established");

	}

}
