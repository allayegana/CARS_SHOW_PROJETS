package com.blit.us.carshow.Repository;

import com.blit.us.carshow.Entity.Carshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Carshop, Long> {

    Carshop findCarById(Long id);
}
