package user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import user.repository.UserRepository;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String template = "Hello, %s!";

	@Resource
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public
	@ResponseBody
	String sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
		return String.format(template, name);
	}

}