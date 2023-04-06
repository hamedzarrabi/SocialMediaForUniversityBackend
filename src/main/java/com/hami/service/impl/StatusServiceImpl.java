package com.hami.service.impl;

import com.hami.entity.Status;
import com.hami.repository.statusRepository;
import com.hami.service.StatusService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class StatusServiceImpl implements StatusService {

   @Autowired
   private statusRepository statusRepository;
   @Autowired
   private UserService userService;

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public ArrayList<Status> getAllStatus() {

        ArrayList<Status> statusList = statusRepository.findAll();

        for (int i=0; i<statusList.size(); i++) {
            Status statusItem = statusList.get(i);
            statusItem.setEmail(userService.displayUserMetaDate(statusItem.getUserId()).getEmail());
        }
        return statusList;
    }
}
