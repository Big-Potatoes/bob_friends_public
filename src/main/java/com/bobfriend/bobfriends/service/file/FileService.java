package com.bobfriend.bobfriends.service.file;

import com.bobfriend.bobfriends.domain.file.File;
import com.bobfriend.bobfriends.repository.FileRepository;
import com.bobfriend.bobfriends.support.aws.AmazonS3Requester;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final AmazonS3Requester amazonS3Requester;
    private final FileRepository fileRepository;

    public List<File> uploadAll(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .map(this::upload)
                .collect(Collectors.toList());
    }

    public File upload(MultipartFile multipartFile) {
        File file = File.multipartOf(multipartFile);
        amazonS3Requester.put(file.getPath(), multipartFile);
        return fileRepository.save(file);
    }

    @SneakyThrows
    public void fileDownload(String fileId, HttpServletResponse httpServletResponse) {
        File file = fileRepository.getReferenceById(fileId);

        String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
        amazonS3Requester.get(file.getPath(), httpServletResponse);
    }
}
