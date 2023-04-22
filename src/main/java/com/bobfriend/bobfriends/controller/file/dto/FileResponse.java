package com.bobfriend.bobfriends.controller.file.dto;

import com.bobfriend.bobfriends.domain.file.File;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {
    private String id;
    private String name;
    private String format;
    private String path;
    private long bytes;

    public FileResponse(File file) {
        id = file.getId();
        name = file.getName();
        format = file.getFormat();
        path = file.getPath();
        bytes = file.getBytes();
    }
}
