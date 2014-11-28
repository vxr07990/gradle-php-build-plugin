package org.swissphpfriends.gradle.installer;

import org.swissphpfriends.gradle.helper.ProcessOutput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ComposerInstaller implements Installer
{
    private static final String INSTALLER_FILE_NAME = "installer";

    private static final String INSTALLER_DOWNLOAD_URL = "https://getcomposer.org/installer";

    private File workingDirectory = new File("./");

    private Path installerDownloadPath;

    private ProcessOutput processOutput = new ProcessOutput();

    public ComposerInstaller()
    {
        this.installerDownloadPath = Paths.get(this.workingDirectory.getAbsolutePath());
    }

    /**
     * @param workingDirectory The composer working directory (where your composer.json is located)
     */
    public ComposerInstaller(String workingDirectory)
    {
        this.workingDirectory = new File(workingDirectory);
        this.installerDownloadPath = Paths.get(this.workingDirectory.getAbsolutePath() + File.separator + INSTALLER_FILE_NAME);
    }

    @Override
    public Boolean install()
    {
        try {
            this.downloadInstaller();
            this.installPhar();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.removeInstaller();
        }

        return true;
    }

    @Override
    public Boolean update()
    {
        try {
            if (!this.isInstalled()) {
                return false;
            }

            Process composerSelfupdateProcess = new ProcessBuilder("php", "composer.phar", "selfupdate")
                .directory(this.workingDirectory)
                .redirectErrorStream(true)
                .start();

            composerSelfupdateProcess.waitFor();

            return (composerSelfupdateProcess.exitValue() == 0);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean isInstalled()
    {
        File composerBin = new File(this.workingDirectory.getAbsolutePath() + File.separator + "composer.phar");

        if (!composerBin.exists() || !composerBin.canRead()) {
            return false;
        }

        try {
            Process composerVersionProcess = new ProcessBuilder("php", composerBin.getAbsolutePath(), "--version")
                .directory(this.workingDirectory)
                .redirectErrorStream(true)
                .start();

            composerVersionProcess.waitFor();

            return (composerVersionProcess.exitValue() == 0);
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
    }

    private void downloadInstaller() throws IOException
    {
        URL website = new URL(INSTALLER_DOWNLOAD_URL);
        Files.copy(
            website.openStream(),
            this.installerDownloadPath,
            StandardCopyOption.REPLACE_EXISTING
        );
    }

    private void installPhar() throws IOException, InterruptedException
    {
        Process installComposerProcess = new ProcessBuilder("php", this.installerDownloadPath.toString())
            .directory(this.workingDirectory)
            .redirectErrorStream(true)
            .start();

        this.processOutput.output(installComposerProcess);

        installComposerProcess.waitFor();
    }

    private Boolean removeInstaller()
    {
        File installer = new File(this.installerDownloadPath.toString());

        return !installer.exists() || installer.delete();
    }
}
