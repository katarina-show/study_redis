package com.sjw.cache.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializerUtil {

    /**
     * ���л�
     * @param object
     * @return
     */
    public static byte[] serializeObj(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException("���л�ʧ��!", e);
        }
    }

    /**
     * �����л�
     * @param bytes
     * @return
     */
    public static Object deserializeObj(byte[] bytes) {
        if (bytes == null){
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("�����л�ʧ��!", e);
        }
    }
}
