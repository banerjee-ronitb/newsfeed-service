package com.example.newsfeedservice.messaging;

import java.util.Date;
import java.util.UUID;

public interface Event {

	UUID getEventId();
	
	Date getDate();
}
