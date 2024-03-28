package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Request;
import com.Onboarding3.AMS.entity.RequestStatus;
import com.Onboarding3.AMS.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request createRequest(Request request) {
        request.setRequestDateTime(LocalDateTime.now());
        calculateETA(request);
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequestStatus(Integer requestId, String status) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if (request != null) {
            if (status.equals(RequestStatus.COMPLETED.toString())) {
                List<Request> pendingRequests = requestRepository
                        .findByStatusOrderByRequestDateTime(RequestStatus.NOT_STARTED);
                if (!pendingRequests.isEmpty()) {
                    Request nextRequest = pendingRequests.get(0);
                    nextRequest.setStatus(RequestStatus.IN_PROGRESS);
                    requestRepository.save(nextRequest);
                }
            }
            request.setStatus(RequestStatus.valueOf(status));
            return requestRepository.save(request);
        }
        return null;
    }

    @Override
    public List<Request> getByAmenityId(Integer amenityId) {
        return requestRepository.findByAmenityId(amenityId);
    }

    @Override
    public List<Request> getByOwner(Integer ownerId) {
        return requestRepository.findByOwnerId(ownerId);
    }



    private void calculateETA(Request request) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Request> latestRequests = requestRepository.findByAmenityIdOrderByRequestDateTimeDesc(request.getAmenityId());

        if (!latestRequests.isEmpty()) {
            Request latestRequest = latestRequests.get(0);
            LocalDateTime latestEta = latestRequest.getEta();

            request.setEta(latestEta.plusDays(1));

            if (request.equals(latestRequest)) {
                request.setStatus(RequestStatus.IN_PROGRESS);
            } else {
                request.setStatus(RequestStatus.NOT_STARTED);
            }
        } else {
            request.setEta(currentDateTime.plusDays(1));
            request.setStatus(RequestStatus.IN_PROGRESS);
        }
    }


}
