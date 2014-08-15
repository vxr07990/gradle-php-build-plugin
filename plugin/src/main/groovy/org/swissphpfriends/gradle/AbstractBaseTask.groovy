package org.swissphpfriends.gradle

import org.gradle.api.DefaultTask
import org.swissphpfriends.gradle.helper.SystemDependencies

abstract class AbstractBaseTask extends DefaultTask {

    Boolean verbose = false

    protected SystemDependencies systemDependencies = new SystemDependencies();

    protected void checkPhpInstallation() {
        if(!this.systemDependencies.hasExecutableInPath('php')) {
            throw new RuntimeException('PHP seems not installed or not available in PATH!')
        }
    }

    protected void printBuildMessage(String msg) {
        println("\n## " + msg);
    }

    protected String getToolBinaryFromComposerVendorOrSystemPath(String binaryName) {
        if(this.hasComposerVendorBinary(binaryName)) {
            return './vendor/bin/' + binaryName;
        }

        if(this.systemDependencies.hasExecutableInPath(binaryName)) {
            return this.systemDependencies.getExecutableFromPath(binaryName);
        }

        throw new FileNotFoundException(
                'Binary "' + binaryName + '" is not available in vendor/bin nor in system PATH! Please install it...'
        );
    }

    private Boolean hasComposerVendorBinary(String binaryName) {
        def file = new File( 'vendor/bin' + binaryName );

        if(!file.isFile()) {
            return false;
        }

        if(!file.canExecute()) {
            file.setExecutable(true);
        }

        return true;
    }
}
