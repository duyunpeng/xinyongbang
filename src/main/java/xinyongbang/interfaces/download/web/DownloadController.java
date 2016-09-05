package xinyongbang.interfaces.download.web;

import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xinyongbang.application.user.IUserAppService;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreHttpUtils;
import xinyongbang.core.util.CoreQRCodeUtils;
import xinyongbang.interfaces.shared.web.BaseController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by yjh on 16-6-2.
 */
@Controller
@RequestMapping("/download")
public class DownloadController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserAppService userAppService;

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping(value = "/share/{id}")
    public ModelAndView shareDownload(@PathVariable String id) {
        return new ModelAndView("/download", "id", id);
    }

    @RequestMapping(value = "/qr_code/{id}")
    public void qrCode(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String content = fileUploadService.getDoMainName() + "download/" + id;
        int width = 250;
        int height = 250;
        CoreQRCodeUtils.setImageWidth(20);
        CoreQRCodeUtils.setImageHeight(20);
        String srcImagePath = new File(DownloadController.class.getResource("/").getFile(), "../../resources/images/logo/10.png").getCanonicalPath();
        ImageIO.write(CoreQRCodeUtils.genBarcode(content, width, height, srcImagePath, "utf-8"), "jpg", response.getOutputStream());

    }

    @RequestMapping(value = "/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            userAppService.shareAddGold(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String platform = CoreHttpUtils.getLoginPlatform(request);
        if (platform.equals("IPhone") || platform.equals("Mac") || platform.equals("IPad")) {
            response.sendRedirect("https://itunes.apple.com/app/id1128307743");
        } else {
            response.sendRedirect("http://a.app.qq.com/o/simple.jsp?pkgname=com.pengyi.xinyongbang");
        }
    }
}
