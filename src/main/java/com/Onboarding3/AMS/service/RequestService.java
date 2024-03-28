package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Request;

import java.util.List;

public interface RequestService {
    Request createRequest(Request request);
    Request updateRequestStatus(Integer requestId, String status);
    List<Request> getByAmenityId(Integer amenityId);
    List<Request> getByOwner(Integer ownerId);

}
