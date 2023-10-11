package com.mily.milyx;

import com.mily.milyxcomment.MilyXCommentService;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/milyx")
@RequiredArgsConstructor
@Controller
public class MilyXController {
    private MilyXService milyXService;
    private MilyXCommentService milyXCommentService;
    private MilyUserService milyUserService;

    @GetMapping("")
    public String showMilyX() { return "milyx_index"; }
}