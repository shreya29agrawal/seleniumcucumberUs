Feature: Compose Mail Action to Gmail website
 Verify the compose  functionality is working
 as expected. 

Scenario Outline: Login with Valid Credentials
	Given User is on gmail Home Page 
	When When user enters the Username and password  from given sheetname "<SheetName>" and rownumber <RowNumber> 
	And User Navigates to Account Page
	And User click on compose Mail and fill the details
	Then after mail was sent 
	And user logout from account
	
Examples:
|SheetName||RowNumber|
|loginData||0|