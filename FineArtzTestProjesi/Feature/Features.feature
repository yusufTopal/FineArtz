Feature: FineArtz n11 Test

Scenario: Product wish list test

	Given Start Driver and Browser
	When Navigate to n11.com
	And Click log in
	Then Log in page should appear
	
	When Enter username 
	And Enter password
	And Log in click
	Then Should logged in
	
	When Search text entered
	And Search button clicked
	Then Search results should appear
	
	When Search results appeared
	And Check if there are more than three products
	Then Add to favourites
	
	When The product added to wishlist
	And My account page should appear
	And Isteklerim click
	And Favorilerim click
	Then Isteklerim should appear

	When Wished device appear on the list
	And Remove the wished product
	And Wished product should be removed from the list
	Then Log out
	