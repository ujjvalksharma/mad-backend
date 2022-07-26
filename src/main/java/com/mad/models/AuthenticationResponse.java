package com.mad.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse implements Serializable {

    private String jwt;

}
