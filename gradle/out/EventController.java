package com.example.restservice.out;

import com.example.restservice.dto.ApiResponse;
import com.example.restservice.dto.EventDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Log4j2
@RestController

public class EventController {
 @Autowired
 private EventService eventService;

    @PostMapping(value = "/event",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Event createEvent(@RequestBody EventDto eventDto) throws ParseException {
        return eventService.createEvent(eventDto);
    }

          @GetMapping("/event")
        @ResponseBody
          public ApiResponse fetchEvent() {
              ApiResponse apiResponse = new ApiResponse();

              try {
                  apiResponse.setData(eventService.fetchEvent());
                  apiResponse.setSuccess(true);
              } catch (Exception e) {
                  apiResponse.setSuccess(false);
                  apiResponse.setMessage("An error occurred while fetching events");
              }
              return apiResponse;
          }
          
   @GetMapping("/event/{eventTitle}")
    @ResponseBody
    public ApiResponse viewEvent(@PathVariable String eventTitle) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(eventService.viewEvent(eventTitle));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching event:" + eventTitle);

        }
        return apiResponse;
    }

    @DeleteMapping(value = "/event/{eventTitle}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse deleteEvent(@PathVariable String eventTitle) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            eventService.deleteEvent(eventTitle);
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while deleting event:" + eventTitle);
        }
        return apiResponse;

    }


    @PatchMapping(value = "/event/{eventTitle}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse editEvent(@RequestBody Event event) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(eventService.editEvent(event));
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while updating event");

//            apiResponse.setMessage("An error occurred while updating event:" + event.getId());
            log.info(e);
        }
        return apiResponse;
    }

}
