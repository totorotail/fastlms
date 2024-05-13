package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMainController {
    private final BannerService bannerService;

    @GetMapping("/admin/main.do")
    public String index(Model model) {
        List<BannerDto> list = bannerService.visibleSortList();

        model.addAttribute("list", list);
        return "admin/main";
    }
}
