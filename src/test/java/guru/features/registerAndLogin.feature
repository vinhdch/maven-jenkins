Feature: Register and login

@register
Scenario: Register to system 
	Given Open regitser page
	When Input to email textbox
	And Click to submit
	Then Get username and password
	And Back to home page
	And Get home page

@new_customer
Scenario Outline: New customer
	Given Open New customer page with value "New Customer"
	When Input to customer name textbox with value "<Customer Name>"
	And Click to "f" radio button
	And Input to "Address" textarea with value "<Address>"
	And Input to "City" textarea with value "<City>"
	And Click Submit button
	Then Verify to successful message
	And The correct input value is displayed with value "<Customer Name>"
	And The correct input value is displayed with value "<Address>"
	And The correct input value is displayed with value "<City>"
	
	Examples:
	| Customer Name | Dob In 	 | Address 	  | City 			| State| Pin  | Phone  | Email          | Password   |
	| John CK 		| 09/12/1995 | 1995/09/15 | 123 Cong Hoa st | HCMC | HCMC | 123456 | vinh@gmail.com | 1234567890 |
	
@regex
Scenario: Regex
	Given I input first account ID
	And I input second account ID
	And I input third account ID
	And I transfer to "<amount>" USD
	And I withdraw to "<amount>" USD