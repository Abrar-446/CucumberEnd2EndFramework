Feature: Add customer test

#Add all the common steps of scenarios in Background

Background: Steps common for all scenarios
	Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then User can view Dashboad

@Sanity
Scenario: Add new customer
#All common steps added in Background:
#Given User Launch Chrome browser 
#	When User opens URL "http://admin-demo.nopcommerce.com/login" 
#	And User enters Email as "admin@yourstore.com" and Password as "admin" 
#	And Click on Login 
#	Then User can view Dashboad
	
	When User click on customers Menu 
	And click on customers Menu Item 
	And click on Add new button 
	Then User can view Add new customer page 
	When User enter customer info 
	And click on Save button 
	Then User can view confirmation message "The new customer has been added successfully." 
	And close browser

@Sanity
Scenario: Search customer by Email
#All common steps added in background
#Given User Launch Chrome browser 
#	When User opens URL "http://admin-demo.nopcommerce.com/login" 
#	And User enters Email as "admin@yourstore.com" and Password as "admin" 
#	And Click on Login 
#	Then User can view Dashboad
	
	When User click on customers Menu
	And click on customers Menu Item
	And Enter customer Email
	When Click on Search button
	Then user should found email in the search table
	And close browser

@Sanity
Scenario: Search customer by Name
#All common steps added in background
#	Given User Launch Chrome browser 
#	When User opens URL "http://admin-demo.nopcommerce.com/login" 
#	And User enters Email as "admin@yourstore.com" and Password as "admin" 
#	And Click on Login 
#	Then User can view Dashboad
	
	When User click on customers Menu
	And click on customers Menu Item
	And Enter customer FirstName
	And Enter customer LastName
	When Click on Search button
	Then user should found Name in the search table
	And close browser