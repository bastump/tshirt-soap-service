//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.13 at 08:00:06 PM EST 
//


package com.tshirtwebservice.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Size.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Size"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="XS"/&gt;
 *     &lt;enumeration value="S"/&gt;
 *     &lt;enumeration value="M"/&gt;
 *     &lt;enumeration value="L"/&gt;
 *     &lt;enumeration value="XL"/&gt;
 *     &lt;enumeration value="XXL"/&gt;
 *     &lt;enumeration value="NA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Size")
@XmlEnum
public enum Size {

    XS,
    S,
    M,
    L,
    XL,
    XXL,
    NA;

    public String value() {
        return name();
    }

    public static Size fromValue(String v) {
        return valueOf(v);
    }

}
