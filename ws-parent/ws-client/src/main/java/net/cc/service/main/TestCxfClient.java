package net.cc.service.main;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTSigner;

import net.cc.domain.APIResponse;
import net.cc.domain.APIRquest;
import net.cc.service.APIConst;
import net.cc.service.APIDemo;
import net.cc.service.API_URL;
import net.cc.service.CXFDemo;
import net.cc.service.EncryptionUtil;

public class TestCxfClient{
	
	public static void main(String[] args) {
		testCXFAPI();
	}
	
	public static void testCXF() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CXFDemo.class);
		factory.setAddress(API_URL.ADDRESS_DEMO);
		CXFDemo client = (CXFDemo) factory.create();
		System.out.println(client.sayHello("foo"));
		
	}
	
	public static void testCXFAPI() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(APIDemo.class);
		factory.setAddress(API_URL.ADDRESS_API);
		APIDemo client = (APIDemo) factory.create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderCode", System.currentTimeMillis());
		map.put("orderAmount", new BigDecimal(129.09));
		map.put("userId", "948757");
		map.put("userName", "8888");
		JWTSigner signer = new JWTSigner(APIConst.SECRET);
		String jwt = signer.sign(map);
		APIRquest request = new APIRquest();
		request.setChannel(APIConst.CHANNEL);
		request.setTimpstamp(String.valueOf(System.currentTimeMillis() / 1000L));
		request.setData(jwt);
		request.setSign(request.buildDigitalSign(APIConst.SECRET));
		APIResponse resp=client.call(request);
		if (!resp.validatorSign(APIConst.SECRET)) {
			throw new RuntimeException("验证签名出错");
		}
		Map<String, Object> mapResp = EncryptionUtil.decodeJWTPackage((String) resp.getData(), APIConst.SECRET);
		System.out.println(JSONObject.toJSONString(mapResp));
		
	}
}