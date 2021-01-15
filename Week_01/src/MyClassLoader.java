import java.io.*;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super(getParentClassLoader());
    }

    private static ClassLoader getParentClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        return MyClassLoader.class.getClassLoader();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        // 获取该class文件字节码数组
        byte[] classData = getData();

        if (classData != null) {
            // 将class的字节码数组转换成Class类的实例
            clazz = defineClass(name, classData, 0, classData.length);
        }
        return clazz;
    }


    private byte[] getData() {
        byte[] bytes = null;
        try(InputStream in = MyClassLoader.class.getResourceAsStream("Hello.xlass");ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
            bytes = out.toByteArray();
            for (int i = 0; i < bytes .length; i++) {
                byte temp = bytes [i];
                bytes [i] = (byte) (~temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes ;
    }

}
