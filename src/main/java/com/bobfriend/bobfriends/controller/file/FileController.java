package com.bobfriend.bobfriends.controller.file;

import com.bobfriend.bobfriends.controller.file.dto.FileResponse;
import com.bobfriend.bobfriends.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/files/multiple", consumes = MULTIPART_FORM_DATA_VALUE)
    public List<FileResponse> fileUpload(@RequestPart("files") List<MultipartFile> multipartFiles) {
        return fileService.uploadAll(multipartFiles).stream()
                .map(FileResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/files/one", consumes = MULTIPART_FORM_DATA_VALUE)
    public FileResponse fileUpload(@RequestPart("file") MultipartFile multipartFile) {
        return new FileResponse(fileService.upload(multipartFile));
    }

    @GetMapping("/file/{fileId}")
    public void fileDownload(@PathVariable String fileId, HttpServletResponse httpServletResponse) {
        fileService.fileDownload(fileId, httpServletResponse);
    }
}
