package com.baomidou.springwind.controller;

import com.baomidou.springwind.service.IAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 投资顾问表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-28
 */
@Controller
@RequestMapping("/features/advisor")
public class AdvisorController {

    @Autowired
    private IAdvisorService advisorService;
    
}
