package entity.enums;

/**
 * 任务状态枚举类
 *
 * @author panda
 * @date 2018/1/14
 */
public enum TaskStatusEnum {

    NOT_RUNNING(0),
    RUNNING(1),
    RUNNED(2),
    ERROR(3);

    private int value;

    TaskStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
