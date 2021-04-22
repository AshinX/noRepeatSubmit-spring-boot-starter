package com.fengyue95.noRepeatSubmitspringbootstarter.web;

import com.fengyue95.noRepeatSubmitspringbootstarter.annotation.NoRepeatSubmit;
import com.fengyue95.noRepeatSubmitspringbootstarter.util.IpAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengyue
 * @date 2021/4/22
 */
@RestController
public class IndexController {

    @GetMapping("/index")
//    @NoRepeatSubmit(lockPrefix = )
    public String index(@RequestParam(value = "str") String str,HttpServletRequest request){
        String ipAddress = IpAddressUtil.getIpAddress(request);
        return ipAddress+"--"+str;
    }


}
