# Lint

[Lint][1] is a static analysis tool that checks your Android project source code for potential bugs and optimization improvements for correctness, security, performance, usability, accessibility, and internationalization.

> [!NOTE]
> This Project initial copied from [nowinandroid]. And add some more customer lint rules.


## Custom Rules

1. A detector that checks for common patterns in naming the test methods:

+ detectPrefix removes unnecessary `test` prefix in all unit test.
+ detectFormat Checks the `given_when_then` format of Android instrumented tests (backticks are not supported).


## Manual Run
In the project root directory, run the following command:
```sh
./gradlew lint
```

[1]:https://developer.android.com/studio/write/lint