package dev.example.bettingstake.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stake")
public class StakeController {

    @RequestMapping(value="/{customerid}/session",method= RequestMethod.GET)
    @ResponseBody
    public  String getSessionKey(@PathVariable String customerid){

        String c=customerid;
        return "Hello World!"+c ;

    }

    @RequestMapping(value="/{betofferid}/stake",method= RequestMethod.POST)
    @ResponseBody
    public  String addStake(String betofferid,String sessionkey){


        return "Hello World!";
    }


}
