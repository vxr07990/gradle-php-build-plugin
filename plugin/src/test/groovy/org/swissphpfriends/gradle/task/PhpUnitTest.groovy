package org.swissphpfriends.gradle.task

import org.gradle.api.*
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.*

class PhpUnitTest {

    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('phpunit', type: PhpUnit)
        assertTrue(task instanceof PhpUnit)
    }

    @Test
    public void supportsVerbosityFlag() {
        Task phpUnitTask = this.getTaskInstance();

        assertTrue(phpUnitTask.hasProperty('verbose'))
        assertEquals(false,phpUnitTask.getProperty('verbose'))


        ArrayList<String> command1 = phpUnitTask.buildCommand();
        assertEquals(false,command1.contains('-v'));

        phpUnitTask.setProperty('verbose', true);

        ArrayList<String> command2 = phpUnitTask.buildCommand();
        assertEquals(true,command2.contains('-v'));
    }

    private Task getTaskInstance() {
        Project project = ProjectBuilder.builder().build()
        return project.task('phpunit', type: PhpUnit)
    }
}
