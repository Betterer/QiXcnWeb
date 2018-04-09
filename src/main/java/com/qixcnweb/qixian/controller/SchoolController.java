package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.domain.*;
import com.qixcnweb.qixian.remote.RabbitFeignClient;
import com.qixcnweb.qixian.service.*;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
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

    @Resource
    private LessonServicer lessonServicer;

    @Resource
    private CategoryService categoryService;


    /**
     * 跳转到学校列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String schoolList(@RequestParam(defaultValue = "name") String searchBy,
                             @RequestParam(defaultValue = "1") String currentPage,
                             String condition,
                             Map<String,Object> resultMap){
        //根据条件分页查询
        Page<School> schoolByPage = schoolService.findSchoolByPage(searchBy, Integer.parseInt(currentPage)-1, condition);
        //将学校图片装换成能访问的OSS URL
        List<School> schoolList = schoolByPage.getContent();
        for(School school:schoolList){
            if(school.getImage()!=null && !"".equals(school.getImage())){
                school.setImageUrl(fileUploadUtils.getFileUrl(school.getImage(),1000*60*60*3,Constant.OSS_STYLE_SCHOOL_IMG_LIST));
            }
        }
        resultMap.put("page",schoolByPage);
        resultMap.put("searchBy",searchBy);
        resultMap.put("condition",condition);
        resultMap.put("schoolList",schoolList);

        return "school/list";
    }



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
    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
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
        Map<String,String> environmenImageMap = new HashedMap();
        if(school.getImage()!=null && !"".equals(school.getImage())){
            schoolMainImg = fileUploadUtils.getFileUrl(school.getImage(),1000*60*60*3,Constant.OSS_STYLE_SCHOOL_MAIN);
        }

        //Step2:获取学校环境图片,并且把课程图片转换成可能访问的url
        if(school.getEnvironment()!=null && !"".equals(school.getEnvironment())){
            String environmenImage = school.getEnvironment();
            List<String> imageList = Arrays.asList(environmenImage.split(","));
            //第一张图片设置为主图,其他的图片为页面图片列表的图片
            for(String imageName : imageList){
                environmenImageMap.put(imageName,fileUploadUtils.getFileUrl(imageName,1000*60*60*3));
            }
        }


        //Step3:获取相关课程,并且把课程图片转换成可能访问的url
        //用于存放状态正常的课程
        List<Lesson> normalLessonList = new ArrayList<>();
        //将课程图片转换成能访问的OSS url
        if(school.getLessonList()!=null){
            for(Lesson lesson:school.getLessonList()){
                if(lesson.getStatus()==Constant.LESSON_STATUS_NORMAL){
                    lesson.setImageUrl(fileUploadUtils.getFileUrl(lesson.getImage(),1000*60*60*3,Constant.OSS_STYLE_LESSON_EDIT));
                    normalLessonList.add(lesson);
                }
            }
        }
        school.setLessonList(normalLessonList);



        //Step4: 获取推荐的教师,并且把教师图片转换成能访问的URL
        //用于存储推荐教师
        List<Teacher> recommentTeacherList = new ArrayList<>();
        //将教师图片转换成能能访问的OSS url
        if(school.getTeacherList()!=null){
            for(Teacher teacher : school.getTeacherList()){
                if(teacher.getRecommend()==Constant.TEACHER_RECOMMEND && teacher.getStatus()==Constant.TEACHER_STATUS_NORMAL){
                    teacher.setImageUrl(fileUploadUtils.getFileUrl(teacher.getImage(),1000*60*60*3,Constant.OSS_STYLE_TEACHER_IMAGE));
                    recommentTeacherList.add(teacher);
                }
            }
        }
        school.setTeacherList(recommentTeacherList);


        resultMap.put("user",user);
        resultMap.put("school",school);
        resultMap.put("schoolMainImg",schoolMainImg);
        resultMap.put("environmenImageMap",environmenImageMap);
        return "school/school";
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
        //根据学校ID查询学校信息
        School school = schoolService.findSchoolById(schoolId);
        //如果当前用户是学校的管理员
        if(school.getUser().getId().equals(user.getId())){
            //根据学校ID查询该学校中的教师,状态可用的
            List<Teacher> teacherList = teacherService.findTeacherBySchoolIdAndStatus(schoolId,Constant.TEACHER_STATUS_NORMAL);
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
        }else{
            return "errorpage/403";
        }
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
     * @param bindResult
     * @param request
     * @return
     */
    @PostMapping("/edit_teacher")
    @PreAuthorize("isAuthenticated()")
    public String editTeacher(@Valid Integer schoolId,@Valid Teacher teacher, BindingResult bindResult, HttpServletRequest request){
        //获取当前用户
        User user = (User) request.getAttribute("user");
        if(!bindResult.hasErrors()){
            School school = schoolService.findSchoolById(schoolId);
            //如果当前用户是学校管理员
            if(school.getUser().getId().equals(user.getId())){
                teacher.setSchool(school);
                teacher.setStatus(Constant.TEACHER_STATUS_NORMAL);
                teacherService.saveTeacher(teacher);
            }
        }
        return "redirect:/school/edit_teacher_page/"+schoolId;
    }


    /**
     * 删除教师
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "/delete_teacher/{teacherId}/{schoolId}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Map deleteTeacher(@PathVariable Integer teacherId,@PathVariable Integer schoolId, HttpServletRequest request){

        Map<String,Object> map = new HashedMap();
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        School school = schoolService.findSchoolById(schoolId);
        Teacher teacher = teacherService.findTeacherById(teacherId);
        //如果当前用户是学校管理员
        if(school.getUser().getId().equals(user.getId())){
            teacher.setStatus(Constant.TEACHER_STATUS_DELETE);
            teacherService.saveTeacher(teacher);
        }

        map.put("status",200);
        return map;
    }

    /**
     * 跳转到编辑课程页面
     * @return
     */
    @GetMapping("/edit_lesson_page/{schoolId}")
    @PreAuthorize("isAuthenticated()")
    public String schoolEditLessonPage(HttpServletRequest request, Map<String,Object> resultMap,@PathVariable Integer schoolId){
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        //根据学校ID查询学校信息
        School school = schoolService.findSchoolById(schoolId);
        //如果当前用户是学校管理员
        if(school.getUser().getId().equals(user.getId())){
            //根据学校ID查询学校中的课程
            List<Lesson> lessonList = lessonServicer.findLessonBySchoolIdAndStatus(schoolId,Constant.LESSON_STATUS_NORMAL);
            //循环课程,将课程图片转换成可访问的OSS url
            for(Lesson lesson : lessonList){
                if(lesson.getImage()!=null && !"".equals(lesson.getImage())){
                    lesson.setImageUrl(fileUploadUtils.getFileUrl(lesson.getImage(),1000*60*60*3,Constant.OSS_STYLE_LESSON_EDIT));
                }
            }
            resultMap.put("user",user);
            resultMap.put("schoolId",schoolId);
            resultMap.put("lessonList",lessonList);
            return "school/school_edit_lesson";

        }else{
            return "errorpage/403";
        }
    }

    /**
     * 加载编辑课程信息的弹出框
     * @param lessonId
     * @return
     */
    @GetMapping("/load_edit_lesson_modal/{lessonId}/{schoolId}")
    public String loadEditLessonModal(Map<String,Object> resultMap,@PathVariable Integer lessonId,@PathVariable Integer schoolId){
        Lesson lesson = new Lesson();
        if(lessonId!=0){
            //根据ID查询到课程信息
            lesson = lessonServicer.findLessonById(lessonId);
            //将课程的图片转换成可以访问的格式
            if(lesson.getImage()!=null && !"".equals(lesson.getImage())){
                lesson.setImageUrl(fileUploadUtils.getFileUrl(lesson.getImage(),1000*60*60*3,Constant.OSS_STYLE_LESSON_EDIT));
            }
        }
        //根据学校ID查询该学校老师
        List<Teacher> teacherList = teacherService.findTeacherBySchoolIdAndStatus(schoolId, Constant.TEACHER_STATUS_NORMAL);
        //将教师的图片转换成可以访问的格式
        for(Teacher teacher:teacherList){
            if(teacher.getImage()!=null && !"".equals(teacher.getImage())){
                teacher.setImageUrl(fileUploadUtils.getFileUrl(teacher.getImage(),1000*60*60*3,Constant.OSS_STYLE_HEAD_MID));
            }
        }
        //查询所有的培训类别
        List<Category> categoryList = categoryService.findAllByLevel(Constant.CATEGORY_LEVEL_1);

        //将课程教师的ID拼凑成一个字符串
        String teacherIds = "";
        List teacherIdList = new ArrayList();
        if(lesson.getTeacherList()!=null){
            for(Teacher teacher : lesson.getTeacherList()){
                if(!"".equals(teacherIds)){
                    teacherIds = teacherIds.concat(","+teacher.getId());
                }else{
                    teacherIds = teacherIds.concat(teacher.getId().toString());
                }
                teacherIdList.add(teacher.getId());
            }
        }


        resultMap.put("lesson",lesson);
        resultMap.put("schoolId",schoolId);
        resultMap.put("teacherList",teacherList);
        resultMap.put("teacherIds",teacherIds);
        resultMap.put("teacherIdList",teacherIdList);
        resultMap.put("categoryList",categoryList);

        return "school/edit_lesson_modal";
    }

    /**
     * 编辑教师信息
     * @param lesson
     * @param bindResult
     * @param request
     * @return
     */
    @PostMapping("/edit_lesson")
    @PreAuthorize("isAuthenticated()")
    public String editLesson(@Valid Integer schoolId,@Valid Integer categoryId,@Valid String teacherIds,
                             @Valid Lesson lesson, BindingResult bindResult, HttpServletRequest request){
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if(!bindResult.hasErrors()){
            School school = schoolService.findSchoolById(schoolId);
            //如果当前用户是学校管理员
            if(school.getUser().getId().equals(user.getId())){
                //课程关联学校
                lesson.setSchool(school);
                //课程关联类目
                if(categoryId!=null && !"".equals(categoryId)){
                    Category category = categoryService.findCategoryById(categoryId);
                    lesson.setCategory(category);
                }
                //课程关联老师
                if(teacherIds!=null && !"".equals(teacherIds)){
                    List<String> idList = Arrays.asList(teacherIds.split(","));
                    List<Teacher> teacherList = teacherService.findTeacherByIds(idList);
                    lesson.setTeacherList(teacherList);
                }
                //默认状态是1:正常
                lesson.setStatus(Constant.LESSON_STATUS_NORMAL);
                //保存
                lessonServicer.saveLesson(lesson);
            }
        }
        return "redirect:/school/edit_lesson_page/"+schoolId;
    }

    /**
     * 删除课程
     * @param lessonId
     * @return
     */
    @RequestMapping(value = "/delete_lesson/{lessonId}/{schoolId}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Map deleteLesson(@PathVariable Integer lessonId,@PathVariable Integer schoolId, HttpServletRequest request){

        Map<String,Object> map = new HashedMap();
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        School school = schoolService.findSchoolById(schoolId);
        Lesson lesson = lessonServicer.findLessonById(lessonId);
        //如果当前用户是学校管理员
        if(school.getUser().getId().equals(user.getId())){
            lesson.setStatus(Constant.TEACHER_STATUS_DELETE);
            lessonServicer.saveLesson(lesson);
        }

        map.put("status",200);
        return map;
    }
}
