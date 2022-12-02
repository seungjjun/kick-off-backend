package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetUserAdminService;
import com.junstudio.kickoff.admin.services.PatchUserAdminService;
import com.junstudio.kickoff.dtos.SelectedUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/admin-users")
public class AdminUserController {
    private final GetUserAdminService getUserAdminService;
    private final PatchUserAdminService patchUserAdminService;

    public AdminUserController(GetUserAdminService getUserAdminService,
                               PatchUserAdminService patchUserAdminService) {
        this.getUserAdminService = getUserAdminService;
        this.patchUserAdminService = patchUserAdminService;
    }

    @GetMapping
    private UsersDto users() {
        return getUserAdminService.users();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void changeGrade(
        @RequestBody SelectedUsersDto selectedUsersDto
    ) {
        patchUserAdminService.patch(selectedUsersDto.usersId, selectedUsersDto.getGrade());
    }
}
