package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.FoundUserDto;
import com.junstudio.kickoff.dtos.RegistrationRequestDto;
import com.junstudio.kickoff.dtos.RegistrationResultDto;
import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.dtos.UserInformationDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.AlreadyExistingIdentification;
import com.junstudio.kickoff.exceptions.PasswordNotMatched;
import com.junstudio.kickoff.exceptions.PatchFailed;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.CreateUserService;
import com.junstudio.kickoff.services.GetUserService;
import com.junstudio.kickoff.services.PatchUserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UserController {
    private final GetUserService getUserService;
    private final PatchUserService patchUserService;
    private final CreateUserService createUserService;

    public UserController(GetUserService getUserService,
                          PatchUserService patchUserService,
                          CreateUserService createUserService) {
        this.getUserService = getUserService;
        this.patchUserService = patchUserService;
        this.createUserService = createUserService;
    }

    @GetMapping("/users")
    private UsersDto users() {
        List<UserDto> users = getUserService.users()
            .stream().map(User::toDto)
            .collect(Collectors.toList());

        return new UsersDto(users);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    private RegistrationResultDto register(
        @Valid @RequestBody RegistrationRequestDto registrationRequestDto
    ) {
        User user = createUserService.register(
            registrationRequestDto.getName(),
            registrationRequestDto.getIdentification(),
            registrationRequestDto.getPassword(),
            registrationRequestDto.getConfirmPassword()
        );

        return new RegistrationResultDto(user.name());
    }

    @GetMapping("/user")
    public FoundUserDto user(
        @RequestAttribute("identification") String identification,
        String userName
    ) {
        UsersDto usersDto = getUserService.findUser(userName, identification);

        return new FoundUserDto(usersDto);
    }

    @GetMapping("/users/me")
    public UserInformationDto information(
        @RequestAttribute("identification") String identification
    ) {
        UsersDto usersDto = getUserService.findMyInformation(identification);

        return new UserInformationDto(usersDto);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
        @RequestBody UserDto userDto,
        @PathVariable Long userId
    ) {
        patchUserService.patch(userId, userDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InValidRequest(MethodArgumentNotValidException exception) {
        BindingResult errors = exception.getBindingResult();

        for(ObjectError error : errors.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return "Bad Request";
    }

    @ExceptionHandler(PatchFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PatchFailed patchFailed() {
        return new PatchFailed();
    }

    @ExceptionHandler(AlreadyExistingIdentification.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String emailAlreadyTaken() {
        return "Email already existing!";
    }

    @ExceptionHandler(PasswordNotMatched.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordNotMatch() {
        return "Password do not match!";
    }
}
