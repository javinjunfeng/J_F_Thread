package top.javinjunfeng.java.thread.hook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/2 2:44 PM
 * @Version 1.0
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "/Users/zhangjunfeng/IdeaProjects/J_F_Thread/Base/src/main/locks";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        // 注入 Hook 线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        // 检查是否存在 .lock文件
        checkRunning();

        // 模拟当前程序正在运行
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("Program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()){
            throw new RuntimeException("The program already running.");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));

    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
