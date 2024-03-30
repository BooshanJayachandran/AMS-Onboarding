package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.AdminDelete;
import com.Onboarding3.AMS.AdminDetails;
import com.Onboarding3.AMS.AdminServiceGrpc;
import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.repository.AdminRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AdminGrpcServiceImpl extends AdminServiceGrpc.AdminServiceImplBase {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void createAdmin(AdminDetails request, StreamObserver<AdminDetails> responseObserver) {


        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setAcc(request.getAcc());

        Integer createdAdminId = adminRepository.save(admin).getAdminId();
        AdminDetails adminDetails = AdminDetails.newBuilder()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .setAcc(request.getAcc())
                .setAdminId(createdAdminId)
                .build();
//        request.toBuilder().setAdminId(createdAdminId).build();
        responseObserver.onNext(adminDetails);
        responseObserver.onCompleted();
    }


}
