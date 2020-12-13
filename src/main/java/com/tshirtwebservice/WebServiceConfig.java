package com.tshirtwebservice;

import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean(name = "tshirts")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema tshirtsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    Properties soapActions = new Properties();
	    soapActions.setProperty("OrderTshirt", "http://ws.tshirtwebservice.com/order-tshirt");
	    soapActions.setProperty("TrackOrder", "http://ws.tshirtwebservice.com/track-order");
	    soapActions.setProperty("ListInventory", "http://ws.tshirtwebservice.com/list-inventory");
	    wsdl11Definition.setSoapActions(soapActions);
		wsdl11Definition.setPortTypeName("TshirtServicePortType");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://ws.tshirtwebservice.com");
		wsdl11Definition.setSchema(tshirtsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema tshirtsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("tshirts.xsd"));
	}
}