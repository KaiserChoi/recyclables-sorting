/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.alin3.greenstar.dao;

import hkmu.alin3.greenstar.model.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wy
 */
public interface UserUploadRepository {

    public void insertModelDetail(MultipartFile file) throws IOException;
    public File getModelDetail(String modelId);
    public int createData(MultipartFile xmlFile, MultipartFile pngFile) throws IOException;
}
