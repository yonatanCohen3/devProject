Springboot Devops Project

Overview
This project involves developing a robust Continuous Integration and Continuous Deployment (CI/CD) automation server using Spring Boot, complemented by Python test scripts and Docker orchestration. The project is designed to showcase essential DevOps practices, tools, and concepts

Prerequisites
Before running this application, ensure that you have the following installed:

Python Interpreter
Apache Maven
Installation
Docker desktop
Clone the repository to your local machine:

git clone https://github.com/yonatanCohen3/devProject
You can build with Maven and run the spring boot web application using IntelliJ IDEA. If you want an alternative way then:

Navigate to the project directory:

- Run cmd from entire directory
- Run docker-compose up / docker-compose up -d
- Run docker run -it -p 8080:8080 --net=host finalproject-tester bash
- execute pytest .venv/test_api.py    ---> this will execute the tests and provide a summary in brief of the executions and also update via email about the completion.
