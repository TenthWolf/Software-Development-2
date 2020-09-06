/**
 * @author Andre Fernandez
 *
 * Object class for a TaskItem
 *
 * This is a piece of data that can hold the text and date of a task
 */
public class TaskItem {
    String task;
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
        return "TaskItem{" +
                "task='" + task + '\'' +
                ", date='" + date + '\'' +
                '}';
    } // end toString()

} // end Task Item
