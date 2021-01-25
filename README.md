# Weather

A simple weather app for Android phone using Open Weather API (https://openweathermap.org/api)


## User Stories

The following **required** functionality is completed:

* [x] 1, The application is a simple Android application which is written by Java/Kotlin.
* [x] 2, The application is able to retrieve the weather information from OpenWeatherMaps
API.
* [x] 3, The application is able to allow user to input the searching term.
* [x] 4, The application is able to proceed searching with a condition of the search term length
must be from 3 characters or above.
* [x] 5, The application is able to render the searched results as a list of weather items.
* [x] 6, The application is able to support caching mechanism so as to prevent the app from
generating a bunch of API requests.
* [x] 7, The application is able to manage caching mechanism & lifecycle.
* [x] 8, The application is able to handle failures.
* [x] 9, The application is able to support the disability to scale large text for who can't see the
text clearly.
* [x] 10, The application is able to support the disability to read out the text using VoiceOver
controls.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src="/art/walkthrough.gif?raw=true" width="320px">


## Module structure

MVVM and Clean Architecture

- #### Base module

    [Base module](/base) composed of base classes for activity, fragment, dialog, recyclerview,... which are contains common actions.

- #### Data module

    [Data module](/data) composed of handle database queries and networking requests.



- #### Domain module

    [Domain module](/domain) composed of use cases of the app.


- #### App module
    [App module](/app) is the implementation of user interfaces on the application.
Based on mvvm architecture (view-databinding-viewmodel-model) with the repository pattern.

## Frameworks and Libraries
- [Retrofit2](https://github.com/square/retrofit) - constructing the REST API.
- [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - logs HTTP request and response data.
- Moshi for Json Parsing
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room database - Local database for the app with SQLite.
- [Hilt](https://dagger.dev/hilt) - constructing dependency injection framework based on compile-time.
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)
- [Lottie Android](https://github.com/airbnb/lottie-android)
- [RxBinding](https://github.com/JakeWharton/RxBinding)


## User Interface Design
Based on `Material` design & animations.

- Google Material Design.
- Ripple Effect.

## Notes
- The app is using [proguard](https://developer.android.com/studio/build/shrink-code) to avoid reverse engineer.
- Separate *dev* and *prod* with [build variants](https://developer.android.com/studio/build/build-variants).

## Run
- Android Studio 4.1.2 stable.

## License

    Copyright 2021 Hùng Nguyễn

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.