/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.alin3.greenstar.dao;

import hkmu.alin3.greenstar.model.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wy
 */
@Repository
public class UserUploadRepositoryimpl implements UserUploadRepository {

    private final JdbcOperations jdbcOp;
    
    @Autowired
    public UserUploadRepositoryimpl(DataSource dataSource) {
        jdbcOp = new JdbcTemplate(dataSource);
    }

    private static final class ModelDetailExtractor implements  RowMapper<File> {
        
        @Override
        public File mapRow(ResultSet rs, int i) throws SQLException, DataAccessException {
            File modelDetail = new File();
            modelDetail.setId(rs.getInt("detail_id"));
            modelDetail.setName(rs.getString("filename"));
            modelDetail.setMimeContentType(rs.getString("content_type"));
            Blob blob = rs.getBlob("content");
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            modelDetail.setContents(bytes);
            return modelDetail;
        }
    }
    
    @Transactional
    @Override
    public int createData(MultipartFile xmlFile, MultipartFile pngFile) throws IOException{
        final String SQL_CREATE_XML= "insert into xmlFile (fileName, content_Type, content)"
                    + " values (?, ?, ?)";

        final String SQL_CREATE_PNG = "insert into imgFile (fileName, content_Type, content, xmlId)"
                    + " values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println("data upload ....................");
        System.out.println("xmlfile name: " + xmlFile.getOriginalFilename());
        System.out.println("pngfile type" + pngFile.getContentType());
        System.out.println("file size: " + pngFile.getSize());
//        jdbcOp.update(SQL_CREATE_DATA,
//                new Object[]{file.getOriginalFilename(), file.getContentType(),
//                        new SqlLobValue(file.getInputStream(), (int) file.getSize(), handler)},
//                new int[]{Types.VARCHAR, Types.VARCHAR, Types.BLOB}, keyHolder);
//        System.out.println("primary key: " + keyHolder.getKey().intValue());
        jdbcOp.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE_XML, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, xmlFile.getOriginalFilename());
            ps.setString(2, xmlFile.getContentType());
            try {
                ps.setBlob(3, xmlFile.getInputStream(), (int) xmlFile.getSize());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ps;
        }, keyHolder);

        LobHandler handler = new DefaultLobHandler();
        System.out.println("xml file primary: " + keyHolder.getKey());
        jdbcOp.update(SQL_CREATE_PNG,
                new Object[]{pngFile.getOriginalFilename(), pngFile.getContentType(),
                        new SqlLobValue(pngFile.getInputStream(), (int) pngFile.getSize(), handler),
                        Objects.requireNonNull(keyHolder.getKey()).intValue()},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.INTEGER});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
    
    @Transactional
    @Override
    public void insertModelDetail(MultipartFile file) throws IOException{
        final String SQL_UPDATE_MODELDETAIL = "INSERT INTO modelDetail (filename, content_type, content) values (?, ?, ?)";
        LobHandler handler = new DefaultLobHandler();
        System.out.println(file.getOriginalFilename());
        jdbcOp.update(SQL_UPDATE_MODELDETAIL,
        new Object[]{file.getOriginalFilename(), file.getContentType(),
            new SqlLobValue(file.getInputStream(), (int) file.getSize(), handler)},
        new int[]{Types.VARCHAR, Types.VARCHAR, Types.BLOB});     
    }
    
    @Transactional
    @Override
    public File getModelDetail(String modelId){
        final String SQL_GET_MODELDETAIL = "select * from modelDetail where fileName=?";
        return jdbcOp.queryForObject(SQL_GET_MODELDETAIL, new ModelDetailExtractor(), modelId);
    }
}
