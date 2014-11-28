package org.swissphpfriends.gradle.task

import org.gradle.api.tasks.TaskAction
import org.swissphpfriends.gradle.AbstractBaseTask
import org.swissphpfriends.gradle.installer.ComposerInstaller

class ComposerInstall extends AbstractBaseTask {

    Boolean doNotUpdatePhar = false

    @TaskAction
    def run() {

        this.checkPhpInstallation();

        ComposerInstaller installer = new ComposerInstaller(this.workingDirectory);

        if (!installer.isInstalled()) {
            installer.install();

        } else if (this.doNotUpdatePhar.equals(false)) {
            installer.update();
        }

        // install composer dependencies
        this.printBuildMessage('installing dependencies using composer...');

        Process composerInstallProcess = new ProcessBuilder("php", "composer.phar", "install")
                .directory(new File(this.workingDirectory))
                .redirectErrorStream(true)
                .start()
        composerInstallProcess.inputStream.eachLine { println it }
        composerInstallProcess.waitFor()
    }
}