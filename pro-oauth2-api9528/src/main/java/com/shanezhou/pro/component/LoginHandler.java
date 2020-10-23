package com.shanezhou.pro.component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@Component
public class LoginHandler {

    public UserDTO getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String user = request.getHeader("user");
        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(user, UserDTO.class);
        //UserDTO userDTO = new UserDTO();
        //userDTO.setUsername(userObject.get("user_name").getAsString());
        //userDTO.setId(userObject.get("id").getAsLong());
        //userDTO.setRoleList(gson.fromJson(userObject.get("authorities"),
        //        new TypeToken<List<String>>(){}.getType()));
        return dto;
    }
}
