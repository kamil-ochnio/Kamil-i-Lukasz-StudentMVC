package javahive.angualrspringapp.controller;

import java.util.ArrayList;
import java.util.List;

import javahive.api.StudenciApi;
import javahive.api.dto.StudentDTO;
import javahive.domain.Ocena;
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
        return "Pomy�lnie usuni�to studenta";
    }

    @RequestMapping(value = "/dodajStudenta", method = RequestMethod.PUT)
    public @ResponseBody
    String dodajStudenta(@RequestBody StudentDTO student) {
        try {
            studenciApi.dodajStudenta(student, student.getNumerIndeksu());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Pomy�lnie dodano studenta";
    }
    
    @RequestMapping(value = "/dodajOcene/student/{studentId}/przedmiot/{przedmiot}", 
            method = RequestMethod.PUT)
    public @ResponseBody
    String dodajOcene(@PathVariable int studentId, @PathVariable String przedmiot,
            @RequestBody String ocena) {
        try{
            Float ocenaFloat = Float.parseFloat(ocena);
            if(ocenaFloat < 2 || ocenaFloat > 5){
                return "Ocena powinna by� zakresu 2-5";
            }else 
                if(ocenaFloat.floatValue()-ocenaFloat.intValue()!= 0){
                    if(ocenaFloat.floatValue()-ocenaFloat.intValue() != 0.5){
                        return "U�amkowa cz�� oceny musi wynosi� 0.5";
                    }
                }
        }catch(NumberFormatException e){
            return "Nale�y poda� ocene w formacie X.X np. 2.0 lub 3.5";
        }catch(Exception e){
            return "Wyst�pi� wyj�tek po stronie serwera";
        }
        studenciApi.dodajOceneStudentowi(studentId, przedmiot, ocena);
        return "Dodano ocene "+ocena+" z przedmiotu "+przedmiot;
    }
    
    @RequestMapping(value="/pobierzOcenyStudenta/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List <Ocena> getOcenyStudenta(@PathVariable int id) {
        return studenciApi.getOcenyStudentaOId(id);
    }
}
