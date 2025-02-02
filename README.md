## Project Structure

This project was originally completed in my **second year** as part of the **Object-Oriented Programming** course and has been refined this year to make it more compatible for **Software testing** course. 

The `src` folder is divided into two subfolders: **main** and **test**.

- The **main** folder contains six key classes:  
  **Administrator, BillNumber, Book, Librarian, Manager, and MainFx**, forming the core functionality of the application.

- The **test** folder is the most crucial part of the project, as it contains all **test cases**. It is structured into three subfolders, each dedicated to a specific type of testing:  
  - **Unit testing**  
  - **Integration testing**  
  - **FX testing**, which also covers **system testing**  

This structured approach ensures **comprehensive validation** of the project's functionality and reliability.

  ![image](https://github.com/user-attachments/assets/f4ff8a3a-926f-468a-a5dc-8e1442db81b0)

## Total Coverage and Conclusions

Running every single test that I had developed above with **coverage** left me with a **total coverage** as follows:
![image](https://github.com/user-attachments/assets/94d5cd26-d20d-4cf1-a63d-8be67ff0c03e)

The **test suite** developed for the **Library Management System** achieved a very high **overall coverage**, thoroughly validating most of the **critical components**.  

- Classes such as **Administrator, BillNumber, and Manager** were tested to full completion, achieving **100% coverage** across all metrics.  
- **Book and Librarian** also reached very high coverage, with only minor methods like `toString` left untested.  
- However, the **MainFx** class posed significant challenges due to its **extensive methods** and **diverse functionalities**, resulting in a relatively lower coverage of **45% for methods** and **53% for lines**.  

Naturally, working on the project **single-handedly**, the hardships regarding **time constraints** were reflected in the class with the most content to cover. However, despite these challenges, the **main workflows** (**user login, navigation, and role-specific operations**) were effectively validated, ensuring that the **core functionalities** of the application are **reliable**.
