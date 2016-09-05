package img;

import org.testng.annotations.Test;
import xinyongbang.core.util.CoreImgUtils;

/**
 * Created by YJH on 2016/5/27.
 */
public class ImgTest {

    @Test
    public void img(){
        String srcImgPath = "C:\\Users\\YJH\\Desktop\\123123.jpg";
//        String logoText = "[ I love Qie]";
        String iconPath = "C:\\Users\\YJH\\Desktop\\logo100100.png";

//        String targerTextPath = "C:\\Users\\YJH\\Desktop\\test.jpg";
//        String targerTextPath2 = "C:\\Users\\YJH\\Desktop\\logo.jpg";

        String targerIconPath = "C:\\Users\\YJH\\Desktop\\test.jpg";
//        String targerIconPath2 = "C:\\Users\\YJH\\Desktop\\logo.jpg";

//        System.out.println("给图片添加水印文字开始...");
//        // 给图片添加水印文字
//        CoreImgUtils.markImageByText(logoText, srcImgPath, targerTextPath);
//        // 给图片添加水印文字,水印文字旋转-45
//        CoreImgUtils.markImageByText(logoText, srcImgPath, targerTextPath2, -45);
//        System.out.println("给图片添加水印文字结束...");

        System.out.println("给图片添加水印图片开始...");
//        CoreImgUtils.setImageMarkOptions(0.3f,1,1,null,null);
        // 给图片添加水印图片
//        CoreImgUtils.markImageByIcon(iconPath, srcImgPath, targerIconPath);
//        // 给图片添加水印图片,水印图片旋转-45
//        CoreImgUtils.markImageByIcon(iconPath, srcImgPath, targerIconPath2, -45);
        System.out.println("给图片添加水印图片结束...");
    }

}
