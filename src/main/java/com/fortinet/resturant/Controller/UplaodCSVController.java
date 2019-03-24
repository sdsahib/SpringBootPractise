package com.fortinet.resturant.Controller;

import com.fortinet.resturant.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@CrossOrigin()
public class UplaodCSVController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploaddetailscsv")
    public boolean uploadDetailsCsv(@RequestParam MultipartFile file){
        boolean toReturn = this.uploadService.uploadCsv(file,"details");
        return toReturn;
    }

    @PostMapping("/uploadaddresscsv")
    public boolean uploadAddressCsv(@RequestParam MultipartFile file){
        boolean toReturn = this.uploadService.uploadCsv(file,"address");
        return toReturn;
    }
}
