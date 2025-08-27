package ru.mentee.power.methods.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests for TaskManager class
 */
class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();

        taskManager.addTask("Urgent task", "Complete as soon as possible", createDate(2023, 5, 15), Task.Priority.HIGH);
        taskManager.addTask("Regular task", "Within a week", createDate(2023, 6, 1), Task.Priority.MEDIUM);
        taskManager.addTask("Non-urgent task", "When there is time", createDate(2023, 7, 1), Task.Priority.LOW);
        taskManager.addTask("Task without description");
    }

    /**
     * Test for adding a task
     */
    @Test
    void testAddTask() {
        // TODO: Implement test to check adding a task
        // Add a task and verify its parameters

        assertThat(taskManager.getTaskById(1).getTitle()).isEqualTo("Urgent task");
        assertThat(taskManager.getTaskById(1).getDescription()).isEqualTo("Complete as soon as possible");
        assertThat(taskManager.getTaskById(3).getDescription()).isEqualTo("When there is time");
        assertThat(taskManager.getTaskById(2).getPriority()).isEqualTo(Task.Priority.MEDIUM);
    }

    /**
     * Test for getting a task by ID
     */
    @Test
    void testGetTaskById() {
        // TODO: Implement test to check getting a task by ID
        // Check edge case when task is not in the list
        assertThat(taskManager.getTaskById(2).getDescription()).isEqualTo("Within a week");
        assertThat(taskManager.getTaskById(7)).isEqualTo(null);
    }

    /**
     * Test for getting tasks by priority
     */
    @Test
    void testGetTasksByPriority() {
        // TODO: Implement test to check filtering by priority
        List<Task> highPriorityTasks = taskManager.getTasksByPriority(Task.Priority.HIGH);
        assertThat(highPriorityTasks).containsExactly(taskManager.getTaskById(1));

        List<Task> mediumPriorityTasks = taskManager.getTasksByPriority(Task.Priority.MEDIUM);
        assertThat(mediumPriorityTasks).containsExactly(taskManager.getTaskById(2));

        List<Task> lowPriorityTasks = taskManager.getTasksByPriority(Task.Priority.LOW);
        assertThat(lowPriorityTasks).containsExactly(taskManager.getTaskById(3));
    }

    /**
     * Test for searching tasks
     */
    @Test
    void testSearchTasks() {
        // TODO: Implement test to check searching tasks,
        // Check edge case when task is not in the list
        assertThat(taskManager.searchTasks("Urgent task")).containsExactly(taskManager.getTaskById(1));
        assertThat(taskManager.searchTasks("hguierh")).isEmpty();
    }

    /**
     * Test for sorting tasks by priority
     */
    @Test
    void testSortTasksByPriority() {
        // TODO: Implement test to check sorting by priority
        List<Task> sortedTasks = taskManager.sortTasksByPriority();

        assertThat(sortedTasks.get(0).getPriority()).isEqualTo(Task.Priority.HIGH);
        assertThat(sortedTasks.get(1).getPriority()).isEqualTo(Task.Priority.MEDIUM);
        assertThat(sortedTasks.get(2).getPriority()).isEqualTo(Task.Priority.LOW);

    }

    /**
     * Helper method for creating a date
     */
    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
}
