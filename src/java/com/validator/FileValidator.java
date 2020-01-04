///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.validator;
//
//import com.model.FileUpload;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
///**
// *
// * @author Supersoft
// */
//@Component
//public class FileValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return FileUpload.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//      FileUpload fileupload = (FileUpload) target;
//      
//      CommonsMultipartFile[] commonmultipartfile = fileupload.getFile();
//      
//      for(CommonsMultipartFile multipartfile : commonmultipartfile){
//      if(multipartfile.getSize() == 0){
//       errors.rejectValue("file", "No chosen file");
//      }
//      }
//      
//    }
//    
//}
