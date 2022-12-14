package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.DeleteUserAdminService;
import com.junstudio.kickoff.admin.services.GetUserAdminService;
import com.junstudio.kickoff.admin.services.PatchUserAdminService;
import com.junstudio.kickoff.dtos.AdminDto;
import com.junstudio.kickoff.dtos.RegistrationRequestDto;
import com.junstudio.kickoff.dtos.SearchedUserDto;
import com.junstudio.kickoff.dtos.SelectedUsersDto;
import com.junstudio.kickoff.dtos.TodaySignupUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.AdminNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminUserController {
    private final GetUserAdminService getUserAdminService;
    private final PatchUserAdminService patchUserAdminService;
    private final DeleteUserAdminService deleteUserAdminService;

    public AdminUserController(GetUserAdminService getUserAdminService,
                               PatchUserAdminService patchUserAdminService,
                               DeleteUserAdminService deleteUserAdminService) {
        this.getUserAdminService = getUserAdminService;
        this.patchUserAdminService = patchUserAdminService;
        this.deleteUserAdminService = deleteUserAdminService;
    }

    @GetMapping("/admin")
    private AdminDto admin(
        @RequestAttribute("identification") String identification
    ) {
        return getUserAdminService.admin(identification);
    }

    @PostMapping("/admin-register")
    @ResponseStatus(HttpStatus.CREATED)
    private void register(
        @RequestBody RegistrationRequestDto registrationRequestDto
    ) {
        getUserAdminService.register(
            registrationRequestDto.getName(),
            registrationRequestDto.getIdentification(),
            registrationRequestDto.getPassword()
        );
    }

    @GetMapping("/admin-users")
    private UsersDto users() {
        return getUserAdminService.users();
    }

    @GetMapping("/admin-user")
    private SearchedUserDto searchedUser(String userName) {
        return getUserAdminService.search(userName);
    }

    @GetMapping("/admin-today-signup-users")
    private TodaySignupUsersDto todaySignupUser() {
        return getUserAdminService.todaySignupUser();
    }

    @PatchMapping("/admin-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void changeGrade(
        @RequestBody SelectedUsersDto selectedUsersDto
    ) {
        patchUserAdminService.patch(selectedUsersDto.usersId, selectedUsersDto.getGrade());
    }

    @DeleteMapping("/admin-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void removeUser(
        @RequestBody SelectedUsersDto selectedUsersDto
    ) {
        deleteUserAdminService.delete(selectedUsersDto.usersId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String userNotFound() {
        return "????????? ?????? ??? ????????????.";
    }

    @ExceptionHandler(AdminNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private AdminNotFound adminNotFound() {
        return new AdminNotFound();
    }
}
