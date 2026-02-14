Bonus Task Implementation: Caching Layer
This document describes the implementation of the In-Memory Caching mechanism added to the Dream Journal API to optimize performance and demonstrate advanced design patterns.

Objective
The goal was to enhance application performance by storing frequently accessed data (all dreams) in memory, reducing the number of direct queries to the PostgreSQL database.

Technical Features
1. Singleton Design Pattern
   The DreamCache class is implemented using the Singleton pattern to ensure that only one instance of the cache exists throughout the application lifecycle.
   It uses a private constructor and a getInstance() method to control access.
2. In-Memory Storage
   The cache uses a Map structure (ConcurrentHashMap) to store and retrieve data efficiently in the application's RAM.
   This ensures that repeated calls to getAllDreams() return data instantly without hitting the database.
3. Cache Invalidation Mechanism
   To prevent serving "stale" or outdated data, an Automatic Invalidation mechanism was implemented:
   Automatic Clear: Whenever a dream is added, updated, or deleted, the system calls invalidate().
   Data Integrity: This ensures the cache is always synchronized with the PostgreSQL database.
Architectural Integration
   Layered Integrity: The caching logic is encapsulated within the Service Layer, ensuring that the Controller and Repository layers remain unaffected.
   SOLID Principles: The implementation follows the Single Responsibility Principle, where DreamCache is solely responsible for data persistence in memory.
 How to Verify
   Initial Load: Send a GET request to /api/dreams. The application fetches data from the DB and populates the cache.
   Performance Boost: Send the same GET request again. Data is returned from the DreamCache (check the IDE console for "Returning cached data" log).
   Invalidation Test: Send a POST request to add a new dream. The cache is cleared automatically.
   Refresh: The next GET request will query the DB again to get the fresh list.