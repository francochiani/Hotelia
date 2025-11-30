# Hotelia
Hotelia is a Java-based project related to hotel management. It utilizes a DAO (Data Access Object) pattern for database interaction. The project aims to provide core functionalities for managing various aspects of a hotel, including rooms, guests, bookings, and billing.

![alt text](https://github.com/francochiani/Hotelia/blob/master/src/main/resources/banner.png "Hotelia Banner")

## Key Features & Benefits

*   **Database Interaction:** Implements DAO pattern for structured database access.
*   **Modular Design:** Separates concerns into different DAO classes for each entity (e.g., `HuespedDAO`, `HabitacionDAO`).
*   **Core Hotel Management Functions:** Likely provides functionalities to manage guests, rooms, stays, billing and related entities

## Prerequisites & Dependencies

Before you begin, ensure you have the following installed:

*   **Java Development Kit (JDK):** Version 8 or higher.
*   **Integrated Development Environment (IDE):** (e.g., IntelliJ IDEA, Eclipse).
*   **Maven:** For dependency management (configured through `pom.xml`).
*   **Database:** A relational database (e.g., MySQL, PostgreSQL, H2) to store hotel data. Ensure the appropriate JDBC driver is included in the `pom.xml`.

## Installation & Setup Instructions

1.  **Clone the Repository:**

    ```bash
    git clone https://github.com/francochiani/Hotelia.git
    cd Hotelia
    ```

2.  **Import the Project into your IDE:**

    *   **IntelliJ IDEA:** Open the `pom.xml` file as a Maven project.
    *   **Eclipse:** Import as an existing Maven project.

3.  **Configure Database Connection:**

    *   Modify the `conexion.java` file located in `src/main/java/DAO/conexionDB/` to reflect your database connection details.  You will need to update the connection string, username, and password.

    ```java
    // Example (replace with your actual values)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
    ```

4.  **Install Dependencies:**

    *   If using Maven, the IDE should automatically resolve the dependencies defined in `pom.xml`. You can also run the following command in the project root directory:

    ```bash
    mvn install
    ```

5.  **Run the Application:**

    *   Locate the main application class (e.g., a class with a `main` method).
    *   Run the application from your IDE.

## Configuration Options

*   **Database Connection:** The most important configuration is the database connection string, located in the `conexion.java` file. Adjust the `DB_URL`, `DB_USER`, and `DB_PASSWORD` variables accordingly.
*   **Logging:** You can configure logging levels using a logging framework (e.g., Log4j, SLF4J). Add the necessary logging dependencies to your `pom.xml` and configure the logging settings.

