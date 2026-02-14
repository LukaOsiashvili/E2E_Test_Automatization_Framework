# End-to-End Test Automation Framework
-Author: Luka Osiashvili

A comprehensive test automation framework for the [Automation Exercise](https://automationexercise.com) platform, implementing both UI and API testing using Java, Selenium WebDriver, RestAssured, and TestNG. 

---

## Project Overview

This project implements an End-to-End Test Automation Framework covering:
- **UI Testing**: 11 automated test cases using Selenium WebDriver with Page Object Model
- **API Testing**: 10 automated test cases using RestAssured
- **Total Test Cases**: 21 automated tests

**Target Application:**
- Frontend (UI): https://automationexercise.com
- Backend (API): https://automationexercise.com/api
- Test Case Reference: https://automationexercise.com/test_cases

### Detailed Test Case Mapping

#### UI Test Cases (11 Tests)
1. **Test Case 1**: Register User - `AuthenticationTests.registerUser()`
2. **Test Case 2**: Login User with Correct Credentials - `AuthenticationTests.loginUserWithCorrectCredentials()`
3. **Test Case 3**: Login User with Incorrect Credentials - `AuthenticationTests.loginUserWithIncorrectCredentials()`
4. **Test Case 4**: Logout User - `AuthenticationTests.loginAndLogoutUser()`
5. **Test Case 5**: Register User with Existing Email - `AuthenticationTests.registerUserWithUsedEmail()`
6. **Test Case 6**: Contact Us Form - `BasicFeaturesFunctionalityTests.fillInContactUsForm()`
7. **Test Case 7**: Verify Test Cases Page - `BasicFeaturesFunctionalityTests.verifyTestPage()`
8. **Test Case 8**: Verify All Products and Product Detail Page - `BasicFeaturesFunctionalityTests.verifyProductsPage()`
9. **Test Case 9**: Search Product - `BasicFeaturesFunctionalityTests.testSearchProduct()`
10. **Test Case 10**: Verify Subscription on Home Page - `BasicFeaturesFunctionalityTests.verifySubscriptionOnHomePage()`
11. **Test Case 11**: Verify Subscription on Cart Page - `BasicFeaturesFunctionalityTests.verifySubscriptionOnCartPage()`

#### API Test Cases (10 Tests)
1. **API Test Case 1**: POST to Create User Account - `UserAPITest.testCreateUser()`
2. **API Test Case 2**: GET User Account Details by Email - `UserAPITest.testGetUserDetailsByEmail()`
3. **API Test Case 3**: PUT to Update User Account - `UserAPITest.testUpdateUser()`
4. **API Test Case 4**: DELETE User Account - `UserAPITest.testDeleteUser()`
5. **API Test Case 5**: POST to Verify Login with Valid Details - `UserAPITest.testVerifyLoginWithValidCredentials()`
6. **API Test Case 6**: POST to Verify Login without Email - `UserAPITest.testVerifyLoginWithoutEmail()`
7. **API Test Case 7**: GET All Products List - `ProductAPITest.testGetAllProductsList()`
8. **API Test Case 8**: POST to All Products List (405 Error) - `ProductAPITest.testPostToAllProductsList()`
9. **API Test Case 9**: POST to Search Product - `ProductAPITest.testSearchProduct()`
10. **API Test Case 10**: POST to Search Product without Parameter - `ProductAPITest.testSearchProductWithoutParam()`

Refer to the respective test csse instructions for detailed test case steps
---

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Programming Language |
| **Selenium WebDriver** | 4.36.0 | UI Automation |
| **TestNG** | 7.12.0 | Testing Framework |
| **RestAssured** | 6.0.0 | API Testing |
| **Maven** | 3.x | Build & Dependency Management |
| **Allure** | 2.32.0 | Test Reporting |
| **Jackson** | 2.17.2 | JSON Processing |

---

## Project Structure

```
E2E_Test_Automatization_Framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── api/
│   │   │   │   ├── client/          # API client classes
│   │   │   │   │   ├── ProductClient.java
│   │   │   │   │   └── UserClient.java
│   │   │   │   └── models/          # API data models
│   │   │   │       ├── Category.java
│   │   │   │       ├── Product.java
│   │   │   │       ├── User.java
│   │   │   │       └── UserType.java
│   │   │   ├── config/              # Configuration management
│   │   │   │   └── ConfigManager.java
│   │   │   ├── listeners/           # TestNG listeners
│   │   │   │   └── ScreenshotListener.java
│   │   │   ├── ui/
│   │   │   │   ├── components/      # Reusable UI components
│   │   │   │   │   ├── Footer.java
│   │   │   │   │   └── Header.java
│   │   │   │   └── pages/           # Page Object Model classes
│   │   │   │       ├── BasePage.java
│   │   │   │       ├── MainPage.java
│   │   │   │       ├── LoginPage.java
│   │   │   │       ├── SignupPage.java
│   │   │   │       ├── AccountCreatedPage.java
│   │   │   │       ├── AccountDeletePage.java
│   │   │   │       ├── CartPage.java
│   │   │   │       ├── ContactUsPage.java
│   │   │   │       ├── ProductsPage.java
│   │   │   │       ├── ProductDetailsPage.java
│   │   │   │       └── TestCasesPage.java
│   │   │   └── utils/               # Utility classes
│   │   │       ├── DriverFactory.java
│   │   │       ├── FileUtils.java
│   │   │       ├── RequestSpecFactory.java
│   │   │       └── TestDataFactory.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── BaseTest.java
│       │       ├── api/
│       │       │   ├── ProductAPITest.java
│       │       │   └── UserAPITest.java
│       │       └── ui/
│       │           ├── AuthenticationTests.java
│       │           └── BasicFeaturesFunctionalityTests.java
│       └── resources/
│           └── testFiles/
│               └── test_file.txt
├── pom.xml
├── testng.xml
└── README.md
```

---

## Framework Features

### Design Patterns
- **Page Object Model (POM)**: Clean separation of page structure and test logic
- **Factory Pattern**: WebDriver and RequestSpecification initialization
- **Builder Pattern**: API request configuration

### Core Capabilities
- **Parallel Execution**: TestNG parallel execution at class level
- **Screenshot on Failure**: Automatic screenshot capture for failed UI tests
- **API Request/Response Logging**: Full request and response details attached to Allure reports
- **Reusable Components**: Header and Footer components shared across pages

### Reporting Features
- Detailed Allure reports with:
  - Test execution steps
  - Screenshots for failed UI tests
  - API request and response attachments
  - Test execution time
  - Pass/Fail statistics

---

## Getting Started

### Prerequisites
- **Java JDK**: Version 21 or higher
- **Maven**: Version 3.6 or higher
- **Allure**: For report generation (optional but recommended)
- **Microsoft Edge Browser**: Framework is configured for Edge (can be modified for Chrome)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd E2E_Test_Automatization_Framework
   ```

2. **Verify Java Installation**
   ```bash
   java -version
   # Should show Java 21 or higher
   ```

3. **Verify Maven Installation**
   ```bash
   mvn -version
   ```

4. **Install Allure (Optional)**
   ```bash
   # On macOS using Homebrew
   brew install allure

   # On Windows using Scoop
   scoop install allure

   # On Linux
   sudo apt-add-repository ppa:qameta/allure
   sudo apt-get update
   sudo apt-get install allure
   ```

---

## Running Tests

### Run Test Suites In Parallel Using Classes
Run testng.xml file

---

## Generating Allure Reports

### Method 1: Using Allure Command Line

 **Generate and Open Report**
   ```bash
   allure serve target/allure-results
   ```
   This will automatically open the report in your default browser.

### Report Features
The Allure report includes:
- Test execution overview with pass/fail statistics
- Test execution trends and history
- Screenshots for failed UI tests
- Detailed step-by-step execution logs
- API request and response details
- Test execution time and duration
- Test categorization by Epic and Feature

---

## Configuration

### TestNG Configuration
Located at: `testng.xml`
- Parallel execution: Enabled at class level
- Test suites: UI Tests and API Tests separated
- Listeners: ScreenshotListener for automatic screenshot capture

### Browser Configuration
Located at: `src/main/java/utils/DriverFactory.java`
- Default browser: Microsoft Edge (Incognito mode)
- Window size: Maximized
- Implicit wait: 10 seconds

**To switch to Chrome:**
```java
// In DriverFactory.java, replace EdgeDriver with ChromeDriver
ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
options.addArguments("--start-maximized");
WebDriver webDriver = new ChromeDriver(options);
```
---

**Last Updated**: February 2026
