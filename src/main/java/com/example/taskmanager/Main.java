package com.example.taskmanager;

import java.util.Scanner;

/**
 * Main class for the TaskManager application.
 */
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TaskManager!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. List all tasks");
            System.out.println("4. List completed tasks");
            System.out.println("5. List pending tasks");
            System.out.println("6. Mark task as completed");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task newTask = new Task(title, description);
                    taskManager.addTask(newTask);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.print("Enter task title to remove: ");
                    String removeTitle = scanner.nextLine();
                    Task taskToRemove = taskManager.findTaskByTitle(removeTitle);
                    if (taskToRemove != null) {
                        taskManager.removeTask(taskToRemove);
                        System.out.println("Task removed successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                    break;
                case 3:
                    System.out.println("All tasks:");
                    for (Task task : taskManager.getAllTasks()) {
                        System.out.println(task);
                    }
                    break;
                case 4:
                    System.out.println("Completed tasks:");
                    for (Task task : taskManager.getCompletedTasks()) {
                        System.out.println(task);
                    }
                    break;
                case 5:
                    System.out.println("Pending tasks:");
                    for (Task task : taskManager.getPendingTasks()) {
                        System.out.println(task);
                    }
                    break;
                case 6:
                    System.out.print("Enter task title to mark as completed: ");
                    String completeTitle = scanner.nextLine();
                    Task taskToComplete = taskManager.findTaskByTitle(completeTitle);
                    if (taskToComplete != null) {
                        taskToComplete.markCompleted();
                        System.out.println("Task marked as completed!");
                    } else {
                        System.out.println("Task not found!");
                    }
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}