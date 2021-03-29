package cl.mobid.adapter.adapterconsalud;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cl.mobid.adapter.adapterconsalud.config.PropertyConfig;
import cl.mobid.adapter.adapterconsalud.util.Constant;

public class InitBean {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${path.property}")
	String PATH_PROPERTY;
	
	@PostConstruct
	public void init() {
		logger.info("Aplicacion inicializada");
		String path = this.PATH_PROPERTY + "/" + Constant.PROPERTY_APP + "/" + Constant.PROPERTY_DIR;
		PropertyConfig.PATH = path;
		PropertyConfig.getInstancia();
	}

	@PreDestroy
	public void destroy() {
		logger.info("Aplicacion Detenida");
	}
}
