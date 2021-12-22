package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private static final String REDIRECT_TO_ADMIN = "redirect:/admin";

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ModelAndView allUsers(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", userService.allUsers());
//        modelAndView.addObject("userThis", userService.loadUserByUsername(name));
//        model.addAttribute("userThis", userService.loadUserByUsername(principal.getName()));
//        modelAndView.addObject("usersThis", userService.loadUserByUsername());
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addUser");
        modelAndView.addObject("rolelist", roleService.getRolesForPage());
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam List<String> role) {
        userService.save(user, roleService.getByIds(role));
//        userService.save(user, new HashSet<>(role));

//        Set<Role> roleSet = new HashSet<>();
//        for (String roleId : role) {
//            roleSet.add(roleService.getById(Long.valueOf(roleId)));
//        }
//        user.setRoles(roleSet);
        return REDIRECT_TO_ADMIN;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminEditUser");
        modelAndView.addObject("user", userService.getById(id));
        modelAndView.addObject("rolelist", roleService.getRolesForPage());
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user, @RequestParam List<String> role) {
         userService.edit(user, role);
         return REDIRECT_TO_ADMIN;
     }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return REDIRECT_TO_ADMIN;
    }


}
