package javahive.angualrspringapp.controller;

import java.util.List;

import javahive.api.StudenciApi;
import javahive.api.dto.StudentDTO;
import javahive.domain.Przedmiot;

import javax.inject.Inject;

import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(types = StudentDTO.class)
@RequestMapping("/studenci")
public class StudentController {

    @Inject
    private StudenciApi studenciApi;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public @ResponseBody
    List<StudentDTO> getStudentList() {
        return studenciApi.getListaWszystkichStudentow();
    }
    
    @RequestMapping(value = "/przedmioty", method = RequestMethod.GET)
    public @ResponseBody
    List<Przedmiot> getPrzedmiotList() {
        return studenciApi.getPrzemioty();
    }

    @RequestMapping("/ping")
    public @ResponseBody
    String getPing() {
        return "pong";
    }

    @RequestMapping(value="/usunStudenta/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String usunStudenta(@PathVariable int id) {
        try {
            studenciApi.usunStudentaOZadanymId(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Pomyœlnie usuniêto studenta";
    }

    @RequestMapping(value = "/dodajStudenta", method = RequestMethod.PUT)
    public @ResponseBody
    String dodajStudenta(@RequestBody StudentDTO student) {
        try {
            studenciApi.dodajStudenta(student, student.getNumerIndeksu());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Pomyœlnie dodano studenta";
    }

}
