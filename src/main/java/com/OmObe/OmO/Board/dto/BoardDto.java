package com.OmObe.OmO.Board.dto;

import com.OmObe.OmO.Comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목을 입력하여 주십시오.")
        private String title;

        @NotBlank(message = "내용을 입력해주십시오.")
        private String content;

        /*
        QNA : QnA 게시판
        FREE : 자유 게시판
        TROUBLE : 고민 게시판
        */

        @Pattern(regexp = "^QNA|FREE|TROUBLE$")
        private String type;
    }

    @Getter
    @Setter
    public static class Patch{
        private long BoardId;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private long BoardId;
        private String title;
        private String content;
        private String tag;
        private String writer;
        private String profileURL;
        private LocalDateTime createdDate;
        private int likeCount;
        private int viewCount;
        private List<CommentDto.Response> comments;
    }
}
