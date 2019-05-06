package net.cc.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.cc.service.EncryptionUtil;

@XmlRootElement(name = "APIRquest")  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "channel", "sign", "timpstamp", "data" }) 
public class APIRquest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channel;
	private String sign;
	private String timpstamp;
	private String data;
	public String buildDigitalSign(String secret) {
		String str = "^" + channel + "&" + secret + "&" + timpstamp + "*";
		if (data != null) {
			str += data;
		}
		return EncryptionUtil.md5(str);
	}

	public boolean validatorSign(String secret) {
		return this.buildDigitalSign(secret).equals(sign);
	}

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimpstamp() {
		return timpstamp;
	}
	public void setTimpstamp(String timpstamp) {
		this.timpstamp = timpstamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
