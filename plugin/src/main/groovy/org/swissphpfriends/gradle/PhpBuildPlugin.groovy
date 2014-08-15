package org.swissphpfriends.gradle;

import org.gradle.api.*;
import org.swissphpfriends.gradle.helper.*;

class PhpBuildPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task('composerInstall', type: ComposerInstallTask)
        project.task('phpunit', type: PhpUnitTask)
    }
}