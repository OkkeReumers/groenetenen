package be.vdab.services;

import be.vdab.entities.Werknemer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface WerknemerService {

    public Page<Werknemer> findAll(Pageable pageable);
}
