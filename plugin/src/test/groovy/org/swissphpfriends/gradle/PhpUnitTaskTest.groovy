package org.swissphpfriends.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class PhpUnitTaskTest {

    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('phpunit', type: PhpUnitTask)
        assertTrue(task instanceof PhpUnitTask)
    }
}
