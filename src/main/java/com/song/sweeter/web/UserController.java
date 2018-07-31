package com.song.sweeter.web;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.aop.LoggerManager;
import com.song.sweeter.comm.Const;
import com.song.sweeter.comm.ResEnum;
import com.song.sweeter.comm.dto.GiveDto;
import com.song.sweeter.comm.dto.ResDto;
import com.song.sweeter.comm.dto.UserDto;
import com.song.sweeter.comm.resource.ResourceFactory;
import com.song.sweeter.entity.Give;
import com.song.sweeter.entity.Consume;
import com.song.sweeter.entity.User;
import com.song.sweeter.service.GiveService;
import com.song.sweeter.service.ConsumeService;
import com.song.sweeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController extends BaseController {
    //TODO  songxd:用户分离锁

    @Resource
    private UserService userService;
    @Autowired
    private ResourceFactory resourceFactory;
    @Autowired
    private ConsumeService consumeService;
    @Autowired
    private GiveService giveService;


    @RequestMapping("/")
    public String index(){
        return "login_page";
    }

    /**
     * 重定向
     * @param uri
     * @return
     */
    @RequestMapping("redirect/{uri}")
    public String redirect(@PathVariable("uri") String uri){
        return "redirect:/"+ uri;
    }

    /**
     * 页面跳转
     * @param pagePath
     * @return
     */
    @RequestMapping("toPage/{pagePath}")
    public String toPage(@PathVariable("pagePath") String pagePath){
        return pagePath;
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManager(description="登陆")
    public String login(Model model, User loginUser, HttpServletResponse response){
        if(loginUser.getUserName() == null || loginUser.getUserName().isEmpty()
                || loginUser.getPassword() == null || loginUser.getPassword().isEmpty()){
            model.addAttribute(Const.TIPS, Const.MSG_001);
            return "login_page";
        }

        User userDB = userService.findByUserName(loginUser.getUserName());
        if(userDB == null || !Objects.equals(userDB.getPassword(), loginUser.getPassword())){
            model.addAttribute(Const.TIPS, Const.MSG_002);
            return "login_page";
        }

        userService.updateLastLogin(new Date(), userDB.getId());
        UserDto userDto = userDB.genUserDto();
        Cookie cookie = new Cookie(Const.SESSION_KEY_USER, userDto.getId().toString());
        cookie.setMaxAge(Const.COOKIE_TIMEOUT);
        cookie.setPath("/");
        response.addCookie(cookie);
        getSession().setAttribute(Const.SESSION_KEY_USER, userDto);

        System.out.println("login:" + userDB.getUserName() + " sessionId:"+getSession().getId());

        //成功登陆进入主页
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        UserDto userDto = getUserDto();
        if(userDto == null){
            return "login_page";
        }
        User user = userService.findUserById(userDto.getId());
        if(user == null){
            return "login_page";
        }
        if(user.getHalf() > 0){
            User half = userService.findUserById(user.getHalf());
            user.setHalfName(half.getUserName());
        }
        model.addAttribute("user", user);
        List<ResDto> resDtos = new ArrayList<>();
        for(ResEnum resEnum : ResEnum.values()){
            ResDto temp = resEnum.genResDto(user);
            resDtos.add(temp);
        }
        model.addAttribute("resDtos", resDtos);
        //成功登陆进入主页
        return "home_page";
    }

    @RequestMapping(value = "/test")
    public String test(Model model){
        model.addAttribute(Const.TIPS, Const.MSG_007);
        return "login_page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, User loginUser){
        if(loginUser.getUserName() == null || loginUser.getUserName().isEmpty()
                || loginUser.getPassword() == null || loginUser.getPassword().isEmpty()){
            model.addAttribute(Const.TIPS, Const.MSG_001);
            return "register_page";
        }

        synchronized (this){
            User userDB = userService.findByUserName(loginUser.getUserName());
            if(userDB != null){
                model.addAttribute(Const.TIPS, Const.MSG_003);
                return "/register_page";
            }
            userService.save(loginUser);
        }
        return "login_page";
    }

    private String getUserName(Long id, Map<Long, String> map){
        String name = map.get(id);
        if(name == null){
            name = userService.findUserById(id).getUserName();
            map.put(id, name);
        }
        return name;
    }

    @RequestMapping(value = "/viewHistory")
    public String viewHistory(Model model){
        UserDto userDto = getUserDto();
        if(userDto == null){
            return "login_page";
        }
        Map<Long, String> idNameMap = new HashMap<>();
        List<Consume> uses = consumeService.findAll();
        for(Consume use : uses){
            use.setFromUserName(getUserName(use.getFromUser(), idNameMap));
            use.setToUserName(getUserName(use.getToUser(), idNameMap));
            use.setClosable(historyClosable(userDto.getId(), use));
        }
        List<Give> gives = giveService.findAll();
        for(Give give : gives) {
            give.setFromUserName(getUserName(give.getFromUser(), idNameMap));
            give.setToUserName(getUserName(give.getToUser(), idNameMap));
        }
        model.addAttribute("uses", uses);
        model.addAttribute("gives", gives);
        return "history_page";
    }

    @RequestMapping("/close")
    public String close(Long id) {
        UserDto userDto = getUserDto();
        if(userDto == null){
            return "login_page";
        }
        Consume use = consumeService.findById(id);
        if(!historyClosable(userDto.getId(), use)){
            return "redirect:/viewHistory";
        }
        consumeService.updateState(StateEnum.FIN, use.getId());
        return "redirect:/viewHistory";
    }

    private boolean historyClosable(Long userId, Consume use){
        return use != null && Objects.equals(use.getFromUser(), userId)
                && use.getState() != StateEnum.FIN;
    }

    @RequestMapping("/use")
    public String use(Model model, String key, String content) {
        UserDto userDto = getUserDto();
        if(userDto == null) {
            return "login_page";
        }
        System.out.println("key:"+key + " content:"+content);
        ResEnum resEnum = null;
        try{
            resEnum = ResEnum.valueOf(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(resEnum == null){
            return "redirect:/home";
        }
//        if(content == null || content.isEmpty()){
//            model.addAttribute(Const.TIPS, Const.MSG_006);
//            return
//        }
        User user = userService.findUserById(userDto.getId());
        if(user == null){
            return "login_page";
        }
        ResDto resDto = resEnum.genResDto(user);
        if(resDto.getNum() <= 0){
            return "redirect:/home";
        }
        ResDto consumeDto = new ResDto(resEnum, 1);
        if(!resourceFactory.consume(userDto, consumeDto, Const.RES_LOG_001)){
            return "redirect:/home";
        }

        Consume consume = new Consume();
        consume.setFromUser(userDto.getId());
        consume.setToUser(user.getHalf());
        consume.setContent(content);
        consume.setRes(resEnum);
        consumeService.save(consume);
        return "redirect:/home";
    }

    @RequestMapping("/getUsePage")
    public String getUsePage(Model model, String key) {
        UserDto userDto = getUserDto();
        if(userDto == null){
            return "login_page";
        }
        ResEnum resEnum = null;
        try{
            resEnum = ResEnum.valueOf(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(resEnum == null){
            return "redirect:/home";
        }
        model.addAttribute("resKey", key);
        model.addAttribute("resName", resEnum.getName());
        return "use_page";
    }

    @RequestMapping("/getGivePage")
    public String getGivePage(Model model) {
        UserDto userDto = getUserDto();
        if(userDto == null){
            return "login_page";
        }
        User user = userService.findUserById(userDto.getId());
        if(user.getHalf() <= 0){
            return "redirect:/home";
        }
        User half = userService.findUserById(user.getHalf());
        model.addAttribute("halfName", half.getUserName());
        return "give_page";
    }

    @RequestMapping(value = "/give", method = RequestMethod.POST)
    public String give(Model model, @Valid GiveDto giveDto, BindingResult result){
        UserDto userDto = getUserDto();
        if(userDto == null) {
            return "login_page";
        }

        if(result.hasErrors()){
            model.addAttribute(Const.TIPS, Const.MSG_008);
            return "give_page";
        }
        if((giveDto.getXing() <= 0 && giveDto.getDong() <= 0)){
            model.addAttribute(Const.TIPS, Const.MSG_009);
            return "give_page";
        }
        User user = userService.findUserById(userDto.getId());
        if(user.getHalf() <= 0){
            return "redirect:/home";
        }
        Give give = new Give();
        give.setFromUser(userDto.getId());
        give.setToUser(user.getHalf());
        give.setXing(giveDto.getXing());
        give.setDong(giveDto.getDong());
        give.setContent(giveDto.getContent());
        giveService.save(give);
        if(giveDto.getXing() > 0){
            userService.changeXing(user.getHalf(), giveDto.getXing());
        }
        if(giveDto.getDong() > 0){
            userService.changeDong(user.getHalf(), giveDto.getDong());
        }
        return "redirect:/home";
    }
}
