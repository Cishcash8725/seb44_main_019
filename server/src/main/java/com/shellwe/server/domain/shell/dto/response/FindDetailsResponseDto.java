package com.shellwe.server.domain.shell.dto.response;

import com.shellwe.server.domain.member.dto.response.FindResponseDto;
import com.shellwe.server.domain.picture.dto.PictureResponseDto;
import com.shellwe.server.domain.tag.dto.TagResponseDto;
import com.shellwe.server.domain.types.ShellType;
import com.shellwe.server.domain.types.Status;
import com.shellwe.server.domain.types.category.ShellCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FindDetailsResponseDto {

    private Long id;

    private ShellType type;

    private String title;

    private List<PictureResponseDto> pictures;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private ShellCategory category;

    private List<TagResponseDto> tags;

    private Status status;

    private FindResponseDto member;
}
