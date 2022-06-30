package com.sofrecom.stage.models;

import java.io.Serializable;

import javax.persistence.Embeddable;


import lombok.Data;

@Embeddable
@Data
public class PKEmployeMeeting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idClient ;
	private Long idMeeting ; 
	

}
