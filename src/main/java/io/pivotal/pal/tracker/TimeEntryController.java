package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping(value="/time-entries",method = RequestMethod.POST)
    public  @ResponseBody
    ResponseEntity<TimeEntry> create (@RequestBody TimeEntry timeEntry) {
        HttpHeaders httpreshed = new HttpHeaders();
        TimeEntry timeEntry1 = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.CREATED);
    }

    @RequestMapping(value="/time-entries",method = RequestMethod.GET)
    public  @ResponseBody
    ResponseEntity<List<TimeEntry>> list () {
        List<TimeEntry> listentry =  timeEntryRepository.list();
        HttpHeaders httpreshed = new HttpHeaders();
        return new ResponseEntity<List<TimeEntry>>(listentry, httpreshed, HttpStatus.OK);
    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.GET)
    public  @ResponseBody
    ResponseEntity<TimeEntry> read (@PathVariable(value="id") Long id) {
        HttpHeaders httpreshed = new HttpHeaders();
        TimeEntry timeEntry1 = timeEntryRepository.find(id);
        if(null!=timeEntry1){
            return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.PUT)
    public  @ResponseBody
    ResponseEntity<TimeEntry> update (@PathVariable(value="id") Long id, @RequestBody TimeEntry timeEntry) {
        HttpHeaders httpreshed = new HttpHeaders();
        TimeEntry timeEntry1 = timeEntryRepository.update(id, timeEntry);
        if(null!=timeEntry1){
            return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.DELETE)
    public  @ResponseBody
    ResponseEntity<Void> delete (@PathVariable(value="id") Long id) {
        HttpHeaders httpreshed = new HttpHeaders();
        timeEntryRepository.delete(id);
        return new ResponseEntity<Void>(httpreshed, HttpStatus.NO_CONTENT);
    }

}
