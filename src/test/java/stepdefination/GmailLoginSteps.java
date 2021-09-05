package stepdefination;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import com.pages.GmailComposePage;
import com.pages.GmailLogin;

import com.qa.util.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GmailLoginSteps {
	
	private GmailLogin gmail_login; 
	
	
	@Given("User is on gmail Home Page")
	public void user_is_on_gmail_home_page() {
		gmail_login = new GmailLogin();
	    
	}
	
	@When("When user enters the Username and password  from given sheetname {string} and rownumber {int}")
	public void when_user_enters_the_username_and_password_from_given_sheetname_and_rownumber(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		 ExcelReader reader = new ExcelReader();
		 List<Map<String,String>> testData= reader.getData("C:\\Users\\OM SAI RAM\\workspace\\seleniumcucumberUs\\src\\test\\resources\\data\\loginCredential.xlsx", sheetName);
		 String User_Email = testData.get(rowNumber).get("UserEmail");
		 String User_Pwd = testData.get(rowNumber).get("Password");
		 
		 gmail_login.gmailloginwithdata(User_Email , User_Pwd ) ;
		 
	}

	@When("User Navigates to Account Page")
	public void user_navigates_to_account_page() {
	  String expectedTitle = "Gmail";
	  
	  String actualTitle = gmail_login.afterLoginPage();
	  assertEquals(expectedTitle, actualTitle);
	  
	}


	@When("User click on compose Mail and fill the details")
	public void user_click_on_compose_mail_and_fill_the_details() {
		gmail_login.composeMail(); 
	}

	@Then("after mail was sent")
	public void after_mail_was_sent() {
		gmail_login.afterMailSend();
	   
	}

	
	@Then("user logout from account")
	public void user_logout_from_account() {
		gmail_login.logOut();
		
		 String expectedTitle = "Gmail";
		  
		  String actualTitle = gmail_login.afterLogout();
		  assertEquals(expectedTitle, actualTitle);
		
		gmail_login.closeBrowser();
		
	}


}
