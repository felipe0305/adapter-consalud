package cl.mobid.adapter.adapterconsalud.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.mobid.adapterenterprise.common.dto.sendmt.normal.request.RequestEntMtType;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.response.ResonseEntMtType;
import cl.mobid.adapterenterprise.common.service.CommonClientEnterpriseService;
import cl.mobid.adapterenterprise.common.util.CommonConstant;
import cl.mobid.adapterenterprise.common.util.CommonParse;

public class ClientEnterpriseService extends CommonClientEnterpriseService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ClientEnterpriseService(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	protected void formatLog(String text, int type, Exception e) {
		switch (type) {
		case CommonConstant.DEBUG_LOG:
			this.logger.debug(text);
			break;
		case CommonConstant.INFO_LOG:
			this.logger.info(text);
			break;
		case CommonConstant.WARN_LOG:
			this.logger.warn(text);
			break;
		case CommonConstant.ERROR_LOG:
			this.logger.error(text, e);
			break;
		default:
			break;
		}
	}

	public ResonseEntMtType callNormalService(RequestEntMtType req) {
		ResonseEntMtType resp=null;
		String json;
		try {
			String body = CommonParse.ObjectToJson(req);
			json = this.callPost(body);
			resp = CommonParse.jsonToObject(json, ResonseEntMtType.class);
		} catch (Exception e) {
			this.logger.error(e.getMessage(),e);
		} 
		return resp;
	}
	
}
