package TaskReminder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    final String name;
    private final LocalDateTime time;

    public Task(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Task: " + name + ", Time: " + time.format(formatter);
    }
}
