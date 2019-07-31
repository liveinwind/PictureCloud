package com.chicex.PictureCloud.Service;

import com.chicex.PictureCloud.Dao.mappers.ImageInfoMapper;
import com.chicex.PictureCloud.Objects.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Component
public class ImageService{

    @Autowired
    private ImageInfoMapper mapper;

    public List<ImageInfo> getAllImages(){return mapper.getAllImages();}
    public void saveImage(ImageInfo imageInfo){
           mapper.insert(imageInfo);
    }
    public ImageInfo getImage(String key){
        return mapper.selectByPrimaryKey(key);
    }

    public boolean deleteImage(String key){
         ImageInfo info = this.getImage(key);
         mapper.deleteByPrimaryKey(key);
         File imgFile = new File(info.getPath());
         File smallImgFile=new File(imgFile.getParentFile().getPath()+"/small/"+info.getImagekey()+".jpg");
         imgFile.delete();
         smallImgFile.delete();
         return true;
    }
}
