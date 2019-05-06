package org.gupao.rpc.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hello world!
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9067155081882406510L;
	private Integer id;
    private String name;
	
}
