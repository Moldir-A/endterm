Bonus Task: In-Memory Caching Layer
Objective
The performance of the application has been enhanced by implementing a custom in-memory caching mechanism for frequently accessed dream records.

Implementation Details
In-Memory Storage: Data is stored directly in RAM using a List structure within the application, avoiding redundant database queries.
+1
Singleton Pattern: The caching logic is encapsulated in the DreamCache class, which follows the Singleton pattern to ensure only one cache instance manages the data throughout the application lifecycle.
+1
Cached Methods: The getAll() method in DreamService is optimized to check the cache before querying the PostgreSQL database.
+1
Cache Invalidation: To ensure data integrity, the cache is automatically cleared (invalidated) whenever a create, update, or delete operation is performed.
SOLID Compliance: The caching layer is decoupled from the repository and database logic, maintaining a clean layered architecture without breaking core business rules.
How it works
First Request: The application queries the database and populates the DreamCache.
Repeated Requests: Data is served instantly from the DreamCache.
Data Change: Any POST, PUT, or DELETE request triggers cache.clear(), forcing the next GET request to fetch fresh data from the database.