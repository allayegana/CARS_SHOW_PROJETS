package com.blit.us.carshow.https;


import com.blit.us.carshow.Entity.Carshop;
import com.blit.us.carshow.UseCase.CarShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CarShowControllers implements Serializable {

    @Autowired
    private CarShowService service;

    @GetMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public List<Carshop> findAllCar() {
        log.info("***** GET ALL CARS CONTROLLER ***** ");
        return service.findCarShow();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void addNewCar(@RequestBody Carshop car) {
        log.info("***** ADD NEW CAR CONTROLLER *****");
        service.addNewCar(car);
    }

    @GetMapping("/car/{id}")
    public Optional<Carshop> getCarById(@PathVariable("id") Long id) {
        log.info("FIND CAR BY ID CONTROLLER " + id);
        return service.getCarById(id);
    }

    @DeleteMapping("/car/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        service.removeCar(id);
        log.info("***** REMOVE CAR BY ID CONTROLLER ******" + id);

    }

    @PutMapping(path = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateCar(@PathVariable("id") Long id, @RequestBody Carshop car) {
        service.updateCar(id, car);
        log.info("***** UPDATE CAR BY ID CONTROLLER ***** " + car);
    }

}
