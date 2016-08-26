# Fathom Android
A series of experiments to get Mozilla's [fathom][] running in the context of
native Android apps.

## Try it
Build the fathom code:

    npm run build

Build and install via gradle (assuming you have an Android device or emulator
connected):

    ./gradlew assembleDebug installDebug

Launch the application `FathomTest` on device! Alternatively, build and run via
Android Studio or another IDE.

[fathom]: https://github.com/mozilla/fathom
