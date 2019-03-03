package com.brujula.brujula.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeansException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "Professor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Professor {
	@Id
	private String id_Person;
	private Timestamp  asociateStartDate;
	@NotNull(message = "La fecha debe ser mayor a la actual")
	private Timestamp actingEndDate;
	@OneToOne(cascade=CascadeType.ALL)
	@MapsId
	@JsonIgnore
	private Person person;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_Department" ) 
	private Department department;
	
		
	 @PrePersist
	    public void prePersist() {
		 Date date = new Date();
		 Timestamp timestamp = new Timestamp(date.getTime());
		 
		 if(this.actingEndDate.before(timestamp)) {
			 this.actingEndDate=null;
		 };
	       
	    }
	
	public Professor() {
	}
	
	
}
