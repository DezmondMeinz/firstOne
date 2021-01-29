package ru.geekbrains.homework;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
public class Main {

    public static void main(String[] args) throws Exception {
        Class c = MyClass.class;
        Object testObj = c.newInstance();
        Method[] methods = c.getDeclaredMethods();
        ArrayList<Method> al = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        for (Method o : c.getDeclaredMethods()) {
            if (o.isAnnotationPresent(Test.class)) {
                al.add(o);
            }
            if (o.isAnnotationPresent(Before.class)) {
                if (beforeMethod == null) beforeMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией Before");
            }
            if (o.isAnnotationPresent(After.class)) {
                if (afterMethod == null) afterMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией After");
            }
            al.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o2.getAnnotation(Test.class).priority()-o1.getAnnotation(Test.class).priority();
                }
            });

        }



        if (beforeMethod != null) beforeMethod.invoke(testObj, null);
        for (Method o : al) o.invoke(testObj, null);
        if (afterMethod != null) afterMethod.invoke(testObj, null);
    }
}
