package org.swissphpfriends.gradle;

import org.gradle.api.*;
import org.swissphpfriends.gradle.helper.*;

class PhpBuildPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.task('composerInstall') << {

            ComposerHelper composerHelper = new ComposerHelper();

            def composerBinary = new File("composer.phar")

            if (!composerHelper.isAvailable()) {
                composerHelper.installPhar();

            } else {
                composerHelper.checkForSelfUpdate();
            }

            // install composer dependencies
            println "\n## installing dependencies using composer..."
            Process composerInstallProcess = new ProcessBuilder("php", "composer.phar", "install")
                .redirectErrorStream(true)
                .start()
            composerInstallProcess.inputStream.eachLine { println it }
            composerInstallProcess.waitFor()

            if (composerInstallProcess.exitValue()) {
                //throw new TaskExecutionException(this, new Throwable('command "php composer.phar install" failed!'))
                println "FAILED -.-"
            }
        }
    }
}