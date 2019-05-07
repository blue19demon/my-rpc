package com.rpc.diyrpc.provider.rest.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@ToString
public class Department implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2178985698430314373L;
	private Long id;
    private String name;
}