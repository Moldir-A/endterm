Dream Journal REST API
A. Project Overview
This project is a Spring Boot RESTful API designed for a digital dream journal. It allows users to log, categorize, and analyze different types of dreams (Lucid Dreams and Nightmares) while tracking emotional states. The system demonstrates advanced backend architecture by integrating Design Patterns, SOLID principles, and Component Principles.

B. REST API Documentation
The API provides full CRUD functionality for dream entries and emotion management.

Endpoints
Method	Endpoint	Description
GET	/api/dreams
Retrieve all dream entries

POST	/api/dreams
Create a new dream (Lucid or Nightmare)

PUT	/api/dreams/{id}
Update an existing dream entry

DELETE	/api/dreams/{id}
Remove a dream from the database

GET	/api/emotions	List all tracked emotions
Sample JSON Request (POST /api/dreams)
JSON
{
"type": "LUCID",
"title": "Ocean Flight",
"description": "Flying over a digital ocean with high control.",
"intensity": 9,
"date": "2026-02-14",
"extraParam": true
}
C. Design Patterns
Singleton: Implemented in DatabaseConnection to ensure a single, shared connection pool across the application.
Factory: The DreamFactory handles the instantiation of LucidDream vs Nightmare objects based on the input type.
Builder: DreamBuilder is used within the factory to construct complex dream objects with fluent method calls.
D. Component Principles
REP (Reuse/Release Equivalence): Common logic is separated into patterns and utils packages for high reusability.
CCP (Common Closure): Related logic (e.g., Dream models and DTOs) is grouped together so changes in dream logic are contained.
CRP (Common Reuse): The package structure ensures that the controller layer only depends on the service layer, avoiding unnecessary dependencies.
E. SOLID & OOP Summary
S (Single Responsibility): Controllers handle HTTP, Services handle logic, and Repositories handle Data.
O (Open/Closed): New dream types can be added via the DreamFactory without modifying existing service code.
L (Liskov Substitution): LucidDream and Nightmare seamlessly substitute the DreamEntry base class.
Advanced OOP: Utilizes abstract classes and inheritance for dream categorization.
F. Database Schema

The system uses a relational PostgreSQL database  with two primary tables:
dreams: Stores ID, type, title, description, intensity, date, and extra parameters.
emotions: Stores unique emotion tags.
G. Instructions to Run
Database: Create a PostgreSQL database named mold.
Schema: Execute the provided schema.sql to initialize tables.
Configure: Update credentials in DatabaseConnection.java.
Launch: Run Application.java as a Spring Boot application.