package be.vdab.restservices;


import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.exceptions.FiliaalNietGevondenException;
import be.vdab.services.FiliaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/filialen")
class FiliaalRestController {
    private final FiliaalService filiaalService ;
    @Autowired
    FiliaalRestController (FiliaalService filiaalService ){
        this.filiaalService = filiaalService ;
    }

    @RequestMapping (value = "{filiaal}", method = RequestMethod.GET)
    Filiaal read(@PathVariable Filiaal filiaal ) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        return filiaal ;
    }

    @ExceptionHandler(FiliaalNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filiaalNietGevonden() {}

    @RequestMapping(value = "{filiaal}", method = RequestMethod.DELETE)
    void delete(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        filiaalService.delete(filiaal.getId());
    }

    @ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String filiaalHeeftNogWerknemers() {
        return "filiaal heeft nog werknemers";
    }
}
