# TASK: PhpUnit

This task runs phpunit tests

## Default Usage

```bash
./gradlew phpunit
```

This will run phpunit tests by executing ```phpunit ./``` without any other parameters

## Customization

The task type ```org.swissphpfriends.gradle.task.PhpUnit``` can be customized in your ```build.gradle```:

```java
task test(type: org.swissphpfriends.gradle.task.PhpUnit) {
    path = "./src"
    verbose = true
    outputTap = true
    outputTestdox = false
    groupIncludeMode = true
    groupExcludeMode = false
    groups = "unit"
    configurationFile = "/app/phpunit.xml"
    bootstrapFile = "test/bootstrap.php"
    coverageFormat = "html"
    coverageTarget = "build/coverage"
}
```

### Parameters
##### path
- *Description:* THe path to the tests
- *Type:* String
- *Default:* ```./```

##### verbose
- *Description:* Verbosity setting
- *Type:* Boolean
- *Default:* ```false```

##### outputTap
- *Description:* Script output in TAP format
- *Type:* Boolean
- *Default:* ```false```
- *Cannot be used with:* outputTestdox

##### outputTestdox
- *Description:* Script output in Testdox format
- *Type:* Boolean
- *Default:* ```false```
- *Cannot be used with:* outputTap

##### groupIncludeMode
- *Description:* Includes only configured test groups
- *Type:* Boolean
- *Default:* ```false```
- *Cannot be used with:* groupExcludeMode
- *Depends on:* groups

##### groupExcludeMode
- *Description:* Excludes configured test groups
- *Type:* Boolean
- *Default:* ```false```
- *Cannot be used with* groupIncludeMode
- *Depends on:* groups

##### groups
- *Description:* Groups to include/exclude
- *Type:* String
- *Default:* ```empty String```

##### configurationFile
- *Description:* Path to phpunit.xml
- *Type:* String
- *Default:* ```empty String```

##### bootstrapFile
- *Description:* Path to bootstrap PHP script
- *Type:* String
- *Default:* ```empty String```

##### coverageFormat
- *Description:* Format for coverage report
- *Type:* String
- *Default:* ```none```
- *Values:* ```clover|crap4j|html|php|text```
- *Depends on:* coverageTarget

##### coverageTarget
- *Description:* Target directory/file for coverage report
- *Type:* String
- *Default:* ```empty String```
