package com.tencoding.cuggishop.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductImage {
	int id;
	int productId;
	String image;
	boolean isThumbnail;
	Timestamp createdAt;
}
