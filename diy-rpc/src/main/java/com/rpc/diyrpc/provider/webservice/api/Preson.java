package com.rpc.diyrpc.provider.webservice.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@XmlRootElement
public class Preson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private int age;
	
	private List<HelloEntity> helloEntityList;
	
	private Map<String,HelloEntity> helloEntityMap;
}
