# - Marvel App

Android application that makes use of the [Marvel API](https://developer.marvel.com/docs) to display some info in a good way.

## Features

- Clean architecture & SOLID principles
- MVVM
- Dependency Injection with Hilt
- Multi Module
- Coroutines
- Error control
- ViewBinding
- State Flows & Channels
- UseCases unit tests
- Jetpack Navigatión
- Retrofit

#### The app is divided in three main layers:
 - App (presentation/UI)
 - Data
 - Domain

### Domain
Layer that contains app entities, repository interfaces and Use Cases (and tests). It has no dependencies with other layers.

### Data
Contains all business logic. Layer in charge of fetching data by implementing our domain interfaces and mapping the DTOs. It depends on domain.

### App (Presentation/UI)
Contains UI. This includes ViewModels (and its respective DI), fragments, activities and delegates. It depends on domain.

### Futurible (due to lack of time)
- Include pagination : Marvel API supports it.
- Better error handling
- Improve UI
- Animations on navigation
