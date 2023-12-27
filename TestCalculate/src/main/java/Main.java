import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main{
    public static void main(String[] args) {
        try {
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{new URL("file:lib/CalculcateFunction-1.0-SNAPSHOT.jar")}
            );

            String className = "Calculate";
            String methodName = "calculateMultiple";

            Class<?> clazz = Class.forName(className, true, classLoader);
            Method method = clazz.getMethod(methodName, double.class, double.class);

            double x = 5.0;
            double y = 10.0;
            Object instance = clazz.getDeclaredConstructor().newInstance();


            Object result = method.invoke(instance, x,y);

            System.out.println("Результат: " + result);

            classLoader.close();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException  | java.io.IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
