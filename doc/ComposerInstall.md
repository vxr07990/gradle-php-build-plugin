# TASK: ComposerInstall

This task installs php libraries from your composer.json configuration

## Default Usage

```bash
./gradlew composerInstall
```

This will download and install composer in the current working directory. After composer.phar is installed, 
it will run ```php composer.phar install```.

## Customization

The task type ```org.swissphpfriends.gradle.task.ComposerInstall``` can be customized in your ```build.gradle```:

```java
task installDeps(type: org.swissphpfriends.gradle.task.ComposerInstall) {
    workingDirectory = "./any/subfolder"
    doNotUpdatePhar = true
}
```

### Parameters
- ```String workingDirectory``` - The directory where composer should be executed. Default: ```./```
- ```Boolean doNotUpdatePhar``` - Run ```composer.phar selfupdate``` before install. Default: ```true```
