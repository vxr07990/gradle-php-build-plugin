# Gradle php-build-plugin

***A gradle plugin for building php applications***

[![Build Status](https://travis-ci.org/swiss-php-friends/gradle-php-build-plugin.svg?branch=master)](https://travis-ci.org/swiss-php-friends/gradle-php-build-plugin)

## Featues

- Automatic composer installation and/or update
- *More features in development...*

## Contribution
If you found a bug or have a feature request, please [submit an issue](https://github.com/swiss-php-friends/gradle-php-build-plugin/issues) or propose a Pull Request. Please write tests for your contributions...

## Development

**Build JAR**
```bash
cd plugin
../gradlew clean uploadArchives
```

**Run Tests**
```bash
cd plugin
../gradlew clean test
```
The tests are also executed on every Pull Request and for every branch by [Travis CI](https://travis-ci.org/swiss-php-friends/gradle-php-build-plugin).

## License
This project, including all sourcecode and other documents is licensed under MIT. For details, see [LICENSE](LICENSE).
