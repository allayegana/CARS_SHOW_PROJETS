package com.blit.us.carshow.https;


import com.blit.us.carshow.Entity.OwnerCar;
import com.blit.us.carshow.UseCase.OwnerService;
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
public class OwnerControllers implements Serializable {

    @Autowired
    private OwnerService service;

    @GetMapping("/owner")
    @ResponseStatus(HttpStatus.OK)
    public List<OwnerCar> findAllCar() {
        log.info("***** GET ALL OWNER CONTROLLER ***** ");
        return service.findOwner();
    }

    @PostMapping("/add/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCar(@RequestBody OwnerCar ownerCar) {
        log.info("***** ADD NEW OWNER CONTROLLER *****");
        service.creatOwner(ownerCar);
    }

    @GetMapping("/owner/{id}")
    public Optional<OwnerCar> getcarById(@PathVariable("id") Long id) {
        log.info("FIND CAR BY ID CONTROLLER " + id);
        return service.getCarById(id);
    }

    @DeleteMapping("/owner/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        service.removeCar(id);
        log.info("***** REMOVE CAR BY ID CONTROLLER ******" + id);

    }

    @PutMapping(path = "/update/owner/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateCar(@PathVariable("id") Long id, @RequestBody OwnerCar ownerCar) {
        service.updateCar(id, ownerCar);
        log.info("***** UPDATE CAR BY ID CONTROLLER ***** " + ownerCar);
    }

}
