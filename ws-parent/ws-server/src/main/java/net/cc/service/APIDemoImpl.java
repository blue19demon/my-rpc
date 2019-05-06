package net.cc.service;

import java.util.Map;

import javax.jws.WebService;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTSigner;

import net.cc.domain.APIResponse;
import net.cc.domain.APIRquest;
@WebService(serviceName = "apiDemo")
public class APIDemoImpl implements APIDemo {

	public APIResponse call(APIRquest requestParam) {
		System.out.println("request="+JSONObject.toJSONString(requestParam));
		APIResponse response=new APIResponse();
		if (!requestParam.validatorSign(APIConst.SECRET)) {
			throw new RuntimeException("验证签名出错");
		}
		Map<String, Object> map = EncryptionUtil.decodeJWTPackage((String) requestParam.getData(), APIConst.SECRET);
		System.out.println(JSONObject.toJSONString(map));
		map.put("text", "resp");
		JWTSigner signer = new JWTSigner(APIConst.SECRET);
		String jwt = signer.sign(map);
		
		response.setChannel(APIConst.CHANNEL);
		response.setTimpstamp(String.valueOf(System.currentTimeMillis() / 1000L));
		response.setData(jwt);
		response.setSign(response.buildDigitalSign(APIConst.SECRET));
		return response;
	}
}
