package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class BannerInput {
    Long id;
    String name;
    String filePath;
    String alterText;
    String url;
    String targetInfo;
    int sortOrder;
    boolean visible;
    String idList;
}
