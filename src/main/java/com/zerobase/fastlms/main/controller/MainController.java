package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;
    private final BannerService bannerService;

    @RequestMapping("/")
    public String login(HttpServletRequest request) {
        String clientIp = RequestUtils.getClientIP(request);
        String userAgent = RequestUtils.getUserAgent(request);

        Principal principal = request.getUserPrincipal();
        String userId = (principal != null) ? principal.getName() : null;

        if (userId != null) {
            ServiceResult serviceResult = memberService.updateLoginHistory(userId, clientIp, userAgent);
        }

        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/main.do";
        }

        return "redirect:/member/main.do";
    }

    @GetMapping("/member/main.do")
    public String index(Model model) {
        List<BannerDto> list = bannerService.visibleSortList();

        model.addAttribute("list", list);
        return "index";
    }


    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
