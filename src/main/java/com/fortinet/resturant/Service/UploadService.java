package com.fortinet.resturant.Service;

import au.com.bytecode.opencsv.CSVReader;
import com.fortinet.resturant.Model.RestaurantAddressModel;
import com.fortinet.resturant.Model.RestaurantCuisines;
import com.fortinet.resturant.Model.RestaurantModel;
import com.fortinet.resturant.Repository.RestaurantModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    private RestaurantModelRepository restaurantModelRepository;

    private final Path rootLocation = Paths.get("upload-dir");

    public void store(MultipartFile file) throws RuntimeException, IOException {


        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));

    }

    public boolean uploadCsv(MultipartFile file, String type) {
        boolean toReturn = true;
        this.init();
        try {
            this.store(file);
            if (type.equals("details")) {
                this.convertToObjectAndSave(file.getOriginalFilename());
            } else {
                this.convertToAddressObjectAndSave(file.getOriginalFilename());
            }
            this.deleteAll();

        } catch (IOException e) {
            toReturn = false;
        }
        //call delete all function to delete all the files later
        return toReturn;
    }

    private void convertToAddressObjectAndSave(String filename) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(rootLocation + "/" + filename), ',', '"', 1);
        String[] nextLine;
        RestaurantModel restaurantModel;
        RestaurantAddressModel restaurantAddressModel;
        while ((nextLine = reader.readNext()) != null) {
                restaurantAddressModel = new RestaurantAddressModel();
            if (nextLine != null) {
                //Verifying the read data here
                restaurantModel = this.restaurantModelRepository.findById(nextLine[0]).get();
                this.restaurantModelRepository.delete(restaurantModel);



                restaurantAddressModel.setCountryCode(Integer.valueOf(nextLine[1]));
                restaurantAddressModel.setCity(nextLine[2]);
                restaurantAddressModel.setAddress(nextLine[3]);
                restaurantAddressModel.setLocality(nextLine[4]);
                restaurantAddressModel.setLongitude(Float.valueOf(nextLine[6]));
                restaurantAddressModel.setLatitude(Float.valueOf(nextLine[7]));
                restaurantModel.setRestaurantAddress(restaurantAddressModel);
                this.restaurantModelRepository.save(restaurantModel);

            }
        }
        reader.close();
    }

    public void convertToObjectAndSave(String filename) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(rootLocation + "/" + filename), ',', '"', 1);
        String[] nextLine;
        String[] cuisineList;
        RestaurantModel restaurantModel;
        List<RestaurantCuisines> restaurantCuisines;
        while ((nextLine = reader.readNext()) != null) {
            restaurantModel = new RestaurantModel();
            restaurantCuisines = new ArrayList<>();

            if (nextLine != null) {
                //Verifying the read data here
//                restaurantModel.setRestaurantId();

//                System.out.println(Arrays.toString(nextLine));

                restaurantModel.setRestaurantId(nextLine[0]);
                restaurantModel.setRestaurantName(nextLine[1]);
                cuisineList = nextLine[2].split(",");
                for (int i = 0; i < cuisineList.length; i++) {
                    restaurantCuisines.add(new RestaurantCuisines(cuisineList[i]));
                }
                restaurantModel.setCuisines(restaurantCuisines);
                restaurantModel.setAverageCostOfTwo(Float.valueOf(nextLine[3]));
                restaurantModel.setCurrency(nextLine[4]);
                restaurantModel.setHasTable((nextLine[5].equals("Yes") ? Boolean.TRUE : Boolean.FALSE));
                restaurantModel.setHasOnline((nextLine[6].equals("Yes") ? Boolean.TRUE : Boolean.FALSE));
                restaurantModel.setRating(Float.valueOf(nextLine[7]));
                restaurantModel.setRatingColor(nextLine[8]);
                restaurantModel.setRatingText(nextLine[9]);
                restaurantModel.setVotes(Integer.valueOf(nextLine[10]));
                restaurantModel.setRestaurantAddress(null);

                restaurantModelRepository.save(restaurantModel);


            }
        }
        reader.close();


    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        File tmpDir = new File("upload-dir");
        if (!tmpDir.exists()) {
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize storage!");
            }
        }
    }
}
