package com.shellwe.server.domain.shell.dto.request;

import com.shellwe.server.domain.types.ShellType;
import com.shellwe.server.domain.types.category.ShellCategory;
import com.shellwe.server.utils.customannotation.EachSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UpdateRequestDto {

    @NotBlank(message = "title required")
    @Size(max = 10, message = "Title should be within 10 characters")
    private String title;

    @NotBlank(message = "body required")
    @Size(max = 1500, message = "Body should be within 1500 characters")
    private String body;

    @NotNull(message = "type required")
    private ShellType type;

    @NotNull(message = "category required")
    private ShellCategory category;

    @EachSize(max = 20)
    private List<String> tags;

    private int pictureOrder;

    @Size(min = 1, max = 4, message = "The number of image files should be between 1 and 4")
    private MultipartFile pictureFile;
}
