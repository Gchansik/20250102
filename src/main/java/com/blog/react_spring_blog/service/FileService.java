package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.response.file.ResFileDownloadDto;
import com.blog.react_spring_blog.dto.response.file.ResFileUploadDto;
import com.blog.react_spring_blog.entity.Board;
import com.blog.react_spring_blog.entity.FileEntity;
import com.blog.react_spring_blog.repository.FileRepository;
import com.blog.react_spring_blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
    private String FOLDER_PATH;


    private final FileRepository fileRepository;


    public List<ResFileUploadDto> upload (Long boardId, List<MultipartFile> multipartFiles) throws IOException {
        Board board = boardRepository.findById(boardId);

        String fileResourcePath = FOLDER_PATH + File.separator + filePath;

        File f = new File(FOLDER_PATH);
        if(!f.exists()) {
            f.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Path.get(filesResourcePath));

        FileEntity saveFile = FileEntity.builder()
                .originalFileName(multipartFile.getOriginalFilename)
                .filePath(filePath)
                .fileType(multipartFile.getContentType())
                .build()
        saveFile.setMappingBoard(board);
        fileEntitys.add(fileRepository.save(saveFile));
    }

    public ResFileDownloadDto download(Long fileId) throws IOException {
        FileEntity file = filesRepository.findById(fileId).orElseThrow(
                () -> new FileNotFoundException()
        );

        String filePath = FOLDER_PATH + file.getFilePath();
        String contentType = determindContentType(file.getFileType());
        byte[] content = Files.readAllBytes(new File(filePath).toPath());

        return ResFileDownloadDto.fromFileResource(file, contentType, content);
    }

    public void delete(Long fileId) {
        FileEntity file = filesRepository.findById(fileId).orElseThrow(
                () -> new ResourceNotFoundException("File", "File Id", String.valueOf(fileId))
        );

        String filePath = FOLDER_PATH + File.separator + file.getFilePath();
        File physicalFile = new File(filePath);
        if(physicalFile.exists()){
            physicalFile.delete();
        }
        fileRepository.delete(file);

    }


    private String determindContentType(String contentType) {
        switch (contentType) {
            case "image/png":
                return MediaType.IMAGE_PNG_VALUE;
            case "image/jpeg":
                return MediaType.IMAGE_JPEG_VALUE;
            case "image/plain":
                return MediaType.TEXT_PLAIN_VALUE;
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;

        }



    }








}
