package com.blit.us.carshow.Repository;

import com.blit.us.carshow.Entity.Carshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarRepositrory extends JpaRepository<Carshop, Long> {

    Carshop findCarById(Long id);
}
