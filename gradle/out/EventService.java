package com.example.restservice.out;

import com.example.restservice.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY");

    public Event createEvent(EventDto eventDto) throws ParseException {
        Event returnValue = new Event();
        returnValue.setType(eventDto.getType());
        returnValue.setTitle(eventDto.getTitle());
        returnValue.setDescription(eventDto.getDescription());
        returnValue.setLocation(eventDto.getLocation());
        returnValue.setDate(new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(eventDto.getDate()));

        return eventRepository.save(returnValue);
    }

    public List<Event> fetchEvent() {
        
        return eventRepository.findAll();
    }


    public Event viewEvent(String title) {

        return eventRepository.findByTitle(title);
    }

    public void deleteEvent(String title) throws Exception {
        Event event = eventRepository.findByTitle(title);
        if (title == null) {
            throw new Exception("Event " + event + " does not exist");
        } else {
            eventRepository.deleteById(event.getId());
        }
    }


    public Event editEvent(Event event) throws Exception {
        //  Optional<Event> createdEventOptional = eventRepository.findById(event.getId());

        Event createdEvent = eventRepository.findByTitle(event.getTitle());
        //!createdEventOptional.isPresent()
        if (createdEvent == null) {
            throw new Exception("Event does not exist");
        } else {
            //Event createdEvent = createdEventOptional.get();
            createdEvent.setTitle(event.getTitle());
            createdEvent.setType(event.getType());
            createdEvent.setDescription(event.getDescription());
            createdEvent.setLocation(event.getLocation());
                    createdEvent.setDate(new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(String.valueOf(event.getDate())));
//            returnValue.setDate(new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(eventDto.getDate()));
            createdEvent.setDate(event.getDate());

            eventRepository.save(createdEvent);
            return createdEvent;
            // return createdEventOptional.get();
        }
    }
}
