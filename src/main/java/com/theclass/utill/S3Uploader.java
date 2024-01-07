package com.theclass.utill;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    //버킷 이름 동적 할당
    @Value("${cloud.aws.s3.bucket:chungdam}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        System.out.println("convert::시도!!!");
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        String originalFileName = multipartFile.getOriginalFilename();
        String formatName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        return upload(uploadFile, dirName, formatName);
    }

    private String upload(File uploadFile, String dirName, String formatName) {
        //파일이름 랜덤 생성
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        String filePath = dirName + "/" + fileName + "." + formatName;
        //파일 S3 업로드
        String uploadImageUrl = putS3(uploadFile, filePath);
        removeNewFile(uploadFile);
        return filePath;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        //File convertFile = new File(file.getOriginalFilename());
        File convertFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                file.getOriginalFilename());

        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            System.out.println("convert::파일 업로드 파일 생성완료!");
            return Optional.of(convertFile);

        }
        return Optional.empty();
    }

    //파일삭제
    public void delOneThumb(String url) {
        amazonS3Client.deleteObject(bucket, url);
    }

    //여러파일 삭제
    public void delThumbAll(String[] keyArr) {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucket).withKeys(keyArr);
        amazonS3Client.deleteObjects(delObjReq);
    }

}