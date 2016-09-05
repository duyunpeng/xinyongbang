package xinyongbang.interfaces.upload.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.upload.UploadResult;

import java.io.IOException;

/**
 * Created by YJH on 2016/4/11.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping(value = "/img_upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadResult imgUpload(@RequestParam MultipartFile[] file) throws IOException {
        return fileUploadService.imgUpload(file);
    }

}
