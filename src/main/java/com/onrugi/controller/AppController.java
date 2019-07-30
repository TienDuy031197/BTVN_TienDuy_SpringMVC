package com.onrugi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onrugi.dao.DictionaryDao;
import com.onrugi.dao.DictionaryDaoImpl;
import com.onrugi.dao.UserDao;
import com.onrugi.dao.UserDaoImpl;
import com.onrugi.entity.Dictionary;
import com.onrugi.model.UserDTO;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserDao userDao;

	@Autowired
	DictionaryDao dictDao;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "Spring MVC Java Configuration Example");
		return "index";
	}

	@RequestMapping("/user")
	public String user(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			return "user";
		}
	}

	@RequestMapping("/admin")
	public String admin(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			return "admin";
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			model.addAttribute("dict", new Dictionary());
			return "add";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDict(@ModelAttribute("dict") Dictionary dictionary, Model map) {
		String message = "";
		System.out.println(dictionary.getTienganh());
		dictDao.addWord(dictionary);
		message = "Tao moi thanh cong";
		map.addAttribute("contentMessage", message);
		return "add";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			if (role.equals(1)) {
				return "user";
			} else {
				return "admin";
			}
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object currentRole = session.getAttribute("role");
		if (currentRole == null) {
			System.out.println(name + password);
			Integer role = userDao.loginUser(name, password);
			System.out.println(role);
			if (role == null) {
				String msgRole = new StringBuilder().append("Sorry ").append(name).append(". Incorrect password")
						.toString();
				session.setAttribute("mess", msgRole);
				return "login";
			} else {
				session.setAttribute("role", role);
				session.setAttribute("name", name);
				if (role == 1) {
					return "user";
				} else {
					return "admin";
				}
			}
		}
		Integer role = userDao.loginUser(name, password);
		if (role.equals("1"))
			return "user";
		return "admin";

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("role");
		session.removeAttribute("name");
		session.removeAttribute("mess");
		return "login";
	}

}