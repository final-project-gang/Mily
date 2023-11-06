package com.mily.article.milyx;

import com.mily.article.milyx.category.CategoryService;
import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/milyx")
public class MilyXController {
    private final CategoryService categoryService;
    private final MilyXService milyXService;
    private final MilyUserService milyUserService;
    private final Rq rq;

    @PreAuthorize("isAnonymous()")
    @GetMapping("")
    public String showMilyX(Model model) {
        List<MilyX> milyx = milyXService.getAllPosts();
        Collections.reverse(milyx);
        model.addAttribute("milyx", milyx);
        System.out.println(milyx);
        return "mily/milyx/milyx_index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(Model model) {
        MilyUser isLoginedUser = milyUserService.getCurrentUser();

        if (isLoginedUser == null) {
            return "mily/milyx/milyx_index";
        }

        int userPoint = isLoginedUser.getMilyPoint();
        model.addAttribute("myPoint", userPoint);

        if (userPoint < 50) {
            return "/mily/payment/payment";
        }

        // 최대값 = 해당 유저가 가진 포인트 or 100 중 작은 값으로 결정
        int maxPoint = Math.min(userPoint, 100);

        // 100까지 5 단위의 숫자 리스트 생성
        List<Integer> pointOptions = IntStream.rangeClosed(50, maxPoint)
                .filter(i -> i % 5 == 0)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pointOptions", pointOptions);

        List<FirstCategory> firstCategories = categoryService.getFirstCategories();
        model.addAttribute("firstCategories", firstCategories);
        return "mily/milyx/milyx_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@RequestParam int firstCategory, @RequestParam String secondCategory, @RequestParam String subject, @RequestParam String body, @RequestParam int point) {
        MilyUser isLoginedUser = milyUserService.getCurrentUser();

        FirstCategory fc = categoryService.findByFId(firstCategory);
        SecondCategory sc = categoryService.findBySId(Integer.parseInt(secondCategory));

        RsData<MilyX> rsData = milyXService.create(isLoginedUser, fc, sc, subject, body, point);
        return "redirect:/milyx";
    }

    @GetMapping("/secondCategories")
    public ResponseEntity<List<SecondCategory>> validateId(@RequestParam(value = "firstCategoryId") int firstCategoryId) {
        List<SecondCategory> sc = categoryService.findByFirstCategoryId(firstCategoryId);
        return ResponseEntity.ok().body(sc);
    }

    @AllArgsConstructor
    @Getter
    public static class CreateForm {
        private FirstCategory firstCategory;
        private SecondCategory secondCategory;
        private String subject;
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable long id) {
        MilyX milyX = milyXService.findById(id).get();
        int view = milyX.getView() + 1;

        MilyX mx = MilyX.builder()
                .view(view)
                .build();

        milyX.updateView(view);
        milyXService.updateView(milyX.getId(), mx);

        model.addAttribute("milyx", milyX);

        return "mily/milyx/milyx_detail";
    }
}