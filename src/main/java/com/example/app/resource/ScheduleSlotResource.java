package com.example.app.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.domain.ScheduleSlot;

@Path("scheduleslotss")
public class ScheduleSlotResource {
	
	private ScheduleSlotDAO scheduleSlotDAO;
	
	@GET
	@Produces("application/xml")
	public List<ScheduleSlot> listAllScheduleSlots(){
		
		List<ScheduleSlot> scheduleslots = scheduleSlotDAO.findAll();
		
		return scheduleslots;
	}

}
