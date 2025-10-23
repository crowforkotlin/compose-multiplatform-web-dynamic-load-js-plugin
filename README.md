# Usage
1. `./gradlew plugin:jsBrowserDevelopmentWebpack`
2. ` python cors_server.py ./plugin/build/kotlin-webpack/js/developmentExecutable/`
3. `./gradlew composeApp:jsBrowserDevelopmentRun`
4. Press F12 and check the console.
5. Finally click compose button

- IP edit at ./composeApp/kotlin/org/example/project/App.kt 
- dev server edit at ./composeApp/build.gradle.kts
