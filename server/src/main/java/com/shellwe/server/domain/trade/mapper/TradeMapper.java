package com.shellwe.server.domain.trade.mapper;

import com.shellwe.server.domain.category.entity.Category;
import com.shellwe.server.domain.member.dto.response.FindResponseDto;
import com.shellwe.server.domain.member.entity.Member;
import com.shellwe.server.domain.picture.dto.PictureResponseDto;
import com.shellwe.server.domain.picture.entity.Picture;
import com.shellwe.server.domain.shell.dto.response.ShellResponseDto;
import com.shellwe.server.domain.shell.entity.Shell;
import com.shellwe.server.domain.tag.dto.TagResponseDto;
import com.shellwe.server.domain.tag.entity.Tag;
import com.shellwe.server.domain.trade.dto.response.MainPageResponseDto;
import com.shellwe.server.domain.trade.dto.response.ShellForMyTradeResponseDetails;
import com.shellwe.server.domain.trade.dto.response.UpdateTradeStatusResponseDto;
import com.shellwe.server.domain.types.category.ShellCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TradeMapper {

    @Mapping(target = "picture", expression = "java(mapFirstPictureToDto(shell.getPictureUrls()))")
    @Mapping(target = "type", source = "shell.shellType")
    @Mapping(target = "category", source = "shell.category.shellCategory")
    ShellResponseDto shellToShellResponseDto(Shell shell);

    default String mapFirstPictureToDto(List<Picture> pictures) {
        if (pictures != null && !pictures.isEmpty()) {
            return pictures.get(0).getUrl();
        }
        return null;
    }

    List<ShellResponseDto> sellerTradeListToMyTradeResponseDto(List<Shell> sellerShells);

    List<ShellResponseDto> buyerTradeListToMyTradeResponseDto(List<Shell> buyerShells);

    @Mapping(target = "pictures", source = "shell.pictureUrls", qualifiedByName = "mapPicturesToDto")
    @Mapping(target = "type", source = "shell.shellType")
    @Mapping(target = "category", source = "shell.category.shellCategory")
    ShellForMyTradeResponseDetails shellToShellForMyTradeResponseDetails(Shell shell);

    List<ShellForMyTradeResponseDetails> buyerTradeListToMyTradeResponseDtoDetails(List<Shell> buyerShells);

    @Mapping(target = "picture", expression = "java(mapFirstPictureToDto(shell.getPictureUrls()))")
    @Mapping(target = "type", source = "shell.shellType")
    @Mapping(target = "category", source = "shell.category.shellCategory")
    MainPageResponseDto shellToMainPageResponseDto(Shell shell);

    List<MainPageResponseDto> shellsToInquiryToMainResponseDto(List<Shell> shells);

    @Mapping(target = "member", source = "shell.member", qualifiedByName = "mapMemberToDto")
    @Mapping(target = "pictures", source = "shell.pictureUrls", qualifiedByName = "mapPicturesToDto")
    @Mapping(target = "tags", source = "shell.tags", qualifiedByName = "mapTagsToDto")
    @Mapping(target = "category", source = "shell.category", qualifiedByName = "mapCategoryToDto")
    @Mapping(target = "type", source = "shell.shellType")
    UpdateTradeStatusResponseDto shellToUpdateTradeStatusResponseDto(Shell shell);

    @Named("mapPicturesToDto")
    default List<PictureResponseDto> mapPicturesToDto(List<Picture> pictures) {
        return pictures.stream()
                .map(picture -> {
                    PictureResponseDto dto = new PictureResponseDto();
                    dto.setOrder(picture.getOrder());
                    dto.setUrl(picture.getUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Named("mapMemberToDto")
    default FindResponseDto mapMemberToDto(Member member) {
        FindResponseDto responseDto = new FindResponseDto();
        responseDto.setId(member.getId());
        responseDto.setDisplayName(member.getDisplayName());
        responseDto.setProfileUrl(member.getProfileUrl());
        return responseDto;
    }

    @Named("mapTagsToDto")
    default List<TagResponseDto> mapTagsToDto(List<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }
        return tags.stream()
                .map(tag -> {
                    TagResponseDto dto = new TagResponseDto();
                    dto.setTagName(tag.getTagName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Named("mapCategoryToDto")
    default ShellCategory mapCategoryToDto(Category category) {
        return category.getShellCategory();
    }
}
