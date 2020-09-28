import javax.persistence.*;

/**
 * @author Andre Fernandez
 *
 * Object class for a TaskItem
 *
 * This is a piece of data that can hold the text and date of a task
 */

@Entity
@Table(name="task_item")
public class TaskItem {

    @Id
    @Column(name="task")
    String task;

    @Column(name = "date")
    String date;

    /**
     * Default constructor for a TaskItem
     * @param task
     * @param date
     */
    public TaskItem(String task, String date) {
        this.task = task;
        this.date = date;
    } // end TaskItem()

    public TaskItem() {
    }

    /**
     * Returns the task of a TaskItem
     * @return
     */
    public String getTask() { return task; }

    /**
     * Sets the task of a TaskItem
     * @param task
     */
    public void setTask(String task) { this.task = task; }

    /**
     * Returns the date of a TaskItem
     * @return
     */
    public String getDate() { return date; }

    /**
     * Sets the date of a TaskItem
     * @param date
     */
    public void setDate(String date) { this.date = date; }

    /**
     * Returns the data of a TaskItem as a String
     * @return
     */
    public String toString() {
        String result = "Task: " + task + " | Date: " + date;
        return result;
    } // end toString()

} // end Task Item
