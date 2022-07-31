package com.mad.models;

import java.io.Serializable;

import javax.persistence.Column;
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
public class Product implements Serializable{
	
	@Id
	private int id;
    private String title;
	private double price;
	@Column(columnDefinition="text")
	private String description;
	private String category;
	private String image;

}
