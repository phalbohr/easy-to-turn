package com.example.taskmanager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Task.
 */
@DisplayName("Task Tests")
public class TaskTest {

    @Test
    @DisplayName("Task creation - should initialize with correct title and description")
    public void testTaskCreation() {
        // Given
        String title = "Test Task";
        String description = "This is a test task";

        // When
        Task task = new Task(title, description);

        // Then
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    @DisplayName("Mark task as completed - should change completion status")
    public void testMarkCompleted() {
        // Given
        Task task = new Task("Test Task", "Description");

        // When
        task.markCompleted();

        // Then
        assertTrue(task.isCompleted());
    }

    @Test
    @DisplayName("Mark task as not completed - should change completion status")
    public void testMarkNotCompleted() {
        // Given
        Task task = new Task("Test Task", "Description");
        task.markCompleted();

        // When
        task.markNotCompleted();

        // Then
        assertFalse(task.isCompleted());
    }

    @Test
    @DisplayName("Set title - should update task title")
    public void testSetTitle() {
        // Given
        Task task = new Task("Old Title", "Description");

        // When
        task.setTitle("New Title");

        // Then
        assertEquals("New Title", task.getTitle());
    }

    @Test
    @DisplayName("Set description - should update task description")
    public void testSetDescription() {
        // Given
        Task task = new Task("Title", "Old Description");

        // When
        task.setDescription("New Description");

        // Then
        assertEquals("New Description", task.getDescription());
    }
}