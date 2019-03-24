package com.yfq.cloud.web.ctl.HolleCtl.upload;

import com.yfq.cloud.web.bean.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

@Slf4j
@Controller
public class UploadCtl {
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseBean layuiUpload(HttpServletRequest request, MultipartFile file, HttpServletResponse response) {
        log.error("error");
//        OutputStream os = null;
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        try {
//            String PPP=ResourceUtils.getURL("").getPath();
//            File  srcPath=new File(ResourceUtils.getURL("").getPath());
//            File testFile = new File(srcPath, "upload");
//            if (!testFile.exists()) {
//                testFile.mkdirs();
//            }
//            os = response.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File(srcPath + "/doc/" + "filename")));
//            int i = bis.read(buff);
//            while (i != -1) {
//                os.write(buff, 0, buff.length);
//                os.flush();
//                i = bis.read(buff);
//            }
//    String s="dds";
//}catch (Exception e){};


        ResponseBean bean = new ResponseBean();
        String oldFileName = file.getOriginalFilename();
//        final String fileName = Calendar.getInstance().getTimeInMillis() + oldFileName;
        final String fileName = oldFileName;
        log.error(fileName);
//        String filePath = "D:/upload";
        try {
        File  filePath=new File(ResourceUtils.getURL("").getPath()+"/upload");
//        String filePath= request.getSession().getServletContext().getRealPath("/upload");
//        log.error(filePath);
//        File dir = new File(filePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File TrxFiles[] = filePath.listFiles();

//        log.error(TrxFiles[0].getName());
        boolean s=filePath.exists();
        log.error(String.valueOf(s));
        File newFile = new File(filePath, fileName);
        log.error(newFile.getAbsolutePath());

            file.transferTo(newFile);
            bean.setMsg(newFile.getName());
        } catch (Exception e) {
        }

//        bean.setData(filePath+TrxFiles[0].getName());
//        bean.setData("12322");
        return bean;
    }
@RequestMapping("/download")
    public void downLoad(HttpServletRequest request, HttpServletResponse response){

//       String path=request.getServletContext().getRealPath("/upload");
       String filename="1553172573(1).rar";
       String path="D:upload";
       File file=new File(path+"/"+filename);
//       response.setContentLength((int)file.length());
////       response.setContentType();
//       response.setHeader("Content-Didposition","attachment:filename="+filename);
//        InputStream inputStream=new FileInputStream(file);
//
//        OutputStream outputStream=response.getOutputStream();

        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
