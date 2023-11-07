package com.mily.lawyer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/lawyer")
@RequiredArgsConstructor
@Controller
public class LawyerController {
    private final LawyerService lawyerService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Lawyer> lawyerList = this.lawyerService.getList();
        model.addAttribute("lawyerList", lawyerList);
        return "lawyer/lawyer_list";
    }


    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Lawyer lawyer = this.lawyerService.getLawyer(id);
        model.addAttribute("lawyer ", lawyer);
        return "lawyer/lawyer_detail";

    }

    @GetMapping("/create")
    public String lawyerCreate() {
        return "lawyer/lawyer_form";
    }

    @PostMapping("/create")
    public String lawyerCreate(@RequestParam String subject, @RequestParam String content) {
        this.lawyerService.create(subject, content); //질문을 저장
        return "redirect:/lawyer/list"; //질문 저장 후 질문목록으로 이동
    }
}
