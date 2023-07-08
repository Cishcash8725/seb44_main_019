package com.shellwe.server.domain.shell.controller;

import com.shellwe.server.auth.memberDetails.MemberContextInform;
import com.shellwe.server.domain.shell.dto.request.RegisterRequestDto;
import com.shellwe.server.domain.shell.dto.request.UpdateRequestDto;
import com.shellwe.server.domain.shell.dto.request.UpdateTradeStatusRequestDto;
import com.shellwe.server.domain.shell.dto.response.InquiryResponseDto;
import com.shellwe.server.domain.shell.dto.response.SearchResponseDto;
import com.shellwe.server.domain.shell.dto.response.UpdateTradeStatusResponseDto;
import com.shellwe.server.domain.shell.dto.response.FindDetailsResponseDto;
import com.shellwe.server.domain.shell.dto.response.RegisterResponseDto;
import com.shellwe.server.domain.shell.dto.response.UpdateResponseDto;
import com.shellwe.server.domain.shell.service.ShellService;
import com.shellwe.server.domain.types.ShellType;
import com.shellwe.server.domain.types.category.ShellCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequestMapping("/shells")
@RestController
public class ShellController {

    private final ShellService shellService;

    @Autowired
    public ShellController(ShellService shellService) {
        this.shellService = shellService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RegisterResponseDto registerShell(@RequestPart("register") RegisterRequestDto registerRequestDto,
                                             @RequestPart("pictures") List<MultipartFile> pictures,
                                             Authentication authentication) {
        return shellService.register(registerRequestDto, getId(authentication), pictures);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{shellId}")
    public FindDetailsResponseDto findShellDetails(@PathVariable long shellId, Authentication authentication) {
        return shellService.findDetails(shellId, getId(authentication));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{shellId}/update")
    public UpdateResponseDto updateShell(@PathVariable long shellId,
                                         @RequestPart("update") UpdateRequestDto updateRequestDto,
                                         @RequestPart("picture") MultipartFile picture,
                                         Authentication authentication) {
        return shellService.update(shellId, updateRequestDto, getId(authentication), picture);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{shellId}")
    public void deleteShell(@PathVariable long shellId, Authentication authentication) {
        shellService.delete(shellId, getId(authentication));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{shellId}")
    public UpdateTradeStatusResponseDto updateShellTradeStatus(@PathVariable long shellId,
                                                               @RequestBody UpdateTradeStatusRequestDto updateTradeStatusRequestDto,
                                                               Authentication authentication) {
        return shellService.updateTradeStatus(shellId, updateTradeStatusRequestDto, getId(authentication));
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/main")
//    public InquiryToMainResponseDto inquiryShellsToMain(@RequestParam("size") int size) {
//        return shellService.inquiryToMain(size);
//    }
//    트레이드 도메인에서 구현

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/shells")
//    public InquiryResponseDto inquiryShells(@RequestParam("limit") int limit,
//                                            @RequestParam("cursor") int cursor,
//                                            @RequestParam("type") ShellType shellType,
//                                            @RequestParam("category") ShellCategory shellCategory) {
//        return shellService.inquiry(limit, cursor, shellType, shellCategory);
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/search")
//    public SearchResponseDto searchShells(@RequestParam("limit") int limit,
//                                          @RequestParam("cursor") int cursor,
//                                          @RequestParam("title") String title) {
//        return shellService.search(limit, cursor, title);
//    }

    private Long getId(Authentication authentication) {
        Long id;
        if (authentication == null) {
            id = null;
        } else {
            MemberContextInform memberInform = (MemberContextInform) authentication.getPrincipal();
            id = memberInform.getId();
        }
        return id;
    }
}
