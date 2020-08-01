package ru.progwards.pavliggs.java2.N10dot3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleAutoLoader extends ClassLoader {
    final static String PATH_OF_TASKS = "C:\\Users\\Эльдорадо\\data\\";
    final static String DOT_CLASS = ".class";
    private static SimpleAutoLoader loader = new SimpleAutoLoader(PATH_OF_TASKS);

    private final String basePath;

    public SimpleAutoLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public SimpleAutoLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String classPath = className.replace(".", "\\");
            Path classPathName = Paths.get(basePath + classPath + DOT_CLASS);
            System.out.println(1);
            System.out.println(classPathName.toString());
            if (Files.exists(classPathName)) {
                byte[] b = Files.readAllBytes(classPathName);
                return defineClass(className, b, 0, b.length);
            } else
                return findSystemClass(className);
        } catch (IOException ex) {
            throw new ClassNotFoundException(className);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findClass(name);
        if (resolve)
            resolveClass(c);
        return c;
    }

    private static void updateTaskList(Map<String, Task> tasks) throws IOException {
        Files.walkFileTree(Paths.get(PATH_OF_TASKS), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) {
                    String className = makeClassName(path);
                    Task task = tasks.get(className);
                    if (task == null || task.getModifiedTime() != attrs.lastModifiedTime().toMillis()) {
                        try {
                            if (task != null)
                                loader = new SimpleAutoLoader(PATH_OF_TASKS);
                            Class<?> taskClass = loader.loadClass(className, true);
                            Task newTask = (Task)taskClass.getDeclaredConstructor().newInstance();
                            newTask.setModifiedTime(attrs.lastModifiedTime().toMillis());
                            tasks.put(className, newTask);
                            System.out.println((task == null ? "Добавлен" : "Обновлен") + " класс " + className);
                        } catch (ClassNotFoundException | NoSuchMethodException |
                                InstantiationException | IllegalAccessException |
                                InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static String makeClassName(Path path) throws IOException {
        path = path.toAbsolutePath();
        Path relPath = Paths.get(PATH_OF_TASKS).relativize(path);
        String className = relPath.toString().replace("\\", ".");
        if (className.toLowerCase().endsWith(DOT_CLASS))
            className = className.substring(0, className.length() - DOT_CLASS.length());
        return className;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Task> tasks = new LinkedHashMap<>();

        while (true) {
            System.out.println("Проверка классов и запуск задач: " +
                    String.format("%1$tI:%1$tM:%1$tS:%1$tN", new Date()));

            updateTaskList(tasks);

            SecureRandom random = new SecureRandom();
            byte[] data = new byte[1000];
            random.nextBytes(data);

            for (var task : tasks.entrySet())
                System.out.println("    " + task.getValue().process(data));

            Thread.sleep(10_000);
        }
    }
}
