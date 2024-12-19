package com.example.practice16.controller;

import com.example.practice16.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class AuthController {

    // Регистрация нового пользователя
    @GetMapping("/")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        // Проверка на пустые значения
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Имя пользователя и пароль не должны быть пустыми!");
            return "register";
        }
        User newUser = new User(username, password);
        model.addAttribute("user", newUser);  // Сохраняем пользователя в сессии
        return "redirect:/login";
    }

    // Показать страницу входа
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Обработка входа
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Проверка данных сессии
        User registeredUser = (User) model.getAttribute("user");
        if (registeredUser == null || !registeredUser.getUsername().equals(username) || !registeredUser.getPassword().equals(password)) {
            model.addAttribute("error", "Неверное имя пользователя или пароль!");
            return "login";
        }
        return "redirect:/home";
    }

    // Главная страница
    @GetMapping("/home")
    public String showHomePage(Model model) {
        // Проверяем, есть ли пользователь в сессии
        if (!model.containsAttribute("user")) {
            return "redirect:/login";
        }
        User user = (User) model.getAttribute("user");
        assert user != null;
        model.addAttribute("username", user.getUsername());
        return "home";
    }

    // Обработка обратной связи
    @PostMapping("/feedback")
    public String handleFeedback(@RequestParam String name, @RequestParam String email, @RequestParam String message, Model model) {
        model.addAttribute("feedback", "Ваше сообщение отправлено успешно!");
        return "home";
    }
}
