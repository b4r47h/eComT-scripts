# SweetShop Test Automation Project

This repository contains Selenium-based automated test scripts for the SweetShop website. The project is structured using Maven and TestNG, making it easy to execute and manage test cases.

## Project Structure
```
AuPr/
├── pom.xml                # Maven project file
├── testng.xml             # TestNG suite configuration
├── src/test/java/f1/      # Contains all test classes
│   ├── SweetShopTest1.java
│   ├── SweetShopTest2.java
│   ├── SweetShopTest3.java
│   ├── SweetShopTest4.java
└── .settings/             # Eclipse IDE settings
```

## Prerequisites
- Java JDK 8+
- Maven
- Selenium WebDriver
- TestNG
- ChromeDriver (for running tests in Chrome)

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd AuPr
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```
3. Run the test suite:
   ```sh
   mvn test
   ```

## Test Execution
- Run all tests with:
  ```sh
  mvn test
  ```
- Run specific tests using TestNG:
  ```sh
  mvn test -Dsurefire.suiteXmlFiles=testng.xml
  ```

## Reporting
Test results are generated in the `target/surefire-reports/` directory. You can view the results in HTML format after execution.

## Contribution
Feel free to fork the repository and submit pull requests for improvements.

## License
This project is licensed under the MIT License.

---

Developed for testing purposes using Selenium and TestNG.

