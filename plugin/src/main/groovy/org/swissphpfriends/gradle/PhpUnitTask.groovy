package org.swissphpfriends.gradle

import org.gradle.api.tasks.TaskAction

class PhpUnitTask extends AbstractBaseTask {

    @TaskAction
    def phpunit() {

        this.checkPhpInstallation()

        String toolBinary = this.getToolBinaryFromComposerVendorOrSystemPath('phpunit');

        // install composer dependencies
        this.printBuildMessage('running phpunit tests...')
        Process phpUnitProcess = new ProcessBuilder(toolBinary, './')
                .redirectErrorStream(true)
                .start()
        phpUnitProcess.inputStream.eachLine { println it }
        phpUnitProcess.waitFor()

        //TODO: Configuration...

        if (phpUnitProcess.exitValue()) {
            //throw new TaskExecutionException(this, new Throwable('command "php composer.phar install" failed!'))
            println "FAILED -.-"
        }
    }
}