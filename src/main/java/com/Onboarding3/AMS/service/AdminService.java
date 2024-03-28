package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.entity.Owner;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    Admin saveAdmin(Admin admin);

    void deleteAdmin(Integer adminId);

    List<Admin> getAllAdmins();

    Admin getAdminById(Integer adminId);

    Admin updateAdmin(Integer adminId, Admin admin);

}
