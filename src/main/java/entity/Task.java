package entity;

/**
 * 任务对象
 *
 * @author xiepd
 * @date 2017/10/28
 */
public class Task {

    private String url;

    public static TaskBuilder getBuilder() {
        return new TaskBuilder();
    }

    private Task(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class TaskBuilder {
        private String url;

        public Task build() {
            return new Task(url);
        }

        public TaskBuilder setUrl(String url) {
            this.url = url;
            return this;
        }
    }

}
