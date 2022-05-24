package cz.nss.onegram.post.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import cz.nss.onegram.post.exception.InvalidImageException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import cz.nss.onegram.post.service.interfaces.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Primary
@RequiredArgsConstructor
public class AzureFileServiceImpl implements FileService {

    private final BlobContainerClient containerCLient;

    @Override
    public String saveFile(InputStream file) {
        try {
            BlobClient client = containerCLient.getBlobClient(new ObjectId() + ".png");
            client.upload(file, file.available());
            return client.getBlobUrl();
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidImageException("Image could not be saved.");
        }
    }

    @Override
    public List<String> saveFiles(List<InputStream> files) {
        List<String> result = new ArrayList<>();
        files.forEach(f -> result.add(saveFile(f)));
        return result;
    }

    @Override
    public void deleteFile(String filename) {
        BlobClient client = containerCLient.getBlobClient(filename);
        client.delete();
        log.info("Deleted file from Azure Storage: " + filename);
    }
}
