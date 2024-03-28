package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.repository.AdminRepository;
import com.Onboarding3.AMS.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
//    private final AdminRepository adminRepository;

    @Autowired
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        adminRepository.deleteById(adminId);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("Admin with ID " + adminId + " not found"));
    }

    @Override
    public Admin updateAdmin(Integer adminId, Admin admin) {
        Admin existingAdmin = adminRepository.findById(adminId)
                .orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setName(admin.getName());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setAccountName(admin.getAccountName());
            return adminRepository.save(existingAdmin);
        }
        return null;
    }


}
