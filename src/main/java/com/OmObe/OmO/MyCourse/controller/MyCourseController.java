package com.OmObe.OmO.MyCourse.controller;

import com.OmObe.OmO.MyCourse.dto.MyCourseDto;
import com.OmObe.OmO.MyCourse.entity.MyCourse;
import com.OmObe.OmO.MyCourse.mapper.MyCourseMapper;
import com.OmObe.OmO.MyCourse.service.MyCourseService;
import com.OmObe.OmO.auth.jwt.TokenDecryption;
import com.OmObe.OmO.member.entity.Member;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/mycourse")
public class MyCourseController {

    private final MyCourseService myCourseService;
    private final MyCourseMapper mapper;
    private final TokenDecryption tokenDecryption;

    public MyCourseController(MyCourseService myCourseService,
                              MyCourseMapper mapper,
                              TokenDecryption tokenDecryption) {
        this.myCourseService = myCourseService;
        this.mapper = mapper;
        this.tokenDecryption = tokenDecryption;
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity postCourse(@RequestBody MyCourseDto.Post postDto,
                                     @RequestHeader("Authorization") String token){
        List<MyCourse> courseList = mapper.coursePostDtoToCourse(postDto);
        Member writer = tokenDecryption.getWriterInJWTToken(token);

        MyCourse myCourse = myCourseService.createCourse(courseList,writer);
        MyCourseDto.Response response = mapper.courseToCourseResponseDto(myCourse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/rebuild")
    public ResponseEntity patchCourse(){

    }

    @GetMapping("/{course-id}")
    public ResponseEntity getCourse(){

    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity deleteCourse(){

    }
}
