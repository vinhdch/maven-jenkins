@login 
Feature: Facebook login page 

// apply as before
#Background:
#Given Open facebook application

@first_step
Scenario: Have no param 
#	Given Open facebook application 
	Then Input to Username textbox 
	And Input to Password textbox 
	And Click to submit button 
#	And Close application 
	
#Scenario: Have param 
#	Given Open facebook application 
#	Then Input to Username textbox with "vinhdch@gmail.com" 
#	And Input to Password textbox with "vinhdch@gmail.com" 
#	And Click to submit button 
#	And Close application 
	
@data_table 
Scenario Outline: Data table 
#	Given Open facebook application 
	Then Input to Username and password 
	| Email   						| Password |
  | autotest1@gmail.com | 123123   |
  | autotest2@gmail.com | 123123   |
  | autotest3@gmail.com | 123123   |
  | autotest4@gmail.com | 123123   |
	And Click to submit button
#	And Close application 


 Examples: 
| Email    					 | Password |             
| autotest@gmail.com | 123123   |

	
