package cl.mobid.adapter.adapterconsalud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.mobid.adapterenterprise.common.config.CommonProperties;
import cl.mobid.adapterenterprise.common.util.CommonConstant;

public class PropertyConfig extends CommonProperties {

	private static Logger logger = LoggerFactory.getLogger(PropertyConfig.class);
	private static PropertyConfig instancia = null;
	public static String PATH;

	protected PropertyConfig(String path) {
		super(path);
	}

	public static PropertyConfig getInstancia() {
		if (instancia == null) {
			instancia = new PropertyConfig(PropertyConfig.PATH);
		}
		return instancia;
	}

	@Override
	protected void formatLog(String message, int typeLog, Exception e) {
		switch (typeLog) {
		case CommonConstant.DEBUG_LOG:
			logger.error(message);
			break;
		case CommonConstant.INFO_LOG:
			logger.info(message);
			break;
		case CommonConstant.WARN_LOG:
			logger.info(message);
			break;
		case CommonConstant.ERROR_LOG:
			logger.error(message, e);
			break;
		default:
			logger.info(message);
			break;
		}

	}

}
