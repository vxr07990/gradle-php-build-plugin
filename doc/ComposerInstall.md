# TASK: ComposerInstall

This task installs php libraries from your composer.json configuration

## Default Usage

```bash
./gradlew composerInstall
```

This will download and install composer in the current working directory. After composer.phar is installed, 
it will run ```php composer.phar install```.

## Customization

```java
task install(type: org.swissphpfriends.gradle.task.ComposerInstall) {
    workingDirectory = "./any/subfolder"
    doNotUpdatePhar = true
}
```
