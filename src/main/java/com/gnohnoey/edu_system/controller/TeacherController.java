package com.gnohnoey.edu_system.controller;

import com.gnohnoey.edu_system.model.Teacher;
import com.gnohnoey.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("teachers", teacherRepository.findAll());

        return "teacher-list";
    }

    //생성 페이지
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    //생성 페이지에 기능 연결
    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher){ //모델 객체에 맞게끔 값을 전달 받기(원래는 RequestParam으로 받았음)
        teacherRepository.save(teacher);

        return "redirect:/teachers";
    }
}
