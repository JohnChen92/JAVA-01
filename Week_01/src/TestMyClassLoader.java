import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMyClassLoader {


    public static void main(String[] args) {
        try {
            MyClassLoader loader = new MyClassLoader();
            Class<?> helloClazz = loader.findClass("Hello");
            Object object = helloClazz.newInstance();
            Method hello = helloClazz.getDeclaredMethod("hello");
            hello.invoke(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
