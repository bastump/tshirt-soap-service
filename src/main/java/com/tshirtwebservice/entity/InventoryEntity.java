package com.tshirtwebservice.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tshirtwebservice.ws.Size;

@Entity
@Table(name="inventory")
public class InventoryEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    private String productCode;
    private Size size;
    private String description;
    private BigInteger count;
    
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
}