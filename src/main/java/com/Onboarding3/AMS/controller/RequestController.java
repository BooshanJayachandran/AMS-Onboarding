package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.Request;
import com.Onboarding3.AMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/create")
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @PutMapping("/updateStatus")
    public Request updateRequestStatus(@RequestParam Integer requestId, @RequestParam String status) {
        return requestService.updateRequestStatus(requestId, status);
    }

    @GetMapping("/getByAmenityId")
    public List<Request> getByAmenityId(@RequestParam Integer amenityId) {
        return requestService.getByAmenityId(amenityId);
    }

    @GetMapping("/getByOwnerId")
    public List<Request> getByOwner(@RequestParam Integer ownerId) {
        return requestService.getByOwner(ownerId);
    }
}
