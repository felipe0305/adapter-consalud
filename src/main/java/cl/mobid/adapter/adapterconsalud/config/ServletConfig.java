package cl.mobid.adapter.adapterconsalud.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.mobid.adapter.adapterconsalud.input.McsQaServlet;
import cl.mobid.adapter.adapterconsalud.input.McsServlet;
import cl.mobid.adapter.adapterconsalud.input.MksServlet;

@Configuration
public class ServletConfig  
{	
	
	@Value("${env.service.enterprise.user.mcs}")
	private String USER;
	
	@Value("${env.service.enterprise.pass.mcs}")
	private String PASS;
	
	@Value("${env.service.enterprise.url.mcs}")
	private String URL;
	
	@Value("${env.service.enterprise.token.mcs}")
	private String TOKEN;
	
	
	
	@Value("${env.service.enterprise.user.mks}")
	private String USER_MKS;
	
	@Value("${env.service.enterprise.pass.mks}")
	private String PASS_MKS;
	
	@Value("${env.service.enterprise.url.mks}")
	private String URL_MKS;
	
	@Value("${env.service.enterprise.token.mks}")
	private String TOKEN_MKS;
	
	
	
	@Value("${env.service.enterprise.user.mcs.qa}")
	private String USER_MCS_QA;
	
	@Value("${env.service.enterprise.pass.mcs.qa}")
	private String PASS_MCS_QA;
	
	@Value("${env.service.enterprise.url.mcs.qa}")
	private String URL_MCS_QA;
	
	@Value("${env.service.enterprise.token.mcs.qa}")
	private String TOKEN_MCS_QA;
	
	
	@Bean
	public ServletRegistrationBean<MksServlet> servletRegistrationBeanMks(){
	    return new ServletRegistrationBean<MksServlet>(new MksServlet(USER_MKS,PASS_MKS,URL_MKS,TOKEN_MKS),"/mks/ListenMT/*");
	}
	@Bean
	public ServletRegistrationBean<McsServlet> servletRegistrationBeanMcs(){
	    return new ServletRegistrationBean<McsServlet>(new McsServlet(USER,PASS,URL,TOKEN),"/mcs/ListenMT/*");
	}
	
	@Bean
	public ServletRegistrationBean<McsQaServlet> servletRegistrationBeanMcsQa(){
	    return new ServletRegistrationBean<McsQaServlet>(new McsQaServlet(USER_MCS_QA,PASS_MCS_QA,URL_MCS_QA,TOKEN_MCS_QA),"/mcs/qa/ListenMT/*");
	}
	
	
	
	
	
	
	
}
