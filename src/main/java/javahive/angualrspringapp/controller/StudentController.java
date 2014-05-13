package javahive.angualrspringapp.controller;

import java.util.List;

import javahive.api.StudenciApi;
import javahive.api.dto.StudentDTO;

import javax.inject.Inject;

import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/studenci")
public class StudentController {

    @Inject
    private StudenciApi studenciApi;

    @RequestMapping("/lista")
    public @ResponseBody
    List<StudentDTO> getStudentList() {
        return studenciApi.getListaWszystkichStudentow();
    }

    @RequestMapping("/ping")
    public @ResponseBody
    String getPing() {
        return "pong";
    }

    @RequestMapping("/usunStudenta/{id}")
    @ResponseBody
    void usunStudenta(@PathVariable int id) {
        studenciApi.usunStudentaOZadanymId(id);
    }

}
