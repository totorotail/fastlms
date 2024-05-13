package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .id(parameter.getId())
                .name(parameter.getName())
                .filePath(parameter.getFilePath())
                .alterText(parameter.getAlterText())
                .url(parameter.getUrl())
                .targetInfo(parameter.getTargetInfo())
                .sortOrder(parameter.getSortOrder())
                .visible(parameter.isVisible())
                .regDt(LocalDateTime.now())
                .build();
        bannerRepository.save(banner);
        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> banner = bannerRepository.findById(parameter.getId());
        if (banner.isPresent()) {
            Banner existingBanner = banner.get();
            existingBanner.setName(parameter.getName());
            existingBanner.setFilePath(parameter.getFilePath());
            existingBanner.setAlterText(parameter.getAlterText());
            existingBanner.setUrl(parameter.getUrl());
            existingBanner.setTargetInfo(parameter.getTargetInfo());
            existingBanner.setSortOrder(parameter.getSortOrder());
            existingBanner.setVisible(parameter.isVisible());
            existingBanner.setRegDt(LocalDateTime.now());
            bannerRepository.save(existingBanner);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        return bannerMapper.selectList(parameter);
    }

    @Override
    public List<BannerDto> visibleSortList() {
        return bannerMapper.selectVisibleSortList(true);
    }

    @Override
    public BannerDto getById(Long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && !idList.isEmpty()) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = Long.parseLong(x);

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }
}
