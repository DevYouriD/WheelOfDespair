
# WheelOfDespair

## Description
I started this project because I wanted to learn the basics of Kotlin and Android App development 
in a fun way.

I got the idea to make a "wheel of fortune" game because I saw a similar wheel in a movie and began
to wonder how I would create something like similar in code.

A few commits later and now we know. Some math to get the wheel working and a lot of styling to
make the Android application look nice.

## Tools/ Technologies used
- Kotlin (and a little bit of java)
- Gradle build tool
- Trello
- GitHub
- SQLite
- Pen and paper (for designs)
- [PlantUML](https://plantuml.com/en-dark/)
- Junit

## Example Project Structure
```text
src
├── main
│   ├── java
│   │   └── com.example.chatapp
│   │       ├── ui
│   │       │   ├── activity
│   │       │   ├── fragment
│   │       │   └── compose
│   │       ├── data
│   │       │   ├── model
│   │       │   ├── repository
│   │       │   └── local
│   │       ├── network
│   │       ├── di
│   │       ├── util
│   │       ├── service
│   │       └── ChatApp.kt
│   ├── res
│   │   ├── layout
│   │   ├── values
│   │   ├── drawable
│   │   └── mipmap
│   ├── assets
│   ├── AndroidManifest.xml
│   └── kotlin
└── test
    └── java
        └── com.example.chatapp
            ├── ui
            ├── data
            └── ChatAppTests.kt
```

## TODO
- [ ] Wheel
  - [x] Create wheel page
  - [x] Figure out wheel logic
  - [x] Display list items within wheel
  - [x] Create popup for "winner"
  - [ ] Create confetti animation?
  - [ ] Set section limit
- [ ] Storage
  - [x] Create input/ storage page
  - [x] Handle user input
  - [x] Set up database
  - [x] Save user input in db
  - [x] Make inserted data editable
  - [ ] Set character limit for user input
- [ ] Design
  - [x] Select app color palette theming
  - [x] Find/ create custom attributes like button icons, popup windows, etc
  - [x] Give the wheel a default color or value in case input equals 0
  - [ ] Make attributes look nicer (visual reformatting)
- [ ] Other
  - [x] Handle navigation between pages
  - [ ] Clean up code
    - [ ] Implement code quality plugins (e.g. checkstyle, pmd, spotbugs)
    - [ ] Scan project with sonarlint/ sonarqube

<br>

![Funny cat](https://preview.redd.it/pray-for-my-cat-he-has-a-serious-gambling-addiction-v0-1w2102sqgl2a1.jpg?width=640&crop=smart&auto=webp&s=abd9d74fc6618b1a130f4575b9b6d5acd1a3b720)