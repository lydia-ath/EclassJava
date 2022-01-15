package com.example.app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScheduleSlotTest {
	
	ScheduleSlot scheduleSlot;
	Teaching teaching;
	
	@BeforeEach
    public void setUp() {
		scheduleSlot = new ScheduleSlot();
		teaching = new Teaching();
		scheduleSlot.setDays(Days.Friday);
		scheduleSlot.setHours(Hours.fifth_Hour);
		scheduleSlot.setTeaching(teaching);
		teaching.addScheduleSlot(scheduleSlot);
    }
	
	@Test
    public void succefulSetUp() {
        teachingBidirectionalAssociationInvariant(scheduleSlot);
        successfullAdditionofTeaching(scheduleSlot, teaching, 1);
    }

	private void successfullAdditionofTeaching(ScheduleSlot scheduleSlot, Teaching teaching, int expected) {
		Assertions.assertEquals(expected , teaching.getScheduleSlots().size());
		
	}

	private void teachingBidirectionalAssociationInvariant(ScheduleSlot scheduleSlot) {
		Assertions.assertSame(scheduleSlot, teaching.getScheduleSlots().get(0));
		
	}
	
	@Test
    public void addNullAsTeaching() {
		scheduleSlot.setTeaching(null);
		Assertions.assertEquals(null, scheduleSlot.getTeaching());
        teachingBidirectionalAssociationInvariant(scheduleSlot);
    }
	
	@Test
    public void addTeaching() {        
	 	teaching = new Teaching();
	 	scheduleSlot.setTeaching(teaching);
	 	scheduleSlot.addTeaching(teaching);
	 	teaching.addScheduleSlot(scheduleSlot);
	 	successfullAdditionofTeaching(scheduleSlot, teaching, 2);
    }
	
	@Test
    public void checkHours() {    
		Assertions.assertEquals(Hours.fifth_Hour, scheduleSlot.getHours());
    }
	
	@Test
    public void checkDays() {    
		Assertions.assertEquals(Days.Friday, scheduleSlot.getDays());
    }
	
	@Test
    public void removeNullAsTeaching() {
		scheduleSlot.removeTeaching(null);
        teachingBidirectionalAssociationInvariant(scheduleSlot); 
        //Assertions.assertNotEquals(1, teaching.getScheduleSlots().size());
    }
	
	@Test
    public void removeTeaching() {
        teachingBidirectionalAssociationInvariant(scheduleSlot);
        scheduleSlot.removeTeaching(teaching);
        successfullRemovalofTeaching(scheduleSlot, teaching);
        //expectedTeachingSize(scheduleSlot,0);        
    }

	private void successfullRemovalofTeaching(ScheduleSlot scheduleSlot, Teaching teaching) {
		Assertions.assertEquals(0, teaching.getScheduleSlots().size());
		
	}
	
}