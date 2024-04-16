package com.blit.us.carshow.Repository;

import com.blit.us.carshow.Entity.OwnerCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwerCarRepository extends JpaRepository<OwnerCar, Long> {
}
