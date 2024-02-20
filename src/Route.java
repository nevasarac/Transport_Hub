import java.util.HashMap;
import java.util.Map;

public class Route {

    private static final Map<String, String> predefinedRoutes = new HashMap<>();
    private static final Map<String, String> addedRoutes = new HashMap<>();

    static {
        predefinedRoutes.put("1.sefer", "˙Istanbul - Kocaeli - Bilecik - Eskisehir - Ankara - Eskisehir - Bilecik - Kocaeli - ˙Istanbul");
        predefinedRoutes.put("2.sefer", "˙Istanbul - Kocaeli - Bilecik - Eskisehir - Konya - Eskisehir - Bilecik - Kocaeli - ˙Istanbul");
        predefinedRoutes.put("3.sefer", "˙Istanbul - Kocaeli - Ankara - Kocaeli - ˙Istanbul - Kocaeli - Ankara - Kocaeli - ˙Istanbul");
        predefinedRoutes.put("4.sefer", "˙Istanbul - Kocaeli - Eskisehir - Konya - Eskisehir - Kocaeli - ˙Istanbul");
        predefinedRoutes.put("5.sefer", "˙Istanbul - Konya - ˙Istanbul");
        predefinedRoutes.put("6.sefer", "˙Istanbul - Ankara - ˙Istanbul");
    }

    public static String getRoute(String key) {
        return predefinedRoutes.get(key);
    }

    public static void addRoute(String key, String route) {
        addedRoutes.put(key, route);
    }

    public static void removeRoute(String key) {
        addedRoutes.remove(key);
    }

    public static Map<String, String> getAddedRoutes() {
        return addedRoutes;
    }
}
