package com.example.User.controller;

import com.example.User.VO.ResponseTemplateVo;
import com.example.User.VO.department;
import com.example.User.entity.User;
import com.example.User.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/adduser")
    public ResponseEntity<User>adduser(@RequestBody User user){
        return ResponseEntity.ok().body(userRepo.save(user));
    }

    @GetMapping("/get/{userId}")
   public ResponseEntity<ResponseTemplateVo>getUserwithdept(@PathVariable long userId){
        ResponseTemplateVo vo=new ResponseTemplateVo();
        User user=userRepo.findByUserId(userId);
        department dept=restTemplate.getForObject("http://localhost:9002/department/get/"+user.getDeptId(), department.class);
        vo.setUser(user);
        vo.setDept(dept);
        return ResponseEntity.ok().body(vo);

    }
    @PutMapping("/{deptId}")
    public ResponseEntity<department>update(@PathVariable long deptId,@RequestBody department dept){
        restTemplate.put("http://localhost:9002/department/{deptId}",dept,deptId,department.class);
        return ResponseEntity.ok().body(dept);
    }
    @DeleteMapping("/{deptId}")
    public ResponseEntity<String>deleteById(@PathVariable long deptId){
        restTemplate.delete("http://localhost:9002/department/{deptId}",deptId,department.class);
        return ResponseEntity.ok().body("deleted");
    }


}
