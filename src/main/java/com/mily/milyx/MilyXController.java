package com.mily.milyx;

import com.mily.milyxcomment.MilyXCommentService;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/milyx")
@RequiredArgsConstructor
@Controller
public class MilyXController {
    @Autowired
    private MilyXService milyXService;
    private MilyXCommentService milyXCommentService;
    private MilyUserService milyUserService;

    @GetMapping("")
    public String showMilyX() { return "milyx_index"; }

    @GetMapping("/create")
    public String createMilyX() { return "milyx_create"; }

    @PostMapping("/create")
    public String createMilyX(@RequestParam String title, @RequestParam String body) {
        this.milyXService.create(title, body);
        return "redirect:/milyx";
    }
}