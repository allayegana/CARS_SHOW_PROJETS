package com.blit.us.carshow.UseCase;


import com.blit.us.carshow.CarShopException;
import com.blit.us.carshow.Entity.OwnerCar;
import com.blit.us.carshow.Repository.OwerCarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OwnerService {
    @Autowired
    private OwerCarRepository repository;

    public void creatOwner(OwnerCar ownerCar) {
        log.info("ADD NEW OWNER");
        repository.save(ownerCar);
    }

    public List<OwnerCar> findOwner() {
        log.info("***** GET OWNER ALL SERVICE *****");
        Sort sort = Sort.by("id").descending();

        return repository.findAll(sort);
    }

    public Optional<OwnerCar> getCarById(Long id) {
        var owner = repository.findById(id);

        log.info("***** GET OWNER BY ID SERVICE ****** ");
        if (owner.isEmpty()) {
            throw new CarShopException(" you no have this owner for this owner");
        }

        return owner;
    }

    public void removeCar(Long id) {
        log.info("***** REMOVE OWNER BY ID SERVICE ****** " + id);
        if (id == null) {
            throw new CarShopException("NOT FIND THIS OWNER WITH THIS ID " + id);
        }
        repository.deleteById(id);
    }

    public void updateCar(Long id, OwnerCar ownerCar) {

        log.info("***** UPDATE OWNER BY ID SERVICE ****** " + ownerCar);
        Optional<OwnerCar> currentUser = repository.findById(id);
        BeanUtils.copyProperties(ownerCar, currentUser);
        repository.save(ownerCar);
    }
}
