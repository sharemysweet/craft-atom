package io.craft.atom.rpc;

import io.craft.atom.protocol.rpc.model.RpcMethod;
import io.craft.atom.rpc.api.RpcParameter;
import io.craft.atom.rpc.spi.RpcApi;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * @author mindwind
 * @version 1.0, Aug 12, 2014
 */
@ToString
@EqualsAndHashCode(of = "key")
public final class DefaultRpcApi implements RpcApi, Comparable<RpcApi> {
	
	
	@Getter private String       key         ;
	@Getter private String       name        ;
	@Getter private String       rpcId       ;
	@Getter private Class<?>     rpcInterface;
	@Getter private RpcMethod    rpcMethod   ;
	@Getter private Object       rpcObject   ;
	@Getter private RpcParameter rpcParameter;
	
	
	// ~ ------------------------------------------------------------------------------------------------------------
	

	public DefaultRpcApi(String rpcId, Class<?> rpcInterface, RpcMethod rpcMethod) {
		this.rpcId        = rpcId       ;
		this.rpcInterface = rpcInterface;
		this.rpcMethod    = rpcMethod   ;
		this.key          = key()       ;
		this.name         = name()      ;
	}
	
	public DefaultRpcApi(String rpcId, Class<?> rpcInterface, RpcMethod rpcMethod, Object rpcObject, RpcParameter rpcParameter) {
		this.rpcId        = rpcId       ;
		this.rpcInterface = rpcInterface;
		this.rpcMethod    = rpcMethod   ;
		this.rpcObject    = rpcObject   ;
		this.rpcParameter = rpcParameter;
		this.key          = key()       ;
		this.name         = name()      ;
	}
	
	
	// ~ ------------------------------------------------------------------------------------------------------------
	
	
	private String key() {
		if (rpcId == null) {
			return Integer.toString(rpcInterface.hashCode()) + Integer.toString(rpcMethod.hashCode());
		} else {
			return rpcId + "-" + Integer.toString(rpcInterface.hashCode()) + Integer.toString(rpcMethod.hashCode());
		}
	}

	private String name() {
		String format = "{rpc-id=%s, rpc-interface=%s, rpc-method-name=%s, rpc-method-parameter-types=%s, rpc-object=%s, rpc-parameter=%s}";
		return String.format(format, rpcId, rpcInterface.getName(), rpcMethod.getName(), rpcMethod.getParameterTypes(), rpcObject, rpcParameter);
	}

	@Override
	public int compareTo(RpcApi api) {
		if (api == null) { return 1; }
		return key.compareTo(api.getKey());
	}

	@Override
	public String getId() {
		return rpcId;
	}

	@Override
	public Class<?> getInterface() {
		return rpcInterface;
	}

	@Override
	public String getMethodName() {
		return rpcMethod.getName();
	}

	@Override
	public Class<?>[] getMethodParameterTypes() {
		return rpcMethod.getParameterTypes();
	}
	
}
