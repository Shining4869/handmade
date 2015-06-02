package com.handmade.cn.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.utils.JsonMapper;

/**
 * @author zgdcool
 * @version 2015年5月20日 下午9:06:00
 *   
 */
public class MobileImageSaveAction {
	
	File tempPathFile;
	
	@ResponseBody
	@RequestMapping(value="mobile/image/save")
	public Boolean saveOrUpdate(HttpServletRequest request, Model model) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(10485760); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
		Iterator<FileItem> i = items.iterator();
		Map<String, Object> map = new HashMap<String, Object>();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			String fieldName = fi.getFieldName();
			if (fi.isFormField()) {  
		        //如果是普通表单字段  
	            String value = fi.getString();  
	            map.put(fieldName, new String(value.getBytes("ISO8859_1"), "utf-8"));
		    }else{
		    	File fullFile = new File(fi.getName());
				String fileNameNew = df.format(new Date())+fullFile.getName();
				File savedFile = new File(AppConfig.getAppMap().get("imagepath"), fileNameNew);
				fi.write(savedFile);
				map.put(fieldName, fileNameNew);
		    }
		}
		return Boolean.TRUE;
	}

}
