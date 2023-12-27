import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main{
    public final static String LIB_URL = "file:lib/CalculcateFunction-1.0-SNAPSHOT.jar";
    public final static String CLASS_NAME = "Calculate";
    public final static String CLASS_METHOD = "calculateMultiple";
    public final static double x = 5.0;
    public final static double y = 10.0;

    public static void main(String[] args) {
        try {
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{new URL(LIB_URL)}
            );

            Class<?> clazz = Class.forName(CLASS_NAME, true, classLoader);
            Method method = clazz.getMethod(CLASS_METHOD, double.class, double.class);

            Object instance = clazz.getDeclaredConstructor().newInstance();

            double result = (double) method.invoke(instance, x, y);

            System.out.println("Result: " + result);

            classLoader.close();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException | InstantiationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
