package com.example.taskmanager;

/**
 * Represents a task in the task manager.
 */
public class Task {
    private String title;
    private String description;
    private boolean completed;

    /**
     * Constructs a new Task with the specified title and description.
     *
     * @param title       the title of the task
     * @param description the description of the task
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    /**
     * Gets the title of the task.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotCompleted() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}