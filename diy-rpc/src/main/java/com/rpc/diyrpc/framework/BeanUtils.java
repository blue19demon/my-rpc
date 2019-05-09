package com.rpc.diyrpc.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BeanUtils {
	/**
	 * 判断object是否为基本类型
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isBaseType(Class<?> className) {
		if (className.equals(java.lang.Integer.class) || className.equals(java.lang.Byte.class)
				|| className.equals(java.lang.Long.class) || className.equals(java.lang.Double.class)
				|| className.equals(java.lang.Float.class) || className.equals(java.lang.Character.class)
				|| className.equals(java.lang.Short.class) || className.equals(java.lang.String.class)
				|| className.equals(java.lang.Boolean.class)) {
			return true;
		}
		return false;
	}
	
	/**
     * 对象转byte
     * @param obj
     * @return
     */
	public static byte[] ObjectToByte(Object obj) {  
        byte[] bytes = null;  
        try {  
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
      
            bytes = bo.toByteArray();  
      
            bo.close();  
            oo.close();  
        } catch (Exception e) {  
            System.out.println("translation" + e.getMessage());  
            e.printStackTrace();  
        }  
        return bytes;  
    } 
    
    /**
     * byte转对象
     * @param bytes
     * @return
     */
	public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }
}
