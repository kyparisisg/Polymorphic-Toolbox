package com.temple.polymorphic.toolbox.services;

import com.temple.polymorphic.toolbox.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    //All the functionality for permissions


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PermissionRepository permissionRepository;


    //Services for User's permissions
}
