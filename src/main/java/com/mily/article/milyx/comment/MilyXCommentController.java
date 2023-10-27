package com.mily.article.milyx.comment;

import com.mily.article.milyx.MilyX;
import com.mily.article.milyx.MilyXService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/milyxcomment")
public class MilyXCommentController {
    private final MilyXService milyXService;
    private final MilyXCommentService milyXCommentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment (Model model, @PathVariable("id") long id, @RequestParam String body) {
        MilyX milyX = milyXService.findById(id).get();
        milyXCommentService.createComment(milyX, body);
        return String.format("redirect:/milyx/detail/%s", id);
    }
}