package com.bobfriend.bobfriends.domain.file;

import com.bobfriend.bobfriends.domain.BaseEntity;
import com.bobfriend.bobfriends.support.utils.MultipartUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "path")
    private String path;

    @Column(name = "bytes")
    private long bytes;

    public static File multipartOf(MultipartFile multipartFile) {
        final String fileId = MultipartUtil.createFileId();
        final String format = MultipartUtil.getFormat(multipartFile.getContentType());
        return File.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .path(MultipartUtil.createPath(fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }
}
