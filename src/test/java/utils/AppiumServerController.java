package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import static io.appium.java_client.service.local.AppiumDriverLocalService.buildService;

public final class AppiumServerController {


    private static final Object mutex = new Object();
    private static volatile AppiumServerController instance;
    public AppiumDriverLocalService service;

    private AppiumServerController() {
        AppiumServiceBuilder builder;
        System.out.println("Building and starting the server:");
        builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = buildService(builder);
    }

    public static AppiumServerController getInstance() {
        AppiumServerController result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new AppiumServerController();
            }
        }
        return result;
    }

    public void startAppiumServer() {
        if (!service.isRunning()) {
            service.start();
        }
    }

    public void stopAppiumServer() {
        if (service.isRunning()) {
            service.stop();
        }
    }
}
