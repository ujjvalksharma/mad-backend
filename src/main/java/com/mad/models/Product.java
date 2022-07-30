package com.mad.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mad.models.MADUser.MADUserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Product")
@Table(name="Product")
@ToString
public class Product {
	
	@Id
	private int id;
    private String title;
	private double price;
	private String description;
	private String category;
	private int image;

}
