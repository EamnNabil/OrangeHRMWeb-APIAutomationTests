# Automation Testing Suite

This project contains an automation testing suite that encompasses both web automation tests and API tests for the OrangeHRM application. The tests are organized using TestNG, a popular testing framework in Java.

## Project Structure

```
├── src
│   └── test
│       ├── java
│       │   ├── Pages
│       │   │   └── AddUser.java
│       │   │   └── AdminPage.java
│       │   │   └── BasePage.java
│       │   │   └── DashboardPage.java
│       │   │   └── LoginPage.java
│       │   ├── Tests
│       │   │   ├── apis
│       │   │   │   └── OrangeHRMCandidateAPI.java
│       │   │   ├── web
│       │   │   │   └── WebAutomationTests.java
│       │   ├── Utils
│       │   │   └── BrowserDriverFactory.java
├── testng.xml                             # TestNG suite configuration file
├── pom.xml                                # Maven project configuration file
└── README.md                              # Project documentation
```

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK** (version 17 or higher)
- **Maven** (version 3.6 or higher)
- An IDE (like IntelliJ IDEA or Eclipse) with Maven support

## Setting Up API Cookie
To ensure the API tests run successfully, you must provide a valid session cookie in the API requests. Follow these steps:

### Retrieve the Cookie:

- Log in to OrangeHRM via a web browser.
- Open your browser’s developer tools (usually F12 or right-click > Inspect).
- Navigate to the Network tab and log in if you haven't already. Look for a request with a Cookie header.
- Copy the orangehrm cookie value.
- Add Cookie to OrangeHRMCandidateAPI cookieValue variable:

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```

2. **Install dependencies**:
   Navigate to the project directory and run:
   ```bash
   mvn install
   ```

3. **Run the Test Suite**:
   You can run the entire test suite using Maven:
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

## Test Suite Overview

The test suite is organized into two main sections:

1. **Web Automation Tests**:
   - **WebAutomationTests.java**: Contains test cases for testing web functionalities of the OrangeHRM application.

2. **API Tests**:
   - **OrangeHRMCandidateAPI.java**: Contains test cases for testing the API endpoints of the OrangeHRM application.

### Test Suite Configuration

The test suite is configured using a TestNG XML file (`testng.xml`) that specifies which test classes to execute.
