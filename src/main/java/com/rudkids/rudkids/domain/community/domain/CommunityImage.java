package com.rudkids.rudkids.domain.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityImage {

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private CommunityImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static CommunityImage create(String path, String url) {
        return new CommunityImage(path, url);
    }

    public boolean hasImage() {
        return !path.isBlank() && !url.isBlank();
    }
}
