# Testing Project

This is a comprehensive Java-based testing project which uses several technologies including Cucumber, Gherkin, RestAssured, and Maven. The project is designed with readability, modularity, and maintainability in mind.

## Features

- Written in Java 11.
- Uses Cucumber as the main testing framework, which supports Behavior Driven Development (BDD) concepts.
- Gherkin language is used for writing Cucumber feature files and step definitions.
- Utilizes RestAssured library for testing HTTP-based REST services.
- The project is Maven-based and uses Maven for dependencies and build automation.
- Follows the Page Object Model (POM) which improves test maintenance and reduces code duplication.
- Configuration reader is used to hide sensitive data.
- Include hook class to execute preconditions and post conditions for each scenario/case.
- Step definitions for feature files are written in detail to cover multiple test scenarios.


## Prerequisites

- Java SDK version 11.
- Maven
- Cucumber Plugin

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/enderta/TestingProject.git
   ```
2. Navigate to the repository directory:
   ```
   cd testingproject
   ```

## Usage

**Running the Tests:**

* You can run the tests using Maven with the following command:

   ```shell
   mvn clean test
   ```

* Alternatively, navigate to the cucumber runner class and execute it directly from there.

**Configuration:**

* You will need to create a `config.properties` file in your project directory. Add the following key-value pairs to this file:

    ```properties
    base_url=
    post_login=
    post_register=
    url=https://master.d2jfuckny8hv4x.amplifyapp.com/
    username=<your_username>
    password=<your_password>
    ```

* Please replace contact me for data to run this project