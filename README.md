![Coverage](.github/badges/jacoco.svg)

# Calories

Simple Spring Boot application to help you manage your calorie intake.

# Setup

This project uses [Firebase](https://console.firebase.google.com/) to manage users.
To set it up yourself, can create a project in the `Firebase Console`.

* Login to Firebase Console
* Create new project
* In the side menu navigate to `Authentication` (and press `Enable`)
* Under the tab `Sign-in Method` add new provider `Google` and add the keys to environment variables (see
  `environment.env.example` for key name)
* Navigate to `project overview` -> `project settings` here you can find the `FIREBASE_API_KEY` under `Web API Key` add
  it to your environment variables
* Run project

# Tech & Libs

* [Kotlin](https://kotlinlang.org/)
* [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html)
* [SQLDelight](https://cashapp.github.io/sqldelight/2.0.2/)
* [Ktor Client](https://ktor.io/docs/client-create-new-application.html)
* [Postgresql](https://www.postgresql.org/)
* [MockK](https://mockk.io/) & [SpringMockK](https://github.com/Ninja-Squad/springmockk)
* [Firebase Admin](https://firebase.google.com/docs/admin/setup)
