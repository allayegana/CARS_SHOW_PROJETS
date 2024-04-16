package com.blit.us.carshow.UseCase;


import com.blit.us.carshow.Entity.Carshop;
import com.blit.us.carshow.Repository.CarRepositrory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CarShowService {

    @Autowired
    private CarRepositrory repositrory;

    public void addNewCar(Carshop car) {
        log.info("ADD NEW CAR AND OWNER " + car);
        repositrory.save(car);
    }

    public List<Carshop> findcar() {
        log.info("***** find ALL car *****");
        return repositrory.findAll();
    }


    @SneakyThrows
    public Optional<Carshop> getCarById(Long id) {
        log.info("UPDATE CAR BY ID " + id);
        var carId = repositrory.findById(id);
        log.info("get car by id");
        if (carId.isEmpty()) {
            throw new RuntimeException(" you no have this car your car show");
        }
        return carId;
    }


    public void updateCar(Long id, Carshop carshop) {
        log.info("UPDATE CAR BY ID" + carshop);
//       var car = repositrory.findCarById(id);
//       car.setId(carshop.getId());
//        car.setRegisterNumber(carshop.getRegisterNumber());
//        car.setYear(carshop.getYear());
//        car.setModel(carshop.getModel());
//        car.setPrice(carshop.getPrice());
//        car.setBrand(carshop.getBrand());
//        car.setColor(carshop.getColor());
//        car.setOwner(carshop.getOwner());

        Carshop currentUser = repositrory.findCarById(id);
        BeanUtils.copyProperties(carshop, currentUser);

        repositrory.save(carshop);
    }

    public void removeCar(Long id) {
        log.info("REMOVE CAR BY ID " + id);
        repositrory.deleteById(id);
    }
}
