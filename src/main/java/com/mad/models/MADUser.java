package com.mad.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity(name="MADUser")
@Table(name="MADUser")
@ToString
public class MADUser implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name;
	private String username;
	private String email;
	private String password;
	private int isReminderOn;
	private int coins;
	@Column(columnDefinition="TEXT")
	private String profilePic;
	private String billingAddress;
	private String token;

}
