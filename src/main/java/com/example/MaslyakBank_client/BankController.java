package com.example.MaslyakBank_client;

import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BankController {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/money/{userId}")
    public String getBalance(@PathVariable int userId, Model model) {
        Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);

        if (userBalance.isPresent()) {
            model.addAttribute("balance_usd", userBalance.get());
        } else {
            model.addAttribute("balance_usd", "Пользователь не найден");
        }

        return "money";
    }
}
