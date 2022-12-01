package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetUserAdminService;
import com.junstudio.kickoff.dtos.UsersDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminUserController {
    private final GetUserAdminService getUserAdminService;

    public AdminUserController(GetUserAdminService getUserAdminService) {
        this.getUserAdminService = getUserAdminService;
    }

    @GetMapping("/admin-users")
    private UsersDto users() {
        return getUserAdminService.users();
    }
}
