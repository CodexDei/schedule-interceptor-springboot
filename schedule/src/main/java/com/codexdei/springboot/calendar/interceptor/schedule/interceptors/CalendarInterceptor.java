package com.codexdei.springboot.calendar.interceptor.schedule.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

//Nos permite inyectar los properties
@Component
// Para interceptores hay que implementar HandlerInterceptor
public class CalendarInterceptor implements HandlerInterceptor {

        @Value("${config.calendar.open}")
        private Integer open;
        @Value("${config.calendar.close}")
        private Integer close;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                        throws Exception {

                Calendar calendar = Calendar.getInstance();
                int curretTIme = calendar.get(calendar.HOUR_OF_DAY);
                System.out.println(curretTIme);

                if (curretTIme >= open && curretTIme <= close) {

                        StringBuilder message = new StringBuilder("Welcome to the customer service system");
                        message.append(" our business hours are from '");
                        message.append(open);
                        message.append("' hours");
                        message.append(" to ");
                        message.append(close);
                        message.append(" hours ");
                        message.append("Thank you for your visit");
                        request.setAttribute("message", message.toString());

                        return true;
                
                }else{

                        ObjectMapper mapper = new ObjectMapper();
                        Map<String,Object> data = new HashMap<>();
                        StringBuilder message = new StringBuilder("Sorry, We're closed, ");
                        message.append("outside of the customer service hours. ");
                        message.append("We server during hours '");
                        message.append(open);
                        message.append("' hours to '");
                        message.append(close);
                        message.append("' hours. ");
                        message.append(" Thank you!");

                        data.put("error", message.toString());
                        data.put("date", new Date().toString());

                        response.setContentType("application/json");
                        response.setStatus(401);
                        response.getWriter().write(mapper.writeValueAsString(data));
                        
                        return false;

                }

                
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                        @Nullable ModelAndView modelAndView) throws Exception {

        }

}
