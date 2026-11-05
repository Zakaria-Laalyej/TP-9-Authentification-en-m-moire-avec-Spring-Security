package ma.fstg.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", auth.getName());
            model.addAttribute("authorities", auth.getAuthorities());
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return "redirect:/user/dashboard";
        }
        return "login";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("authorities", auth.getAuthorities());
        return "user_dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("authorities", auth.getAuthorities());
        return "admin_dashboard";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            model.addAttribute("username", auth.getName());
        }
        return "access_denied";
    }
}
