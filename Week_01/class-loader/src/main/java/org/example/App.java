package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.example.CustomClassLoader.HELLO_CLASS_NAME;
import static org.example.CustomClassLoader.HELLO_METHOD_NAME;

public class App {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        CustomClassLoader.setWorkingDirectory(System.getProperty("user.dir"));

        System.out.println("\n加载Hello.class");
        CustomClassLoader.setFileType(FileType.CLASS);
        invokeHelloClass();

        System.out.println("\n加载Hello.xlass");
        CustomClassLoader.setFileType(FileType.XLASS);
        invokeHelloClass();
}

    private static void invokeHelloClass() {
        try {
            Object object = new CustomClassLoader().findClass(HELLO_CLASS_NAME)
                    .getDeclaredConstructor().newInstance();
            System.out.println("调用hello方法");
            Method method = object.getClass().getDeclaredMethod(HELLO_METHOD_NAME);
            method.setAccessible(true);
            method.invoke(object);
        } catch (InstantiationException e) {
            System.out.println("InstantiationException" + e.toString());
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException" + e.toString());
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException" + e.toString());
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException" + e.toString());
        }
    }
}
