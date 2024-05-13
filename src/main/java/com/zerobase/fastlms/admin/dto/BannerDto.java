package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {

    Long id;
    String name;
    String filePath;
    String alterText;
    String url;
    String targetInfo;
    int sortOrder;
    boolean visible;
    LocalDateTime regDt;


    public static List<BannerDto> of(List<Banner> banners) {
        if (banners != null) {
            List<BannerDto> bannerList = new ArrayList<>();
            for (Banner x : banners) {
                bannerList.add(of(x));
            }
            return bannerList;
        }

        return null;
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .filePath(banner.getFilePath())
                .alterText(banner.getAlterText())
                .url(banner.getUrl())
                .targetInfo(banner.getTargetInfo())
                .sortOrder(banner.getSortOrder())
                .visible(banner.isVisible())
                .regDt(banner.getRegDt())
                .build();
    }
}
