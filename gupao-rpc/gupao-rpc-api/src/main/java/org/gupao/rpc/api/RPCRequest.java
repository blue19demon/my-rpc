package org.gupao.rpc.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RPCRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375998969911401112L;

	private String interfaceName;
	private String methodName;
	private Object[] args;

}
