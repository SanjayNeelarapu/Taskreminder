## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Task Reminder App

This is a Java-based task reminder application with persistent storage and a Swing-based user interface. It allows users to manage their tasks efficiently by adding, deleting, and viewing tasks.

## Features
- **Add Tasks**: Specify the task name and due date.
- **Delete Tasks**: Remove tasks by their name.
- **View Tasks**: List all saved tasks.
- **Persistent Storage**: Tasks are stored in a file and reloaded on application start.
- **Automatic Reminders**: Get notified when a task is due.

## Requirements
- Java JDK 8 or higher
- IDE (e.g., IntelliJ, Eclipse) or terminal for running Java applications

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/TaskReminder.git
   cd TaskReminder
   ```

2. **Compile the Source Code**:
   ```bash
   javac -d bin src/TaskReminder/*.java
   ```

3. **Run the Application**:
   ```bash
   java -cp bin TaskReminder.App
   ```

## Project Structure
```
TaskReminder/
├── src/
│   ├── TaskReminderApp.java
│   ├── Task.java
│   ├── App.java
├── storage/
│   ├── tasksaver.txt
├── README.md
├── pom.xml (if using Maven)
├── .gitignore
```

## How It Works
- When the application starts, tasks are loaded from `tasksaver.txt` if it exists.
- Adding a task schedules a reminder at the specified time.
- Tasks can be deleted, and the changes are saved immediately.

## Future Enhancements
- Export tasks to external formats (e.g., CSV, JSON).
- Add support for recurring tasks.
- Enhance the user interface with additional features and styling.

Feel free to fork and contribute to this project! 😊


