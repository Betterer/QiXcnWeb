package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.Teacher;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.remote.RabbitFeignClient;
import com.qixcnweb.qixian.service.SchoolService;
import com.qixcnweb.qixian.service.TeacherService;
import com.qixcnweb.qixian.service.UserService;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/** 学校相关controller
 * Created by dingxiaochi on 2018/3/29.
 */
@Controller
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @Resource
    private UserService userService;

    @Resource
    private RabbitFeignClient rabbitFeignClient;

    @Resource
    private FileUploadUtils fileUploadUtils;

    @Resource
    private TeacherService teacherService;


    /**
     * 上传学校图片
     * @param file
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/upload_images", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadImages(@RequestParam("file") MultipartFile file){
        //结果集
        Map<String,Object> resultMap = new HashedMap();
       //上传到阿里云服务器,学校介绍目录
        String resultString = schoolService.uploadImages(file, Constant.SCHOOL_IMAGES);
        //如果上传正确
        if(!resultString.equals("upload error")){
            resultMap.put("status",200);
            resultMap.put("fileName",resultString);
        }else{
            resultMap.put("status",500);
            resultMap.put("message",resultString);
        }
        return resultMap;
    }


    /**
     * 学校入驻
     * @param identityImage
     * @param licenseImage
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public String enter(@RequestParam("identityImage") MultipartFile identityImage, @RequestParam("licenseImage") MultipartFile licenseImage,
                        @Valid School school, BindingResult schoolBindResult, HttpServletRequest request){

        //获取当前登录用户
        User sessionUser = (User)request.getSession().getAttribute("user");
        User user = userService.findUserById(sessionUser.getId());

        if(!schoolBindResult.hasErrors()){
            //保存法人身份证信息,上传到阿里云服务器,学校介绍目录
            if(identityImage!=null && !"".equals(identityImage.getOriginalFilename())){
                String identityImageName = schoolService.uploadImages(identityImage, Constant.USER_INDENTITY);
                user.setIdentity(identityImageName);        //更新用户的identity字段
                sessionUser.setIdentity(identityImageName); //更新session中存储的用户信息
            }
            //保存营业执照
            if(licenseImage!=null && !"".equals(licenseImage.getOriginalFilename())){
                String licenseImageName = schoolService.uploadImages(licenseImage, Constant.SCHOOL_LICENSE);
                school.setLicense(licenseImageName);
            }

            //保存school信息
            school.setUser(user);                       //关联用户
            school.setStatus(Constant.SCHOOL_WAITE);    //学校状态为0 :待审核信息
            schoolService.saveSchool(school);


            //更新用户信息
            user.setType(Constant.SCHOOL_ADMIN);        //更新用户类型

            //将用户信息更新到session中
            sessionUser.setType(Constant.SCHOOL_ADMIN);
            request.getSession().setAttribute("user",sessionUser);
            userService.saveUser(user);

            //调用rabbitmq的远程服务发送消息到 "qixian.mail"队列中,mail服务收到消息之后会发送邮件
            rabbitFeignClient.sendMessageWithoutExchange("qixian.mail",user);

            return "redirect:/index";
        }else{
            return "errorpage/500";
        }
    }

    /**
     * 跳转到学校详情页面
     * @param schoolId
     * @return
     */
    @GetMapping("/index/{schoolId}")
    public String toSchoolPage(HttpServletRequest request, Map<String,Object> resultMap, @PathVariable Integer schoolId){
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        //根据ID获取学校信息
        School school = schoolService.findSchoolById(schoolId);
        //Step1:
        //获取学校图片,并且把图片换成能访问的OSS url
        String schoolMainImg = "";
        Map<String,String> schoolImgMap = new HashedMap();
        if(school.getImage()!=null && !"".equals(school.getImage())){
            String image = school.getImage();
            List<String> imageList = Arrays.asList(image.split(","));
            //第一张图片设置为主图,其他的图片为页面图片列表的图片
            for(String imageName : imageList){
                if(imageList.get(0).equals(imageName)){
                    schoolMainImg = fileUploadUtils.getFileUrl(imageName,1000*60*60*3,Constant.OSS_STYLE_SCHOOL_MAIN);
                }else{
                    schoolImgMap.put(imageName,fileUploadUtils.getFileUrl(imageName,1000*60*60*3));
                }
            }
        }

        //Step3: 获取推荐的教师,并且把教师图片转换成能访问的URL
        //用于存储推荐教师
        List<Teacher> recommentTeacherList = new ArrayList<>();
        //将教师图片转换成能能访问的OSS url
        if(school.getTeacherList()!=null){
            for(Teacher teacher : school.getTeacherList()){
                if(teacher.getRecommend()==1){
                    teacher.setImageUrl(fileUploadUtils.getFileUrl(teacher.getImage(),1000*60*60,Constant.OSS_STYLE_TEACHER_IMAGE));
                    recommentTeacherList.add(teacher);
                }
            }
        }
        school.setTeacherList(recommentTeacherList);


        resultMap.put("user",user);
        resultMap.put("school",school);
        resultMap.put("schoolMainImg",schoolMainImg);
        resultMap.put("schoolImgMap",schoolImgMap);
        return "school/school";
    }

    /**
     * 跳转到编辑课程页面
     * @return
     */
    @GetMapping("/edit_lesson_page/{schoolId}")
    @PreAuthorize("isAuthenticated()")
    public String schoolEditLessonPage(HttpServletRequest request, Map<String,Object> resultMap,@PathVariable Integer schoolId){
        User user = (User) request.getSession().getAttribute("user");
        resultMap.put("user",user);
        resultMap.put("schoolId",schoolId);
        return "school/school_edit_lesson";
    }

    /**
     * 跳转到编辑教师页面
     * @return
     */
    @GetMapping("/edit_teacher_page/{schoolId}")
    @PreAuthorize("isAuthenticated()")
    public String schoolEditTeacherPage(HttpServletRequest request, Map<String,Object> resultMap,@PathVariable Integer schoolId){
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        //根据学校ID查询该学校中的教师
        List<Teacher> teacherList = teacherService.findTeacherBySchoolId(schoolId);
        //循环教师列表,将每个教师的image替换成可访问的url
        for(Teacher teacher: teacherList){
            if(teacher.getImage()!=null && !"".equals(teacher.getImage())){
                teacher.setImage(fileUploadUtils.getFileUrl(teacher.getImage(),1000*60*60*3,Constant.OSS_STYLE_TEACHER_IMAGE));
            }
        }

        resultMap.put("user",user);
        resultMap.put("schoolId",schoolId);
        resultMap.put("teacherList",teacherList);
        return "school/school_edit_teacher";
    }


    /**
     * 加载编辑教师信息的弹出框
     * @param teacherId
     * @return
     */
    @GetMapping("/load_edit_teacher_modal/{teacherId}/{schoolId}")
    public String loadEditTeacherModal(Map<String,Object> resultMap,@PathVariable Integer teacherId,@PathVariable Integer schoolId){
        Teacher teacher = new Teacher();
        if(teacherId!=0){
            //根据ID查询到教师信息
            teacher = teacherService.findTeacherById(teacherId);
            //将教师的图片转换成可以访问的格式
            teacher.setImageUrl(fileUploadUtils.getFileUrl(teacher.getImage(),1000*60*60*3,Constant.OSS_STYLE_TEACHER_IMAGE));
        }

        resultMap.put("teacher",teacher);
        resultMap.put("schoolId",schoolId);
        return "school/edit_teacher_modal";
    }


    /**
     * 编辑教师信息
     * @param teacher
     * @param schoolBindResult
     * @param request
     * @return
     */
    @PostMapping("/edit_teacher")
    public String editTeacher(@Valid Integer schoolId,@Valid Teacher teacher, BindingResult schoolBindResult, HttpServletRequest request){
        if(!schoolBindResult.hasErrors()){
            School school = schoolService.findSchoolById(schoolId);
            teacher.setSchool(school);
            teacherService.saveTeacher(teacher);
        }
        return "redirect:/school/edit_teacher_page/"+schoolId;
    }
}
