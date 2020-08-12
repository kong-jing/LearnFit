package com.fengguncao.learnfit.hotfix;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * name: ShareReflectUtil<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 6/18/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class ShareReflectUtil {
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        for(Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);
                if(!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {

            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    public static void expandFieldArray(Object instance, String fieldName, Object[] patchElements) {
        try {
            Field dexElementField = findField(instance, fieldName);
            Object[] dexElements = (Object[]) dexElementField.get(instance);
            Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + patchElements.length);
            System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
            System.arraycopy(dexElements, 0, newElements, patchElements.length, dexElements.length);

            dexElementField.set(instance, newElements);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
        throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);

                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }

                return method;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }
        throw new NoSuchMethodException("Method "
            + name
            + " with parameters "
            + Arrays.asList(parameterTypes)
            + " not found in " + instance.getClass());
    }
}
