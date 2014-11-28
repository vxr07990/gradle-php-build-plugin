package org.swissphpfriends.gradle.helper;

/**
 * Class to write process output to the console
 */
public class ProcessOutput
{
    public void output(Process process)
    {
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");

        outputGobbler.start();
        errorGobbler.start();
    }
}
