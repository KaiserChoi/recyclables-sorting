package hkmu.alin3.greenstar.controller;

import hkmu.alin3.greenstar.service.UserUploadService;
import hkmu.alin3.greenstar.model.File;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserUpload {
    
    private final UserUploadService userUpload;

    @Autowired
    public UserUpload(UserUploadService userUpload) {
        this.userUpload = userUpload;
    }

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String index() {
        System.out.println("index");
        return "index";
    }
    @RequestMapping(value = "/detail", method= RequestMethod.GET)
    public ModelAndView updateModelDetail() {
        System.out.println("model detail upload");
        ModelAndView model = new ModelAndView("updateModelDetail", "modelDetail", new Form());
        return model;
    }
    
    @RequestMapping(value = "/detail", method= RequestMethod.POST)
    public View UpdateModelDetail(Form modelDetail) throws IOException{
        System.out.println(modelDetail.getModelDetail().get(0).getOriginalFilename());
        userUpload.updateModelDetail(modelDetail.getModelDetail().get(0));
        return new RedirectView("/", true);
    }

    @RequestMapping(value="/modelDetail/{modelId}", method= RequestMethod.GET) //, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody String getModelDetail(@PathVariable("modelId") String modelId) throws IOException {
        File f = userUpload.getModelDetail(modelId);
        String message = new String(f.getContents());
        System.out.println(message);
        
        JSONArray jb = JSONArray.parseArray(message);
        return jb.toString();
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody int create(MultipartHttpServletRequest request) throws IOException{
        // Get the file parts from the request
        Map<String, MultipartFile> fileMap = request.getFileMap();
        // Do something with the files
        MultipartFile pngFile = fileMap.get("pngfile");
        MultipartFile xmlFile = fileMap.get("xmlfile");

        System.out.println(pngFile.getOriginalFilename());
        System.out.println(xmlFile.getOriginalFilename());

        int out = userUpload.uploadData(xmlFile, pngFile);
        System.out.println("primary key: " + out);
        return out;
    }

    public static class Form {
        private List<MultipartFile> modelDetail;

        public List<MultipartFile> getModelDetail() {
            return modelDetail;
        }

        public void setModelDetail(List<MultipartFile> modelDetail) {
            this.modelDetail = modelDetail;
        }

    }
}
