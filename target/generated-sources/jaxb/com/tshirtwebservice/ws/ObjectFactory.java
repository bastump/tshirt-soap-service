//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.13 at 08:24:42 PM EST 
//


package com.tshirtwebservice.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tshirtwebservice.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListInventoryRequest_QNAME = new QName("http://ws.tshirtwebservice.com", "ListInventoryRequest");
    private final static QName _ListInventoryResponse_QNAME = new QName("http://ws.tshirtwebservice.com", "ListInventoryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tshirtwebservice.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link APIUsageInformation }
     * 
     */
    public APIUsageInformation createAPIUsageInformation() {
        return new APIUsageInformation();
    }

    /**
     * Create an instance of {@link AuthenticationHeader }
     * 
     */
    public AuthenticationHeader createAuthenticationHeader() {
        return new AuthenticationHeader();
    }

    /**
     * Create an instance of {@link OrderTshirtRequest }
     * 
     */
    public OrderTshirtRequest createOrderTshirtRequest() {
        return new OrderTshirtRequest();
    }

    /**
     * Create an instance of {@link OrderTshirtResponse }
     * 
     */
    public OrderTshirtResponse createOrderTshirtResponse() {
        return new OrderTshirtResponse();
    }

    /**
     * Create an instance of {@link TrackOrderRequest }
     * 
     */
    public TrackOrderRequest createTrackOrderRequest() {
        return new TrackOrderRequest();
    }

    /**
     * Create an instance of {@link TrackOrderResponse }
     * 
     */
    public TrackOrderResponse createTrackOrderResponse() {
        return new TrackOrderResponse();
    }

    /**
     * Create an instance of {@link TshirtFault }
     * 
     */
    public TshirtFault createTshirtFault() {
        return new TshirtFault();
    }

    /**
     * Create an instance of {@link ListInventoryRequest }
     * 
     */
    public ListInventoryRequest createListInventoryRequest() {
        return new ListInventoryRequest();
    }

    /**
     * Create an instance of {@link ListInventoryResponse }
     * 
     */
    public ListInventoryResponse createListInventoryResponse() {
        return new ListInventoryResponse();
    }

    /**
     * Create an instance of {@link InventoryItem }
     * 
     */
    public InventoryItem createInventoryItem() {
        return new InventoryItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.tshirtwebservice.com", name = "ListInventoryRequest")
    public JAXBElement<Object> createListInventoryRequest(Object value) {
        return new JAXBElement<Object>(_ListInventoryRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.tshirtwebservice.com", name = "ListInventoryResponse")
    public JAXBElement<Object> createListInventoryResponse(Object value) {
        return new JAXBElement<Object>(_ListInventoryResponse_QNAME, Object.class, null, value);
    }

}
