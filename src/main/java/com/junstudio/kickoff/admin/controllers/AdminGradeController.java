package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.PatchGradeService;
import com.junstudio.kickoff.dtos.ApplicationFormDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminGradeController {
    private final PatchGradeService patchGradeService;

    public AdminGradeController(PatchGradeService patchGradeService) {
        this.patchGradeService = patchGradeService;
    }

    @PatchMapping("/grade")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchGrade(
        @RequestBody ApplicationFormDto applicationFormDto
    ) {
        patchGradeService.patch(
            applicationFormDto.getApplicationPostId(),
            applicationFormDto.getGrade(),
            applicationFormDto.getUserName()
        );
    }
}
