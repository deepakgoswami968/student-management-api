package com.lecture16DTO.DTO_crud.service;

import com.lecture16DTO.DTO_crud.DTO.CreateStudentRequestDTO;
import com.lecture16DTO.DTO_crud.DTO.CreateStudentResponseDTO;
import com.lecture16DTO.DTO_crud.DTO.UpdateStudentRequestDTO;
import com.lecture16DTO.DTO_crud.DTO.UpdateStudentResponseDTO;
import com.lecture16DTO.DTO_crud.ExceptionHandler.DuplicateResourceException;
import com.lecture16DTO.DTO_crud.ExceptionHandler.ResourceNotFoundException;
import com.lecture16DTO.DTO_crud.entity.Student;
import com.lecture16DTO.DTO_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO createStudentRequestDTO){
        Student student = maptoCreateEntity(createStudentRequestDTO);

        if(emailExists(student)){
            throw new DuplicateResourceException("Email id already exists " +student.getEmail()+" already exists");
        }

        Student studentresp = studentRepository.save(student);

        return maptoCreateDTO(studentresp);

      // return studentRepository.save(stud);
    }
    public CreateStudentResponseDTO readStudent(Long id){

        //Optional<Student> studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);
       Student studentResponse = studentRepository.findByIdAndDeletedIsFalse(id)
               .orElseThrow(
                       ()->new ResourceNotFoundException("Student with id "+id+" is not present")
               );
       return maptoCreateDTO(studentResponse);

//        if(studentResponse.isEmpty())
//        {
//            return null;
//        }
        //return maptoCreateDTO(studentResponse.get());

    }
    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentlist = studentRepository.findByDeletedIsFalse();
        return studentlist.stream()
                .map(this::maptoCreateDTO)
                .toList();
    }

    public UpdateStudentResponseDTO updateStudentResponse(Long id , UpdateStudentRequestDTO studentReq){
        //StudentResponseDTO existingStudentdto = new StudentResponseDTO();

        Student existingStudent = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("student with id "+id+" not found"));
//        if(saveStudent.isEmpty()){
//            return null;
//
//        }
        //Student saveStudent = existingStudent;
       // saveStudent.setId(studentReq.getId());
        existingStudent.setName(studentReq.getName());
        //saveStudent.setEmail(studentReq.getEmail());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setUpdatedAt(LocalDateTime.now());
        existingStudent.setDeleted(false);

        Student savedStudent = studentRepository.save(existingStudent);

        return  maptoUpdateDTO(savedStudent);



    }

    public void deleteStudent( Long id){
        Student studentTobeDeleted = studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("student not present with id "+id));
//        if(student){
//            studentRepository.deleteById(id);
//            return true;
//
//        }
//        return false;
        studentRepository.delete(studentTobeDeleted);

  }
    public void deleteStudentSoftly(Long id) {

        Student studentToBeDeleted = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("student with id "+id+" not found"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);

    }

    private Student maptoCreateEntity(CreateStudentRequestDTO createStudentRequestDto){


        Student student=new Student();
        student.setName(createStudentRequestDto.getName());
        student.setEmail(createStudentRequestDto.getEmail());
      //  student.setId(createStudentRequestDto.getId());
        student.setAge(createStudentRequestDto.getAge());
        student.setRollNo(createStudentRequestDto.getRollNo());
        student.setSubject(createStudentRequestDto.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        student.setDeleted(false);
        return student;

    }
    private CreateStudentResponseDTO maptoCreateDTO(Student student){

        CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();

        createStudentResponseDTO.setId(student.getId());
        createStudentResponseDTO.setEmail(student.getEmail());
        createStudentResponseDTO.setAge(student.getAge());
        createStudentResponseDTO.setRollNo(student.getRollNo());
        createStudentResponseDTO.setSubject(student.getSubject());
        createStudentResponseDTO.setCreatedAt(student.getCreatedAt());
        createStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());
        createStudentResponseDTO.setMessage("Student saved successfully");

        return createStudentResponseDTO;



    }

    private UpdateStudentResponseDTO maptoUpdateDTO(Student student){
        UpdateStudentResponseDTO updateStudentResponseDTO = new UpdateStudentResponseDTO();
        updateStudentResponseDTO.setId(student.getId());
        updateStudentResponseDTO.setEmail(student.getEmail());
        updateStudentResponseDTO.setAge(student.getAge());
        updateStudentResponseDTO.setRollNo(student.getRollNo());
        updateStudentResponseDTO.setSubject(student.getSubject());
       // updateStudentResponseDTO.setCreatedAt(student.getCreatedAt());
        updateStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());
        updateStudentResponseDTO.setMessage("Student updated successfully");
        return updateStudentResponseDTO;


    }
    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }

}
