package com.hami.service;

import com.hami.entity.Status;

import java.util.ArrayList;

public interface StatusService {

    public Status createStatus(Status status);
    public ArrayList<Status> getAllStatus();
}
