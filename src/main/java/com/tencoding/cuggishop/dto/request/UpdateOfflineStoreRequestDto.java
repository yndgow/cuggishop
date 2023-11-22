package com.tencoding.cuggishop.dto.request;

import com.tencoding.cuggishop.repository.model.OfflineStore;

import lombok.Data;

@Data
public class UpdateOfflineStoreRequestDto {
	int id;
	String storeName;
	Integer storeNumber;
	String storeAddress;
	String storeAddressDetail;

	public OfflineStore toEntity() {
		return new OfflineStore(this.id, this.storeName, this.storeNumber, this.storeAddress, this.storeAddressDetail,
				null);
	}
}
