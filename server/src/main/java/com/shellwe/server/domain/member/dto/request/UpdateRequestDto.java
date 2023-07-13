package com.shellwe.server.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateRequestDto {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*])[a-zA-Z\\d!@#$%&*]{8,}$",
            message = "Must contain at least 8 characters, including lowercase letters, numbers, and special symbols.")
    private String password;

    @Size(min = 1, max = 8, message = "The displayname size must be greater than 0 and less than 8.")
    private String displayName;

    private String introduction;

    private MultipartFile pictureFile;
}
