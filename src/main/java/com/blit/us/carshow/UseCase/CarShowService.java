package com.blit.us.carshow.UseCase;


import com.blit.us.carshow.CarShopException;
import com.blit.us.carshow.Entity.Carshop;
import com.blit.us.carshow.Repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CarShowService {

    @Autowired
    private CarRepository repository;

    public void addNewCar(Carshop car) {
        log.info("***** ADD NEW CAR AND OWNER SERVICE ***** " + car);
        repository.save(car);
    }

    public List<Carshop> findCarShow() {
        log.info("***** GET CAR ALL SERVICE *****");
        Sort sort = Sort.by("id").descending();
        var list = repository.findAll(sort);
        if (list.isEmpty()) {
            throw new CarShopException("YOUR CARS SHOW IS EMPTY " + list);
        }
        return list;
    }


    public Optional<Carshop> getCarById(Long id) {
        var carId = repository.findById(id);
        log.info("***** UPDATE CAR BY ID SERVICE ****** ");
        if (carId.isEmpty()) {
            throw new CarShopException(" you no have this car your in car show");
        }
        return carId;
    }


    public void updateCar(Long id, Carshop carshop) {
        log.info("***** UPDATE CAR BY ID SERVICE ******" + carshop);
        Carshop currentUser = repository.findCarById(id);
        BeanUtils.copyProperties(carshop, currentUser);
        repository.save(carshop);
    }

    public void removeCar(Long id) {
        log.info("***** REMOVE CAR BY ID SERVICE ****** " + id);
        if (id == null) {
            throw new CarShopException("NOT FIND THIS CAR WITH THIS ID " + id);
        }
        repository.deleteById(id);
    }
}
