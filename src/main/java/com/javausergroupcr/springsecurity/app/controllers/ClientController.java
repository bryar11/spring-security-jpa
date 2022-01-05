package com.javausergroupcr.springsecurity.app.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javausergroupcr.springsecurity.app.models.entity.Client;
import com.javausergroupcr.springsecurity.app.models.service.IClientService;

@Controller
@SessionAttributes("client")
public class ClientController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IClientService clientService;

	@RequestMapping(value = { "/list", "/" }, method = RequestMethod.GET)
	public String list(Model model, Authentication authentication, HttpServletRequest request) {

		if(null != authentication) {
			if (hasRole("ROLE_ADMIN")) {
				logger.info("Hola ".concat(authentication.getName()).concat(" eres admin"));
			} else {
				logger.info("Hola ".concat(authentication.getName()).concat(" NO eres admin"));
			}
		}

		List<Client> clients = clientService.findAll();
		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("clients", clients);
		return "list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String create(Map<String, Object> model) {

		Client client = new Client();
		model.put("client", client);
		model.put("title", "Crear cliente");
		return "form";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/form/{id}")
	public String update(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Client client = null;
		client = clientService.findOne(id);
		if (null == client) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/list";
		}
		model.put("client", client);
		model.put("title", "Editar cliente");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("title", "Formulario de cliente");
			return "form";
		}

		String message = (null != client.getId()) ? "Cliente editado con éxito" : "Cliente creado con éxito";

		clientService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", message);
		return "redirect:list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			clientService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}

		return "redirect:/list";
	}

	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (null == context) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (null == auth) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));

	}
}
