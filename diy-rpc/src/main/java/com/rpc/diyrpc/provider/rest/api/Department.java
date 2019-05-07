package com.rpc.diyrpc.provider.rest.api;

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
public class Department {
    private Long id;
    private String name;
}