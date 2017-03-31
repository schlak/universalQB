package com.github.schlak.database;

import com.github.schlak.database.Definition.Cleanable;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jonas Schlak.
 */
public class ObjectRecycler {

    private static int DEFAULT = 100000;
    private static HashMap<Integer, ObjectRecycler> instanceHashMap = new HashMap<>();

    private HashMap<Integer, Stack> classStackHashMap;

    private ObjectRecycler() {
        this.classStackHashMap = new HashMap<>();
    }

    private static ObjectRecycler getInstance(Integer instanceKey) {
        if (!instanceHashMap.containsKey(instanceKey))
            instanceHashMap.put(instanceKey, new ObjectRecycler());

        return instanceHashMap.get(instanceKey);
    }


    public static <T extends Cleanable> T getInstance(Class<T> clazz) {
        return getInstance(DEFAULT, clazz);
    }

    public static <T extends Cleanable> T getInstance(Integer instanceTag, Class<T> clazz) {
        ObjectRecycler instance = getInstance(instanceTag);
        T object = null;

//        if (!instance.classStackHashMap.containsKey(clazz)) {
//            instance.classStackHashMap.put(clazz, new Stack());
//        }

        if (instance.classStackHashMap.get(System.identityHashCode(clazz)) == null || instance.classStackHashMap.get(System.identityHashCode(clazz)).size() == 0) {
            try {
                object = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            object = (T) instance.classStackHashMap.get(System.identityHashCode(clazz)).pop();
        }


        return object;
    }


    public static void returnInstance(Cleanable cleanable) {
        cleanable.clean();
        returnInstance(DEFAULT, cleanable);
    }

    public static void returnInstance(Integer instanceKey, Cleanable cleanable) {
        ObjectRecycler instance = getInstance(instanceKey);

        if (!instance.classStackHashMap.containsKey(System.identityHashCode(cleanable.getClass())))
            instance.classStackHashMap.put(System.identityHashCode(cleanable.getClass()), new Stack());

        instance.classStackHashMap.get(System.identityHashCode(cleanable.getClass())).push(cleanable);

    }

    public static int numberOfCachedItems() {
        i = 0;

        instanceHashMap.forEach((s, objectRecycler) -> {
            objectRecycler.classStackHashMap.forEach((aClass, stack) -> {
                stack.forEach(o -> add());
            });
        });

        return i;
    }

    private static int i = 0;

    private static void add() {
        i++;
    }

}
