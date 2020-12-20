# AppsFactory QA Challenge

This is a simple Web Testing Project for Chrome that executes the following Test Case.

"As a new Amazon user, I want to search for the cheapest Snickers and Skittles on the page.

Add the cheapest ones to your Basket and check if the basket calculates the result correctly

Check if on Checkout, without an account, the user gets redirected to the registration page."

<h1>Tools Used:</h1>

<b>Selenium</b>

Selenium is used to automate web applications. It has webdrivers for several different operating systems and browsers.
I have used the page object model of selenium to benefit from the DRY principle.

<b>Cucumber</b>

Cucumber is based on Behavior-Driven Development and helps to describe the test scenarios in a descriptive manner.

<b>Maven</b>

Maven is a build automation tool which helps to manage the dependencies. 
### Project Structure:

Every amazon web page involved in the test scenario is described in the form of a java class. These page object classes are present in amazon.pages package.

The scenarios are written in checkout.feature which is inside the features package

### running tests:
```
git clone https://github.com/Suganya31/AppsFactoryQA.git
mvn clean test -Dtest=runner.checkoutrunner
```
