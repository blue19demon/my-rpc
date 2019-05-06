package com.rpc.diyrpc.provider.api;

import java.io.Serializable;
import java.util.Map;

public class Order implements Serializable{


    /**
     * 
     */
    private static final long serialVersionUID = -1352128884522619903L;

    private String userName;

    private int age;

    private Map<String, Object> detailData;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Object> getDetailData() {
        return detailData;
    }

    public void setDetailData(Map<String, Object> detailData) {
        this.detailData = detailData;
    }

	@Override
	public String toString() {
		return "Order [userName=" + userName + ", age=" + age + ", detailData=" + detailData + "]";
	}
    
}
