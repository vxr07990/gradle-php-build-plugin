package org.swissphpfriends.gradle.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class PhpUnitTest {

    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('phpunit', type: PhpUnit)
        assertTrue(task instanceof PhpUnit)
    }
}
