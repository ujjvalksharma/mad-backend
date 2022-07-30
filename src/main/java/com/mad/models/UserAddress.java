package com.mad.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="UserAddress")
@Table(name="UserAddress")
@ToString
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String userId;
	private String street;
	private String city;
	private String state;


}
