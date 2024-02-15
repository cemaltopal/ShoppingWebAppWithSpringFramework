package com.project.contactmessage.controller;
import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import com.project.contactmessage.service.ContactMessageService;
import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contactMessages")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/save")//http://localhost:8080/contactMessages/save + POST + JSON
    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest){
        return contactMessageService.save(contactMessageRequest);
    }
    @GetMapping("/getAll")//http://localhost:8080/contactMessages/getAll + GET
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        return contactMessageService.getAll(page, size, sort, type);
    }
    @GetMapping("/searchByEmail") //http://localhost:8080/contactMessages/searchByEmail?email=aaa@bbb.com + GET
    public Page<ContactMessageResponse> searchByEmail(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        return contactMessageService.searchByEmail(email, page, size, sort, type);
    }

    @DeleteMapping("/deleteById/{contactMessageId}") // http://localhost:8080/contactMessages/deleteById/2 + DELETE
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }

    @DeleteMapping("/deleteByIdParam")//http://localhost:8080/contactMessages/deleteByIdParam?contactMessageId=2
    public ResponseEntity<String> deleteById(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }
    @GetMapping("/searcBetweeenDates")//http://localhost:8080/contactMessages/searchBetweenDates?beginDate=2023-09-13&endDate=2023-09-15   + GET
    public ResponseEntity<List<ContactMessage>> searchBetweenDate(
            @RequestParam(value = "beginDate") String begindateString,
            @RequestParam(value = "endDate") String endDateString
    ){
        List<ContactMessage> contactMessage = contactMessageService.searchBetweenDates(begindateString, endDateString);
        return ResponseEntity.ok(contactMessage);
    }

    @GetMapping("/searchBetweenTimes")//http://localhost:8080/contactMessages/searchBetweenTimes?startHour=09&startMinute=00&endHour=17&endMinute=30  + GET
    public ResponseEntity<List<ContactMessage>> searchBetweenTimes(
            @RequestParam(value = "startHour") String startHourString,
            @RequestParam(value = "startMinute") String startMinuteString,
            @RequestParam(value = "endHour") String endHourString,
            @RequestParam(value = "endMinute") String endMinuteString
    ){
        List<ContactMessage> contactMessages = contactMessageService.searchBetweenTimes(startHourString, startMinuteString, endHourString, endMinuteString);
        return ResponseEntity.ok(contactMessages);
    }
}
