# Project Config

Before you start, you should check the project's config:

## tools/code

### Code style config.

Code style is a consistent code specification, which can avoid a lot of problems. Therefore, we strongly recommend configuring code specification in IDE (such as Android Studio), or using code formatting tool (such as [ktlint]) to format code.

Here, we adopt the [nowinandroid][2] code style. The file is copied in the project with directory `tools/code/nowinandroid-codestyle.xml`. You can config in IDE.

### git hooks

+ install pre-push

```sh
# In any project root directory, run:
cd `git rev-parse --show-toplevel`
# install pre-push
 ./tools/code/setup.sh

```


## tools/Lint
> [Lint] is a static analysis tool that checks your Android project source code for potential bugs and optimization improvements for correctness, security, performance, usability, accessibility, and internationalization.

+ Add some custom lint rules and config report vendor infos.
+ In the CI progress, run some custom lint rules to auto detect some bugs.


[1]:https://developer.android.com/studio/write/lint
[2]:https://github.com/android/nowinandroid/blob/main/tools/nowinandroid-codestyle.xml


## tools/spotless
> [Spotless] is a code formatter for Java, Kotlin, Android, and Gradle projects. It can be used as a Gradle plugin or as a command line tool.

