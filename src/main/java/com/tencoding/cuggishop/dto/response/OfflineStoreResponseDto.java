package com.tencoding.cuggishop.dto.response;

import com.tencoding.cuggishop.repository.model.OfflineStore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfflineStoreResponseDto {
	int id;
	String storeName;
	int storeNumber;
	String storeAddress;
	String storeAddressDetail;
	
	public static OfflineStoreResponseDto fromEntity(OfflineStore offlineStore){
		return new OfflineStoreResponseDto(
				offlineStore.getId(), 
				offlineStore.getStoreName(), 
				offlineStore.getStoreNumber(), 
				offlineStore.getStoreAddress(), 
				offlineStore.getStoreAddressDetail()
				);
	}
}
