package be.vdab.services;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;

import java.math.BigDecimal;
import java.util.List;

public interface FiliaalService {
    void create(Filiaal filiaal, String urlAlleFilialen);

    Filiaal read(long id);

    void update(Filiaal filiaal);

    void delete(long id);

    List<Filiaal> findAll();

    long findAantalFilialen();

    List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks);

    List<Filiaal > findNietAfgeschreven();
    void afschrijven(Iterable <Filiaal> filialen);

    void aantalFilialenMail();
}
