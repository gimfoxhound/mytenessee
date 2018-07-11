
package org.o7planning.springmvcsecurity.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
 
public class MyUploadForm {

private String name;
private String description;
//upload files
private CommonsMultipartFile[] fileDatas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommonsMultipartFile[] getFileDatas() {
        return fileDatas;
    }
//A enlever
    public CommonsMultipartFile getFileData(){
        return fileDatas[0];
    }
    public void setFileDatas(CommonsMultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
/*
    @Override
    public String toString() {
        return "("+ this.getName()+", "+ this.getDescription()+ ", "+ this.getFileDatas().length+")";
    }*/

    
    


    
}
