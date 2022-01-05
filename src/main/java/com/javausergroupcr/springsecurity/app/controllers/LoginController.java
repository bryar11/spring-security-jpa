package com.javausergroupcr.springsecurity.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (null != principal) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/";
		}

		if (null != error) {
			model.addAttribute("error", "Usuario o contraseña incorrecta, por favor vuelva a intentarlo");
		}

		if (null != logout) {
			model.addAttribute("success", "Ha cerrado sesión con éxito");
		}
		
		model.addAttribute("title", "Iniciar sesión");

		return "login";
	}
}
