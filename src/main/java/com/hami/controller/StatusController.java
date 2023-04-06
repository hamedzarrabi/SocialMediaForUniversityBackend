package com.hami.controller;

import com.hami.entity.Status;
import com.hami.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @PostMapping("/createStatus")
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        Status newStatus = statusService.createStatus(status);
        return new ResponseEntity<Status>(newStatus, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ArrayList<Status>> showAllStatus() {

        ArrayList<Status> newStatus = statusService.getAllStatus();

        return new ResponseEntity<>(newStatus, HttpStatus.OK);
    }
}
