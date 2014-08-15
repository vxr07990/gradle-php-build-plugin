package org.swissphpfriends.gradle.task

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import org.swissphpfriends.gradle.task.ComposerInstall

import static org.junit.Assert.*

class ComposerInstallTest {

    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('composerInstall', type: ComposerInstall)
        assertTrue(task instanceof ComposerInstall)
    }
}
