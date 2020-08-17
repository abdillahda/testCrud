package com.test.springDataJPA.Controller;

import com.test.springDataJPA.Model.User;
import com.test.springDataJPA.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String viewHomePage(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<User> userPage = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//        List<User> listUser = userService.listUser();
//        System.out.println(listUser.get(0));
        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("listUser", userPage);
        return "index";
    }
//    @RequestMapping(value = "/search",method = RequestMethod.GET)
//    public String findOne(@RequestParam(value = "Search") String user){
//        userService.findUser(user);
//        return "";
//    }

    @RequestMapping("/new_user")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("User", user);

        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("User") User user) {
        System.out.println(user.getStatus());
        user.setCreated(new Date());
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("User") User user) {
        System.out.println(user.getStatus());
        User user1 = userService.get(user.getUserId());
        user.setCreated(user1.getCreated());
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int userId) {
        ModelAndView mav = new ModelAndView("edit_user");
        System.out.println(userId);
        User user = userService.get(userId);
        System.out.println(userId);
        mav.addObject("User", user);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

}
