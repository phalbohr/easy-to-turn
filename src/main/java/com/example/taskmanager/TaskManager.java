package com.example.taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks.
 */
public class TaskManager {
    private List<Task> tasks;

    /**
     * Constructs a new TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the manager.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the manager.
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Gets all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets all completed tasks.
     *
     * @return a list of completed tasks
     */
    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }

    /**
     * Gets all pending tasks.
     *
     * @return a list of pending tasks
     */
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }

    /**
     * Finds a task by title.
     *
     * @param title the title to search for
     * @return the task if found, null otherwise
     */
    public Task findTaskByTitle(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }
}