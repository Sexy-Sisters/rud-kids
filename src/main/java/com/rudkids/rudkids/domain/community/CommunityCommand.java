package com.rudkids.rudkids.domain.community;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.Builder;

public class CommunityCommand {

    @Builder
    public record Create(
        String title,
        String content,
        String writer,
        String type
    ) {
        public Community toEntity(User user) {
            var title = Title.create(title());
            var content = Content.create(content());
            return Community.create(user, title, content);
        }
    }

    @Builder
    public record Update(
        String title,
        String content,
        String writer
    ) {
    }
}
