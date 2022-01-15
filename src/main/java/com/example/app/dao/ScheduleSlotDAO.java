package com.example.app.dao;

import java.util.List;

import com.example.app.domain.ScheduleSlot;

public interface ScheduleSlotDAO {
	
	public ScheduleSlot find(long scheduleId);

	void save(ScheduleSlot scheduleSlot);

	void delete(ScheduleSlot scheduleSlot);

	List<ScheduleSlot> findAll();

}