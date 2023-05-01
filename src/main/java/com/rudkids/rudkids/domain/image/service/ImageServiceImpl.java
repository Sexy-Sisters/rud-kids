package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final S3ImageUploader s3ImageUploader;

    @Override
    public ImageInfo.Main upload(MultipartFile image) {
        return s3ImageUploader.upload(image);
    }

    @Override
    public List<ImageInfo.Main> uploads(List<MultipartFile> images) {
        return images.stream()
            .map(s3ImageUploader::upload)
            .toList();
    }

    @Override
    public void delete(Item item) {
        if(!item.hasImages()) {
            item.getImagePaths().forEach(s3ImageUploader::delete);
        }
    }

    @Override
    public void delete(Product product) {
        if(!product.hasImage()) {
            s3ImageUploader.delete(product.getFrontImagePath());
            s3ImageUploader.delete(product.getBackImagePath());
        }
    }
}
