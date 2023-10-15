package com.mily.user.lawyerUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class LawyerUserController {
    private final LawyerUserService lawyerUserService;
}
