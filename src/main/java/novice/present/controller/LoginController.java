package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.domain.form.LoginForm;
import novice.present.repository.UserRepository;
import novice.present.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        log.info("로그인 화면 호출");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Validated @ModelAttribute("loginInfo") LoginForm form, BindingResult bindingResult
    ) {
        log.info("로그인 요청");

        User user = userService.login(form.getLoginId(), form.getPassword(), bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("로그인 field error: {}", bindingResult.getFieldErrors());
            log.info("로그인 global error: {}", bindingResult.getGlobalErrors());
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "forgot-password";
    }
}
