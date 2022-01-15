package com.example.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="scheduleslots")
public class ScheduleSlot {
	
	@Id
	@Column(name="scheduleId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scheduleId;
	
	/** enum days*/
	@Enumerated(EnumType.STRING)
	@Column(name="days")
	private Days days;
	
	
	/** enum hours*/
	@Enumerated(EnumType.STRING)
	@Column(name="hours")
	private Hours hours;
	
	/** one sheduleSlot has many teaching procedures */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="teachingId", nullable = true)
	private Teaching teaching;
	
	/** Default constructor*/
	public ScheduleSlot() {
		
	}
	
	/** Constructor*/
	public ScheduleSlot(Days days, Hours hours, Teaching teaching) {
		this.days = days;
		this.hours = hours;
		this.teaching = teaching;
	}
	
	/** getters*/
	public Long getScheduleId() {
		return scheduleId;
	}
	
	public Days getDays() {
		return days;
	}
	
	public Hours getHours() {
		return hours;
	}
	
	public Teaching getTeaching() {
		return teaching;
	}

	
	/** setters*/
	public void setDays(Days days) {
		this.days = days;
	}
	
	public void setHours(Hours hours) {
		this.hours = hours;
	}
	

	public void setTeaching(Teaching teaching) {
		this.teaching = teaching;
	}
	
	public void addTeaching(Teaching teaching) {
        if (teaching != null) {
        	teaching.addScheduleSlot(this);
        }
    }
	
	
	public void removeTeaching(Teaching teaching) {
        if (teaching != null) {
        	teaching.deleteScheduleSlot(this);
        }
        
    }
	
}