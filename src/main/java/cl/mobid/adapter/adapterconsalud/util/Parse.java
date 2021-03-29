package cl.mobid.adapter.adapterconsalud.util;

import java.io.IOException;
import java.io.InputStream;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.mobid.adapter.adapterconsalud.config.PropertyConfig;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.request.ItemDetailReqEntMtType;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.request.RequestEntMtType;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.response.ResonseEntMtType;
import cl.mobid.adapterenterprise.common.util.CommonConstant;

public class Parse {

	private final static Logger logger = LoggerFactory.getLogger(Parse.class);

	public static RequestEntMtType obtenerDtoWsEnterprise(InputStream inputStream, String token, String celular,
			String mensaje, String idTrx) throws JDOMException, IOException {
		RequestEntMtType request = new RequestEntMtType();
		ItemDetailReqEntMtType item = new ItemDetailReqEntMtType();
		request.setToken(token);
		String numMobil = celular;
		int max = numMobil.length();

		if (numMobil != null && !"".equals(numMobil) && 8 <= max && 11 > max) {
			Parse.logger.debug("se modifica numero de telefono: " + numMobil);
			String aux = numMobil.substring(max - 8, max);
			Parse.logger.debug("se recorta numero de telefono: " + aux);
			aux = "569" + aux;
			Parse.logger.debug("se agrega prefijo: " + aux);
			numMobil = aux;
		}
		item.setNumberMobil(numMobil);
		item.setClientCode(idTrx);
		item.setMessage(mensaje);
		item.setSendDate("");
		request.getDetailReqEntMt().add(item);

		return request;
	}

	
	public static String readResponseEnt(ResonseEntMtType responseData) {

		String message = "";
		String prefixMsg = "adapter.consalud.send.status.message.code";
		String code = null;

		if (responseData == null) {
			code = "1002";
			
		} else {
			int GeneralCode = responseData.getGeneralCode();
			code = Integer.toString(GeneralCode);
		}

		String key = prefixMsg + "." + code;
		PropertyConfig prop = PropertyConfig.getInstancia();
		message = prop.getPropiedadType(key, CommonConstant.PROPERTY_MSG_ES);
		String resp = "<RESPUESTA><STATUS>" + code + "</STATUS><CODERROR>" + code + "</CODERROR><DESCERROR>" + message+"</DESCERROR></RESPUESTA>";

		return resp;
	}

}