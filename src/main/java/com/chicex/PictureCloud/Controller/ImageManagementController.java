package com.chicex.PictureCloud.Controller;

import com.chicex.PictureCloud.Objects.ImageInfo;
import com.chicex.PictureCloud.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ImagesManagement")
public class ImageManagementController {
    @Autowired
    ImageService imageService;
    @RequestMapping("/list")
    public @ResponseBody
    List<ImageInfo> list(@RequestParam(value = "imageKey",required = false) String imageKey){
         return imageService.getAllImages();
    }
    @RequestMapping("/deleteOne")
    public @ResponseBody
    boolean deleteOne(@RequestParam("imageKey") String imageKey){
        if(imageService.deleteImage(imageKey)){ return true;}
        else {return false;}
    }
    @RequestMapping("/findOne")
    public @ResponseBody
    ImageInfo findOne(@RequestParam("imageKey") String imageKey){
        return imageService.getImage(imageKey);
    }
}
