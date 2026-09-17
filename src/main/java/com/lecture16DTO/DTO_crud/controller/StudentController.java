package com.lecture16DTO.DTO_crud.controller;

import com.lecture16DTO.DTO_crud.DTO.CreateStudentRequestDTO;
import com.lecture16DTO.DTO_crud.DTO.CreateStudentResponseDTO;
import com.lecture16DTO.DTO_crud.DTO.UpdateStudentRequestDTO;
import com.lecture16DTO.DTO_crud.DTO.UpdateStudentResponseDTO;
import com.lecture16DTO.DTO_crud.entity.Student;
import com.lecture16DTO.DTO_crud.service.StudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent
    (@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO) {


        CreateStudentResponseDTO createStudentResponsedto
                = studentService.createStudent(createStudentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudentResponsedto);


    }

    //read
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> readStudent(@PathVariable Long id){
        CreateStudentResponseDTO studentResponse = studentService.readStudent(id);
       // return ResponseEntity.ok(studentResponse);
        return ResponseEntity
                .status(HttpStatus.OK)       //comment wala aur ye return method same hai
                .body(studentResponse);

    }
    //read all
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> readAllStudent(){
        List<CreateStudentResponseDTO> studentlist = studentService.getAllStudent();
        return ResponseEntity.ok(studentlist);

    }



    //update
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id  ,
                                                 @RequestBody UpdateStudentRequestDTO studentReq){

       UpdateStudentResponseDTO studentresp =
               studentService.updateStudentResponse(id,studentReq);
//       if(studentresp == null){
//           return ResponseEntity.notFound().build();
//       }
//       else{
//           return ResponseEntity.ok(studentresp);
//       }
        return ResponseEntity.status(HttpStatus.OK).body(studentresp);

    }

    //delete
    @DeleteMapping
    public ResponseEntity<String> DeleteStudent(@RequestParam Long id) {
         studentService.deleteStudent(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        if (isDeleted) {
//            return ResponseEntity.ok("Record deleted");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {
        studentService.deleteStudentSoftly(id);


        return ResponseEntity.noContent().build();
    }







}
