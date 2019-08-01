package com.chicex.PictureCloud.Controller;

import com.chicex.PictureCloud.ApplicationStartUpConfig;
import com.chicex.PictureCloud.Objects.ImageInfo;
import com.chicex.PictureCloud.Service.ImageService;
import com.chicex.PictureCloud.Utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

@Controller
@RequestMapping("/image")
public class imageUploadController {
    @Autowired
    ImageService imageService;
    @PostMapping(value = "/imageUpload")
    public @ResponseBody
    com.chicex.PictureCloud.Objects.ResponseBody upload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "sessionID",required = false) String sessionID) throws IOException {
        //获取服务器中保存文件的路径
        ImageInfo imageI = new ImageInfo();
        String imageKey = null;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String path = ApplicationStartUpConfig.DefaultImageLocation;
        //System.out.println(path);
        //System.out.println(request.getContentType());
        //获取解析器
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断是否是文件
        System.out.println(resolver.isMultipart(request));
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> it = multiRequest.getFileNames();
            while (it.hasNext()) {
                //根据文件名称取文件
                MultipartFile file = multiRequest.getFile(it.next());
                String fileName = file.getOriginalFilename();
                imageI.setName(fileName);
                //创建图片文件key
                imageKey = imageI.createKey();
                //System.out.println(fileName);
                if(!ImageInfo.matchType(fileName.substring(fileName.lastIndexOf(".")+1))){
                    return new com.chicex.PictureCloud.Objects.ResponseBody(400,"not allow this type: " + fileName);
                }//后缀名
                String localPath = path + imageKey +"."+ fileName.substring(fileName.lastIndexOf(".")+1);
                //创建一个新的文件对象，创建时需要一个参数，参数是文件所需要保存的位置
                File newFile = new File(localPath);
                System.out.println(localPath);
                if (newFile.getParentFile() != null || !newFile.getParentFile().isDirectory()) {
                    // 创建父文件夹
                    newFile.getParentFile().mkdirs();
                }
                file.transferTo(newFile);
                String pathSmall=path+"small/"+imageKey+"."+"jpg";


                BufferedImage bufferedImage= ImageIO.read(newFile);
                ImgUtils.scale(bufferedImage,pathSmall,100,100,true);

                imageI.setExtrainfo("/image/getSmallImage/"+imageKey+"."+"jpg");
                imageI.setType(fileName.substring(fileName.lastIndexOf(".")+1));
                imageI.setPath(localPath);
                imageI.setImagekey(imageKey);
                imageService.saveImage(imageI);
            }
            return new com.chicex.PictureCloud.Objects.ResponseBody(200,"/image/getImage/"+imageI.getImagekey()+"."+imageI.getType());
        }
        return new com.chicex.PictureCloud.Objects.ResponseBody(400,"未知错误");
    }
    @RequestMapping("/getImage/{key}.{type}")
    public void getImage(@PathVariable String key,
                         @PathVariable String type,
                         HttpServletResponse response)throws FileNotFoundException,IOException{
        ImageInfo info = imageService.getImage(key);
        ServletOutputStream out= null;
            out = response.getOutputStream();
        String realpath=info.getPath();
        InputStream in=new FileInputStream(realpath);
        BufferedInputStream bf = new BufferedInputStream(in);
        int len=0;
        byte[] buffer=new byte[8*1024];
        while((len=bf.read(buffer))>0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();

    }


    @RequestMapping("/getSmallImage/{key}.jpg")
    public void getSmallImage(@PathVariable String key,
                         HttpServletResponse response) throws FileNotFoundException,IOException{

        ImageInfo info = imageService.getImage(key);
        ServletOutputStream out= null;
        out = response.getOutputStream();
        String realpath=info.getPath();
        File file = new File(realpath);
        String path = file.getParentFile().getPath()+"/small/"+key+".jpg";
        InputStream in=new FileInputStream(path);
        BufferedInputStream bf = new BufferedInputStream(in);
        int len=0;
        byte[] buffer=new byte[1024];
        while((len=bf.read(buffer))>0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
    }
}
