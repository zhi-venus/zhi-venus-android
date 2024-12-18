# zhi-venus-android

This is an android projects, used to build an app with littlest work


## Project tree overview

```sh
.
│
├── kernel
│
├── .gitmodules                 # git submodule config.
├── .gitignore                  # git ignore file.
│
│
├── tools
│   ├── code
│   │   └── venus-codestyle.xml # code style config.
│   ├── lint
│   │   └── README.md
│   └── spotless
│       ├── init.gradle.kts      # spotless & klint
│       └── README.md
├── lint.xml                     # lint global config.
├── .editorconfig                # spotless ktlint config.
│
│
│
├── gradle
│   ├── wrapper
│   └── libs.versions.toml       # gradle version config.
├── gradle.properties
├── gradlew
├── gradlew.bat
│
├── build-logic
│   ├── README.md
│   └── convention              # convention config, like version, etc.
├── build.gradle.kts
├── settings.gradle.kts
├── local.properties
│
│ 
│
├── LICENSE
├── docs
│   └── project-config.md       # project config guide.
│
└── README.md
```