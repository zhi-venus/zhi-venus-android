# Spotless 

[Spotless][1] is a code formatter for Java, Kotlin, Android, and Gradle projects. It can be used as a Gradle plugin or as a command line tool.

[Ktlint][2] is also triggered by spotless.

This module use nowinandroid's spotless config. 

## config
For ktlint, it's a config in the project root directory, `.editorconfig`
For spotless, it's a config in 'tools/spotless' directory.

## spotlessCheck

Use `spotlessCheck` to check code style.

```sh
./gradlew spotlessCheck --init-script tools/spotless/init.gradle.kts --no-configuration-cache
```

## spotlessApply

Use `spotlessApply` to format code style.
```sh
./gradlew spotlessApply --init-script tools/spotless/init.gradle.kts --no-configuration-cache
```


[1]:https://github.com/diffplug/spotless
[2]:https://github.com/pinterest/ktlint