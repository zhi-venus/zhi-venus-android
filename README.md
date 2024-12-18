# zhi-venus-android

This is an android projects, used to build an app with littlest work


## Project tree overview

```sh
.
├── apps
│   ├── nia                     # app-nia
│   │   ├── app
│   │   ├── feature
│   │   │   ├──foryou
│   │   │   ├──bookmarks
│   │   │   ├──interests
│   │   │   ├──search
│   │   │   ├──settings
│   │   │   └──topic
│   │   │
│   │   ├── ui                 # components shared by multi features.
│   │   ├── design             # app-wide themes and components.
│   │   │
│   │   ├── domain             # mediator layer.
│   │   ├── model              # data model shared by ui and data layers.
│   │   │
│   │   ├── data
│   │   ├── data-test
│   │   ├── database
│   │   ├── datastore
│   │   ├── datastore-proto
│   │   ├── datastore-test
│   │   ├── network            # network layer.
│   │   ├── notifictions
│   │   │
│   │   ├── sync               # sync network data to local.
│   │   ├── sync-test
│   │   │
│   │   ├── testing            # test suppored, with data-layers.
│   │   │
│   │   ├── installedProjects.gradle.kts
│   │   ├── docs
    │   │   ├── app_dep_graph.svg
│   │   └── ...
│   └── ...
│
│
├── kernel
│   ├── core                    # core module
│   ├── analytics               # analytics info report module
│   ├── performance             # performance monitor module
│   ├── hilt                    # hilt extensions.
│   │   ├── coroutine           # hilt coroutine extensions.
│   │   ├── lifecycle           # hilt lifecycle extensions.
│   │   └── README.md
│   ├── hilt-test               # test for hilt testing.
│   ├── network-core
│   ├── network-monitor
│   ├── network-monitor-test
│   ├── coil                    # image loading library.
│   └── ...
│
│
├── testing
│   ├── core                    # core module
│   ├── hilt-workaround
│   ├── screenshot              # screenshot test support.
│   └── ...
│
│
├── .gitmodules                 # git submodule config.
├── .gitignore                  # git ignore file.
│
│
├── tools
│   ├── code
│   │   └── venus-codestyle.xml # code style config.
│   ├── config
│   ├── dep-graph               # module depencency graph.
│   ├── lint
│   │   └── README.md
│   ├── lint                    # lint module.
│   └── spotless
│       ├── init.gradle.kts      # spotless & klint
│       └── README.md
├── lint.xml                     # lint global config.
├── .editorconfig                # spotless ktlint config.
├── .editconfig                  # code style project config.
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
├── settings.gradle.gradle.kts
├── local.properties
│ 
│
├── LICENSE
├── docs
│   └── project-config.md       # project config guide.
│
└── README.md
```
