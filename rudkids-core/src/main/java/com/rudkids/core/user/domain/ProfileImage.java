package com.rudkids.core.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private ProfileImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProfileImage create(String path, String url) {
        return new ProfileImage(path, url);
    }
}
