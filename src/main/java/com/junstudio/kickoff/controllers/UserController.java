package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.dtos.UserInformationDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.PatchFailed;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.GetUserService;
import com.junstudio.kickoff.services.PatchUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {
    private final GetUserService getUserService;
    private final PatchUserService patchUserService;

    public UserController(GetUserService getUserService,
                          PatchUserService patchUserService) {
        this.getUserService = getUserService;
        this.patchUserService = patchUserService;
    }

    @GetMapping()
    private UsersDto users() {
        List<UserDto> users = getUserService.users()
            .stream().map(User::toDto)
            .collect(Collectors.toList());

        return new UsersDto(users);
    }

    @GetMapping("me")
    public UserDto user(
        @RequestAttribute("identification") String identification
    ) {
        User user = getUserService.findMyInformation(identification);
        return user.toDto();
    }

    @GetMapping("{userId}")
    public UserInformationDto information(
        @PathVariable Long userId,
        @RequestAttribute("identification") String identification
    ) {
        UsersDto usersDto = getUserService.findUser(userId, identification);

        return new UserInformationDto(usersDto);
    }

    @PatchMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
        @RequestBody UserDto userDto,
        @PathVariable Long userId
    ) {
        patchUserService.patch(userId, userDto);
    }

    @ExceptionHandler(PatchFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PatchFailed patchFailed() {
        return new PatchFailed();
    }
}
