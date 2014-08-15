package org.swissphpfriends.gradle.helper;

class ComposerHelper {

    /**
     * @return boolean True if composer.phar is available and working
     */
    public boolean isAvailable() {
        def composerVersionProcess = new ProcessBuilder('php', 'composer.phar', '--version')
                .redirectErrorStream(true)
                .start()

        composerVersionProcess.waitFor();

        if (composerVersionProcess.exitValue()) {
            return false;
        }

        return true;
    }

    public void installPhar() {
        println "\n## composer.phar not presend in current directory. Installing..."

        // download installer
        def address = 'https://getcomposer.org/installer'
        def file = new FileOutputStream(address.tokenize("/")[-1])
        def out = new BufferedOutputStream(file)
        out << new URL(address).openStream()
        out.close()

        // install composer.phar
        def installComposerProcess = new ProcessBuilder('php', './installer')
                .redirectErrorStream(true)
                .start()

        installComposerProcess.inputStream.eachLine { println it }
        installComposerProcess.waitFor();

        if (installComposerProcess.exitValue()) {
            println "Composer installation failed!"
        }
    }

    public void checkForSelfUpdate() {
        println "\n## composer.phar already present. Checking for update..."

        Process composerSelfupdateProcess = new ProcessBuilder("php", "composer.phar", "selfupdate")
                .redirectErrorStream(true)
                .start()
        composerSelfupdateProcess.inputStream.eachLine { println it }
        composerSelfupdateProcess.waitFor()

        if (process.exitValue()) {
            println "Composer installation failed!"
        }
    }

}