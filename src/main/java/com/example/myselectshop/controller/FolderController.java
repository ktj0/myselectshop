package com.example.myselectshop.controller;

import com.example.myselectshop.dto.FolderRequestDto;
import com.example.myselectshop.dto.FolderResponseDto;
import com.example.myselectshop.security.UserDetailsImpl;
import com.example.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;
    // 폴더 생성
    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<String> folderNames = requestDto.getFolderNames();

        System.out.println("folderNames: " + folderNames);

        folderService.addFolders(folderNames, userDetails.getUser());
    }

    // 폴더 조회
    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return folderService.getFolders(userDetails.getUser());
    }
}
