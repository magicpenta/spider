package factory;

import entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugins.AbstractPlugin;
import plugins.Plugin;
import util.CommonUtil;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件工厂
 *
 * @author panda
 * @date 2017/12/01
 */
public class PluginFactory {

    private static final Logger logger = LoggerFactory.getLogger(PluginFactory.class);

    private static final PluginFactory factory = new PluginFactory();

    private List<Class<?>> classList = new ArrayList<Class<?>>();

    private Map<String, String> pluginMapping = new HashMap<String, String>();

    private PluginFactory() {
        scanPackage("plugins");
        if (classList.size() > 0) {
            initPluginMapping();
        }
    }

    public static PluginFactory getInstance() {
        return factory;
    }

    /**
     * 扫描包、子包
     *
     * @param packageName
     */
    private void scanPackage(String packageName) {
        try {
            String path = getSrcPath() + File.separator + changePackageNameToPath(packageName);
            File dir = new File(path);
            File[] files = dir.listFiles();

            if (files == null) {
                logger.warn("包名不存在！");
                return;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    scanPackage(packageName + "." + file.getName());
                } else {
                    Class clazz = Class.forName(packageName + "." + file.getName().split("\\.")[0]);
                    classList.add(clazz);
                }
            }
        } catch (Exception e) {
            logger.error("扫描包出现异常：", e);
        }
    }

    /**
     * 获取根路径
     *
     * @return
     */
    private String getSrcPath() {
        return System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "java";
    }

    /**
     * 将包名转换为路径格式
     *
     * @param packageName
     * @return
     */
    private String changePackageNameToPath(String packageName) {
        return packageName.replaceAll("\\.", File.separator);
    }

    /**
     * 初始化插件容器
     */
    private void initPluginMapping() {
        for (Class<?> clazz : classList) {
            Annotation annotation = clazz.getAnnotation(Plugin.class);
            if (annotation != null) {
                pluginMapping.put(((Plugin) annotation).value(), clazz.getName());
            }
        }
    }

    /**
     * 通过反射实例化插件对象
     * @param task
     * @return
     */
    public AbstractPlugin getPlugin(Task task) {

        if (task == null || task.getUrl() == null) {
            logger.warn("非法的任务！");
            return null;
        }

        if (pluginMapping.size() == 0) {
            logger.warn("当前包中不存在插件！");
            return null;
        }

        Object object = null;

        String pluginName = CommonUtil.getHost(task.getUrl());
        String pluginClass = pluginMapping.get(pluginName);

        if (pluginClass == null) {
            logger.warn("不存在名为 " + pluginName + " 的插件");
            return null;
        }

        try {
            logger.info("找到解析插件：" + pluginClass);
            Class clazz = Class.forName(pluginClass);
            Constructor constructor = clazz.getConstructor(Task.class);
            object = constructor.newInstance(task);
        } catch (Exception e) {
            logger.error("反射异常：", e);
        }

        return (AbstractPlugin) object;
    }

    public static void main(String[] args) {
        PluginFactory factory = PluginFactory.getInstance();
        Task task = Task.getBuilder()
                .setUrl("http://sm.xmu.edu.cn/html/intl/")
                .build();
        factory.getPlugin(task);
    }

}
