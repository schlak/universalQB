package com.github.schlak.database;

import com.github.schlak.database.Definition.Cleanable;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jonas Schlak.
 */
public class ObjectRecycler {

    private static String DEFAULT = "COM_GITHUB_SCHLAK_DATABASE_OBJECT_RECYCLER";
    private static HashMap<String, ObjectRecycler> instanceHashMap = new HashMap<>();

    private HashMap<Class, Stack> classStackHashMap;

    private ObjectRecycler() {
        this.classStackHashMap = new HashMap<>();
    }

    private static ObjectRecycler getInstance(String string) {
        if (!instanceHashMap.containsKey(string))
            instanceHashMap.put(string,new ObjectRecycler());

        return instanceHashMap.get(string);
    }



    public static <T extends Cleanable> T getInstance(Class<T> clazz) {
        return getInstance(DEFAULT, clazz);
    }

    public static <T extends Cleanable> T getInstance(String string, Class<T> clazz) {
        ObjectRecycler instance = getInstance(string);

        if (!instance.classStackHashMap.containsKey(clazz)){
            instance.classStackHashMap.put(clazz,new Stack());
            try {
                instance.classStackHashMap.get(clazz).push(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return (T) instance.classStackHashMap.get(clazz).pop();
    }



    public static void returnInstance(Cleanable cleanable) {
        cleanable.clean();
        returnInstance(DEFAULT, cleanable);
    }

    public static void returnInstance(String string, Cleanable cleanable) {
        ObjectRecycler instance = getInstance(string);

        if (!instance.classStackHashMap.containsKey(cleanable.getClass()))
            instance.classStackHashMap.put(cleanable.getClass(),new Stack());

        instance.classStackHashMap.get(cleanable.getClass()).push(cleanable);

    }

}
