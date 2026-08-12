# IPL Project

A Java-based IPL data analysis project developed as part of the MountBlue training program.

The project processes IPL match and ball-by-ball delivery data and provides different features and analysis based on the available dataset.

## Features

* Extracts data from IPL CSV files
* Processes match and delivery information
* Performs IPL-related statistical analysis
* Uses separate models and POJOs for better code organization
* Provides utility classes for common operations
* Uses a controller and feature-based structure

## Project Structure

```text
IPL-Project-Mountblue/
│
├── src/
│   ├── Controller/
│   ├── DataExtraction/
│   ├── Features/
│   ├── Models/
│   ├── POJO/
│   ├── Utils/
│   └── Main.java
│
├── deliveries.csv
├── matches.csv
├── IPL.iml
├── .gitignore
└── README.md
```

## Dataset

The project uses two CSV files:

### matches.csv

Contains information about IPL matches such as:

* Match ID
* Season
* Teams
* Venue
* Toss information
* Match result
* Winner
* Player of the match

### deliveries.csv

Contains ball-by-ball information such as:

* Match ID
* Inning
* Batting team
* Bowling team
* Batsman
* Bowler
* Runs
* Extras
* Wickets

## Technologies Used

* Java
* Object-Oriented Programming
* Collections
* File Handling
* CSV Data Processing
* Multithreading

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Dhruvdangi03/IPL-Project-Mountblue.git
```

### 2. Open the project

Open the project in IntelliJ IDEA or any Java-supported IDE.

### 3. Make sure Java is installed

Check your Java version:

```bash
java -version
```

### 4. Run the application

Run:

```text
src/Main.java
```

The application will read the IPL data from:

```text
matches.csv
deliveries.csv
```

and execute the available features.

## Architecture

The project follows a modular structure:

**DataExtraction**

Responsible for reading and processing data from the CSV files.

**Models / POJO**

Contains classes representing IPL entities and data structures.

**Features**

Contains the different IPL analysis and business logic.

**Controller**

Handles the flow between the application and its features.

**Utils**

Contains reusable utility methods.

## Learning Outcomes

Through this project, I worked with:

* Java OOP concepts
* Collections and data structures
* CSV file processing
* Separation of concerns
* Modular project structure
* Data analysis using Java
* Multithreading and concurrent data processing

## Author

**Dhruv Dangi**

GitHub: [Dhruvdangi03](https://github.com/Dhruvdangi03)

## Repository

[IPL-Project-Mountblue](https://github.com/Dhruvdangi03/IPL-Project-Mountblue)
