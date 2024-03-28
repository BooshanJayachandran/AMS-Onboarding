package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        if (admin.getAdminId() != null) {
            return ResponseEntity.badRequest().body("Admin ID must not be provided in the request body.");
        }

        Admin createdAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok().body("Admin " + createdAdmin.getAdminId() + " created successfully.");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam Integer adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok().body("Admin " + adminId + " deleted successfully.");
    }


    @GetMapping("/getall")
    public ResponseEntity<Object> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok().body(admins);
    }

    @GetMapping("/get")
    public ResponseEntity<Admin> getAdminById(@RequestParam Integer adminId) {
        Admin admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok().body(admin);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestParam Integer adminId, @RequestBody Admin admin) {
        Admin existingAdmin = adminService.getAdminById(adminId);

        if (existingAdmin == null) {
            return ResponseEntity.notFound().build();
        }

        existingAdmin.setName(admin.getName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setAccountName(admin.getAccountName());

        adminService.saveAdmin(existingAdmin);

        return ResponseEntity.ok().body("Admin " + adminId + " updated successfully.");
    }
}
