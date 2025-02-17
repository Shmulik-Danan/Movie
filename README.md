# Android Clean Architecture

This project demonstrates the implementation of **Clean Architecture** in an Android application, divided into three main layers: **Domain**, **Data**, and **Presentation (App)**.

## Table of Contents
1. [About the Project](#about-the-project)  
2. [Architecture Layers](#architecture-layers)  
3. [Libraries and Technologies](#libraries-and-technologies)  


---

## About the Project

The goal of this project is to showcase how to implement Clean Architecture in an Android application by clearly separating business logic from the UI and data handling. This separation helps in maintaining, testing, and scaling the code effectively.

---

## Architecture Layers

The project is structured into three layers:

1. **Domain Layer**  
   - Contains the core business logic (Entities..).  
   - Independent of platform-specific details such as UI frameworks, databases, or network libraries.  
   - Communicates with the Data layer through defined interfaces (Repositories).

2. **Data Layer**  
   - Responsible for accessing external data sources (APIs, databases, files, etc.).  
   - Contains Repositories that implement the interfaces defined in the Domain layer.  
   - Handles the mapping between external data models (DTOs/Database Entities) and Domain models.

3. **Presentation (App) Layer**  
   - Manages the user interface and user interactions.  
   - Uses [Jetpack Compose](https://developer.android.com/jetpack/compose) for building modern, declarative UI components.  
   - ViewModels (if implemented) interact with Repositories to retrieve the necessary data.

---

## Libraries and Technologies

1. **Compose**  
   - Utilized for building declarative and responsive user interfaces.

2. **Coroutines**  
   - Provides support for asynchronous programming, making it easier to manage background operations like network calls and database queries.

3. **Coil**  
   - A fast and efficient image loading library that integrates well with Compose.

4. **Navigation**  
   - Part of Android Jetpack, this library simplifies navigation between screens (using Fragments or Composables).

5. **Serialization**  
   - Handles the serialization and deserialization of objects, such as converting JSON data into Kotlin objects using libraries like kotlinx.serialization.

6. **Paging**  
   - Manages the incremental loading of large data sets, which is particularly useful for implementing efficient lists and feeds.

7. **Network**  
   - Network libraries (such as Retrofit and OkHttp) are used for making API calls, handling network requests, and data fetching.

8. **Room**  
   - Provides an abstraction layer over SQLite for local database management, featuring a simple API and support for migrations.

9. **Hilt**  
   - The official dependency injection (DI) library for Android by Google, which helps manage dependencies across the project (ViewModels, Use Cases, Repositories, etc.).

---

