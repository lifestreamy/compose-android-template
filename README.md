
# compose_android_template

---

### This is a general-purpose, Kotlin, Jetpack Compose, multi-modular template repository, aimed to make new project creation much easier and faster, reducing the tedious setting-up work.

### Always open for contribution and feedback!

---

# Setting up
For convenience, everything related to the project name was declared as `compose_android_template`,
allowing to easily replace it with your own name using a single command.

The default project name is `compose_android_template`, 
the default package structure is `com.github.compose_android_template`.

The convention plugins' names also start with `compose_android_template`, as do the id's that they 
are registered with in the `build-logic/convention/build.gradle.kts`.

The same goes for the app name in the manifest, resources, `settings.gradle.kts` and the theme() in Theme.kt.

### In Android Studio: 
 - Use `ctrl + shift + R` *(Replace in Files)*
 - Enter in the first *"Search"* field `compose_android_template`
 - In the second field enter the name of your new project
 - Select `In Project` on the toolbar and click `Replace All`
 - All the references, including those in the libs.versions.toml, will get replaced with your
project's name, though you will have to manually change the directories' names in the file explorer
 - Repeat the same with `compose.android.template`, because in libs.toml, even if the convention plugins' names contain underscores `_`, those are referenced as periods `.` in `build.gradle.kts` files inside `plugins {}`

# Features:
 - Multi-modular approach following Google's guidelines
 - build/convention modules are included, alongside:
   - Common convention plugins for easy module configuration, heavily reducing boilerplate and setup time, promoting reusability
     - Android plugins: AppCompose, App, AppFlavors, Feature, Hilt, LibCompose, Lib, Room, Test
     - Kotlin: Library Convention Plugin
   - Common configuration code, including:
     - Tasks
     - Build types
     - Flavor types
 - Pre-populated libs.versions.toml, with commonly used versions, plugins and libraries.
   - Feel free to open pull requests to add new useful entries or correct the existing ones. 
   - The file is lightweight, and the unused deps do not slow the project down
   - The bigger, "ultimate" `libs.versions.toml` version will be available in a separate repository
 - _Creative Commons Zero_ license is included so you can freely use the project
 - Default `docs` folder for documentation and images, that could be referenced in your README
 - Pre-populated .gitignore with the most common exclusions with the help of www.toptal.com
 - Tweaked gradle.properties files for optimised builds and convenience, including a separate one for the `build-logic`
 - `proguard-rules.pro` for the `:app` module is populated with code necessary for avoiding obfuscation and shrinking interfering with Room, Retrofit, Parcelable, Gson, Serialization, Reflect, libraries and plugins, etc.
 - Utility plugins for the project modules' diagram generation 
 - Comments, documentation, clear names and code style to help understand the structure, remember better which part does what and ease the burden of configuring


## TODO & Plans : 
 - Include the https://github.com/littlerobots/version-catalog-update-plugin
 - A script or another measure for automatic swap of the "compose_android_template" for your custom project name everywhere, including directories

# Contributions
 Are always welcome! Fork, create pull requests, open issues, etc.

## Simple guidelines: 
 - Be polite, please
 - Better suggest small corrections in the issues first
 - You are free to do whatever you want with the code, in compliance with the CC0 license.
 - Mentioning this original repo would be appreciated but is not required


Hopefully, this template will come in handy for beginners and the more experienced alike.

- Tim K.