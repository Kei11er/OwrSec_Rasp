package com.owrsec.rasp;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class rootDetector2 {
    // detecção de root com métodos alternativos
    public boolean isDeviceRooted() {
        return checkBuildTags() || checkSuperUserPaths() || checkSuBinary();
    }

    private static boolean checkBuildTags() {
        return "test-keys".equals(android.os.Build.TAGS);
    }

    private static boolean checkSuperUserPaths() {
        List<String> paths = Arrays.asList(
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        );

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkSuBinary() {
        String[] commands = {"/system/xbin/which su", "/system/bin/which su", "which su"};
        try {
            for (String command : commands) {
                Process process = Runtime.getRuntime().exec(command);
                int exitValue = process.waitFor();
                if (exitValue == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
