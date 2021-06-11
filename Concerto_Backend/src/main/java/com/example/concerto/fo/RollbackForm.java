package com.example.concerto.fo;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 20:28 2021/6/11
 * @Version: 1.0$
 */

public class RollbackForm {
    long task_id;
    int task_version;

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public int getTask_version() {
        return task_version;
    }

    public void setTask_version(int task_version) {
        this.task_version = task_version;
    }
}
