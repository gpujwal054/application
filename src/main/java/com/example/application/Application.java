package com.example.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application {



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//Compress image file in server side

//	public static void compressImage(File originalImage,File compressedImage,float compressionQuality) throws IOException{
//		RenderedImage image = ImageIO.read(originalImage);
//		ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
//		ImageWriteParam jpegWriteParam = jpegWriter.getDefaultWriteParam();
//		jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//		jpegWriteParam.setCompressionQuality(compressionQuality);
//
//		try(ImageOutputStream output = ImageIO.createImageOutputStream(compressedImage)) {
//			jpegWriter.setOutput(output);
//			IIOImage outputImage = new IIOImage(image,null,null);
//			jpegWriter.write(null,outputImage,jpegWriteParam);
//		}
//		jpegWriter.dispose();
//	}
//	public static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
//		File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
//		multipart.transferTo(convFile);
//		return convFile;
//	}
}
