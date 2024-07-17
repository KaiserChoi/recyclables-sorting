/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.alin3.greenstar.service;

import hkmu.alin3.greenstar.model.File;
import hkmu.alin3.greenstar.dao.UserUploadRepository;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wy
 */

@Service
public class UserUploadService {
       
    @Autowired
    UserUploadRepository uploadRepo;
    
    public File getModelDetail(String modelId){
        return uploadRepo.getModelDetail(modelId);
    }
    
    public void updateModelDetail(MultipartFile file) throws IOException{
        uploadRepo.insertModelDetail(file);
    }

    public int uploadData(MultipartFile xmlFile, MultipartFile pngFile) throws IOException{
        try {
            return uploadRepo.createData(xmlFile, pngFile);
        }catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}
