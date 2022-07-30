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

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="UserToken")
@Table(name="UserToken")
public class UserToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String token;
	private int userId;

}
