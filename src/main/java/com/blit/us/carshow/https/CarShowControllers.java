package com.blit.us.carshow.https;


import com.blit.us.carshow.Entity.Carshop;
import com.blit.us.carshow.UseCase.CarShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Carshop> findallCar() {
        log.info("FIND ALL CARS");
        return service.findcar();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCar(@RequestBody Carshop car) {
        log.info("ADD NEW CAR " + car);
        service.addNewCar(car);
    }

    @GetMapping("/car/{id}")
    public Optional<Carshop> getcarById(@PathVariable("id") Long id) {
        log.info("FIND CAR BY ID " + id);
        return service.getCarById(id);
    }

    @DeleteMapping("/car/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        service.removeCar(id);
        log.info("REMOVE CAR BY ID " + id);

    }

    @PutMapping(path = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateCar(@PathVariable("id") Long id, @RequestBody Carshop car) {
        service.updateCar(id, car);
        log.info("UPDATE CAR BY ID " + car);
    }

}
