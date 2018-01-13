package entity;

/**
 * 任务对象
 *
 * @author panda
 * @date 2017/10/28
 */
public class Task {

    private Integer id;

    private String url;

    private Integer status;

    public static TaskBuilder getBuilder() {
        return new TaskBuilder();
    }

    private Task(Integer id, String url, Integer status) {
        this.id = id;
        this.url = url;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static class TaskBuilder {

        private Integer id;
        private String url;
        private Integer status;

        public Task build() {
            return new Task(id, url, status);
        }

        public TaskBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public TaskBuilder setStatus(Integer status) {
            this.status = status;
            return this;
        }

    }

}
