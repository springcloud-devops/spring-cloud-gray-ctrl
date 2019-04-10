package demo.sdk.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhusen on 2017/8/18.
 */
public class FileUploadUtil {
//    public static String multipartFileUpload(MultipartFile multipartFile, String staticsDir, String uploadBaseUrl) throws IOException {
//        if (multipartFile == null) {
//            return null;
//        }
//        File file = new File(staticsDir);
//        if (!file.exists() && !file.isDirectory()) {
//            file.mkdirs();
//        }
//        String name = multipartFile.getOriginalFilename();
//        name = UUID.randomUUID().toString().replace("-", "") + name.substring(name.lastIndexOf(".")).toLowerCase();
//        String path = staticsDir + name;
//        try {
//            file = new File(path);
//            // 先尝试压缩并保存图片
//            Thumbnails.of(multipartFile.getInputStream()).scale(0.7f).outputQuality(0.35f).toFile(file);
//        } catch (IOException e) {
//            try {
//                multipartFile.transferTo(file);
//            } catch (IOException e1) {
//                throw e1;
//            }
//        }
//        return uploadBaseUrl + "/statics/" + name;
//    }

    public static String base64UpLoad(String base64Data, String staticsDir, String uploadBaseUrl) throws IOException {
        if (base64Data == null || "".equals(base64Data)) {
            return null;
        }
        String dataPrix;
        String data;

        String[] d = base64Data.split("base64,");
        if (d != null && d.length == 2) {
            dataPrix = d[0];
            data = d[1];
        } else {
            return null;
        }
        String suffix;
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {
            return null;
        }
        //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        String imageName = UUID.randomUUID().toString().replace("_", "") + suffix;
        String pathName = staticsDir + imageName;
        //使用apache提供的工具类操作流
        FileUtils.writeByteArrayToFile(new File(pathName), bs);
        return uploadBaseUrl + "/statics/" + imageName;
    }
}