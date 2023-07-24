package com.shellwe.server.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class DeleteRequestDto {

    private String password;
}
