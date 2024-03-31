package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.*;
import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.repository.AdminRepository;
import io.grpc.Status;
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

    @Override
    public void getAdmin(GetAdminRequest request, StreamObserver<AdminResponse> responseObserver) {
        int adminId = request.getAdminId();
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if (admin != null) {
            AdminResponse response = AdminResponse.newBuilder()
                    .setAdminId(admin.getAdminId())
                    .setName(admin.getName())
                    .setEmail(admin.getEmail())
                    .setAcc(admin.getAcc())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Admin with ID " + adminId + " not found.")
                    .asRuntimeException());
        }
    }

    @Override
    public void updateAdmin(UpdateAdminRequest request, StreamObserver<AdminResponse> responseObserver) {
        int adminId = request.getAdminId();
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if (admin != null) {
            admin.setName(request.getName());
            admin.setEmail(request.getEmail());
            admin.setAcc(request.getAcc());

            Admin updatedAdmin = adminRepository.save(admin);

            AdminResponse response = AdminResponse.newBuilder()
                    .setAdminId(updatedAdmin.getAdminId())
                    .setName(updatedAdmin.getName())
                    .setEmail(updatedAdmin.getEmail())
                    .setAcc(updatedAdmin.getAcc())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Admin with ID " + adminId + " not found.")
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteAdmin(DeleteAdminRequest request, StreamObserver<DeleteAdminResponse> responseObserver) {
        int adminId = request.getAdminId();
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if (admin != null) {
            adminRepository.delete(admin);

            DeleteAdminResponse response = DeleteAdminResponse.newBuilder()
                    .setSuccess(true)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Admin with ID " + adminId + " not found.")
                    .asRuntimeException());
        }
    }
}

