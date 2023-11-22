package com.tencoding.cuggishop.dto.request;

import com.tencoding.cuggishop.repository.model.OfflineStore;

import lombok.Data;

@Data
public class InsertOfflineStoreRequestDto {
	String storeName;
	Integer storeNumber;
	String storeAddress;
	String storeAddressDetail;

	public OfflineStore toEntity() {
		return new OfflineStore(0, this.storeName, this.storeNumber, this.storeAddress, this.storeAddressDetail, null);
	}
}
