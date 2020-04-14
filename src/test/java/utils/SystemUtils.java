package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SystemUtils {
    public static String getRandomConnectedDeviceID() {
        List<String> devices = new ArrayList<>();
        String deviceID;
        String output = runCommandAndReturnOutput("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) {
                devices.add(line.replace("device", "").trim());
            }
        }
        int numberOfDevices = devices.size();
        Random rand = new Random();
        int r = rand.nextInt(numberOfDevices);
        deviceID = devices.get(r);
        return deviceID;
    }

    public static String runCommandAndReturnOutput(String command) {
        command = command.replace("adb ", getADBPath() + " ");
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext())
                output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (output != null) {
            return output.trim();
        } else {
            return null;
        }
    }

    private static String getADBPath() {
        return System.getenv("ANDROID_HOME") + File.separator + "platform-tools" + File.separator + "adb";
    }


}