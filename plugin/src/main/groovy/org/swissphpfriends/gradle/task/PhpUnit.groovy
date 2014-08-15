package org.swissphpfriends.gradle.task

import org.gradle.api.tasks.TaskAction
import org.swissphpfriends.gradle.AbstractBaseTask

class PhpUnit extends AbstractBaseTask {

    String path = './'

    Boolean outputTap = false

    Boolean groupIncludeMode = false

    Boolean groupExcludeMode = false

    String groups = ''

    @TaskAction
    def phpunit() {

        this.checkPhpInstallation()

        String toolBinary = this.getToolBinaryFromComposerVendorOrSystemPath('phpunit');
        List<String> command = new ArrayList<String>();
        command.add(toolBinary);

        if(this.verbose) {
            command.add('-v');
        };

        if(this.groupIncludeMode) {
            command.add('--groups ' + this.groups + ' ');
        } else if (this.groupExcludeMode){
            command.add('--exclude-group ' + this.groups + ' ');
        }

        if(this.outputTap) {
            command.add('--tap');
        }

        command.add(this.path);

        this.printBuildMessage('running phpunit tests...')
        if(this.verbose) {
            println('RUNNING: ' + command.toString());
        }

        Process phpUnitProcess = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()

        phpUnitProcess.inputStream.eachLine { println it }
        phpUnitProcess.waitFor()

        if (phpUnitProcess.exitValue()) {
            //throw new TaskExecutionException(this, new Throwable('command "php composer.phar install" failed!'))
            println "FAILED -.-"
        }
    }
}