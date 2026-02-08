 Dream Journal API
A professional Spring Boot-based RESTful API for tracking and analyzing dreams. This project demonstrates advanced software engineering practices, including design patterns, clean architecture, and SOLID principles.

Design Patterns 
The project utilizes three major Creational Patterns to ensure code flexibility and reusability:
Singleton Pattern:
Implementation: DatabaseConnection class.
Purpose: Ensures that only one instance of the database connection exists throughout the application lifecycle, preventing resource leaks and managing database overhead efficiently.
Factory Method Pattern:
Implementation: DreamFactory class.
Purpose: Centralizes the logic for creating different dream types (LUCID, NIGHTMARE). It decouples the client code (Service layer) from the concrete classes, making it easy to add new dream types in the future.
Builder Pattern:
Implementation: DreamBuilder class.
Purpose: Provides a fluent API for constructing complex DreamEntry objects. It solves the "telescoping constructor" problem and improves code readability when dealing with many parameters.
SOLID Principles & OOP
S (Single Responsibility): Each layer has a specific role. The Controller handles HTTP, the Service manages business logic, and the Repository handles data persistence.
O (Open/Closed): The system is open for extension (adding new dream types) but closed for modification of existing logic.
L (Liskov Substitution): LucidDream and Nightmare objects can be used interchangeably wherever the parent DreamEntry is expected without breaking the application.
I (Interface Segregation): Interfaces (if applicable) and classes are kept lean and focused on specific behaviors.
D (Dependency Inversion): High-level modules (Service) do not depend on low-level implementation details but rather on abstractions.
 Technology Stack
Java 17
Spring Boot: For Dependency Injection, REST controllers, and centralized Exception Handling.
Jackson: For polymorphic JSON processing using @JsonTypeInfo and @JsonSubTypes.
PostgreSQL / JDBC: For persistent data storage and reliable transaction management.
Database Schema
The application uses a structured SQL schema (schema.sql):
dreams Table: Stores core dream data with a type discriminator column and an extra_param for subtype-specific features.
emotions Table: A lookup table for managing emotional states associated with dreams.