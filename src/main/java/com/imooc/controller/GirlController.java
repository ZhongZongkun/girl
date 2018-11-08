package com.imooc.controller;

import com.imooc.entity.Girl;
import com.imooc.entity.Result;
import com.imooc.repository.GirlRepository;
import com.imooc.service.GirlService;
import com.imooc.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;


    @GetMapping("/girls")
    public Result<List<Girl>> girlList() {
        logger.info("girl list");
        return ResultUtil.success(girlRepository.findAll());
    }

    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResultUtil.error(1, errorMsg);
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());
        girl.setMoney(girl.getMoney());

        return ResultUtil.success(girlRepository.save(girl));
    }

    @GetMapping("/girl/{id}")
    public Result<Girl> getGirl(@PathVariable("id") Integer id) {
        return ResultUtil.success(girlRepository.findOne(id));
    }

    @PutMapping("/girl/{id}")
    public Result<Girl> girlUpdate(@PathVariable("id") Integer id, @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age, @RequestParam("money") Integer money) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setMoney(money);
        return ResultUtil.success(girlRepository.save(girl));

    }

    @DeleteMapping("/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    @GetMapping("/girls/age/{age}")
    public Result<List<Girl>> girlListByAge(@PathVariable("age") Integer age) {
        return ResultUtil.success(girlRepository.findByAge(age));
    }

    //@DeleteMapping("/girl/{id}")
    @PostMapping("/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    @GetMapping("/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}
