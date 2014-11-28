package org.swissphpfriends.gradle.installer;

import org.junit.*;
import java.io.File;

import static org.junit.Assert.*;

public class ComposerInstallerTest
{
    private ComposerInstaller installer;

    @Test
    public void testIsInstalled()
    {
        assertFalse("Assert composer is absent", installer.isInstalled());
    }

    @Test
    public void testInstall()
    {
        assertTrue(installer.install());

        assertTrue("Assert composer is present after installation", installer.isInstalled());
    }

    @Test
    public void testUpdate()
    {
        assertFalse("Updater must return false if composer binary is absent", installer.update());

        installer.install();

        assertTrue("Update is successful", installer.update());
        assertTrue("phar is present after update", installer.isInstalled());
    }

    @After
    public void cleanup()
    {
        File composerBin = new File("../user/composer.phar");

        if (composerBin.exists()) {
            composerBin.delete();
        }
    }

    @Before
    public void setup()
    {
        File workingDir = new File("../user");
        this.installer = new ComposerInstaller(workingDir.getAbsolutePath());
    }
}


