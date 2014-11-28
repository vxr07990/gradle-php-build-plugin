package org.swissphpfriends.gradle.installer;

public interface Installer
{
    /**
     * Installs a dependency
     *
     * @return True if successful, false otherwise
     */
    public Boolean install();

    /**
     * Updates a dependency
     *
     * @return True if successful, false otherwise
     */
    public Boolean update();

    /**
     * CHecks if the needed stuff is installed
     *
     * @return True if successful, false otherwise
     */
    public Boolean isInstalled();
}
