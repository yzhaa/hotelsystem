package com.yzh.www.factory;

import com.yzh.www.manger.Manger;
import com.yzh.www.manger.MangerImpl;
import com.yzh.www.service.*;
import com.yzh.www.serviceImpl.*;

public class ServiceFactory {
    private static Manger manger;
    private static FirstService firstService;
    private static AdministratorService administratorService;
    private static CustomService customService;
    private static BaseService baseService;
    private static ManagerService managerService;

    public static FirstService getFirstService(){
        if (firstService == null) {
            firstService = new FirstServiceImpl();
        }
        return firstService;
    }

    public static Manger getManger(){
        if (manger == null) {
            manger = new MangerImpl();
        }
        return manger;
    }

    public static AdministratorService getAdministratorService(){
        if(administratorService==null){
            administratorService = new AdministratorServiceImpl();
        }
        return administratorService;
    }

    public static CustomService getCustomService(){
        if(customService==null){
            customService = new CustomServiceImpl();
        }
        return customService;
    }

    public static BaseService getBaseService(){
        if(baseService==null){
            baseService = new BaseServiceImpl();
        }
        return baseService;
    }

    public static ManagerService getManagerService(){
        if(managerService==null){
            managerService = new ManagerServiceImpl();
        }
        return managerService;
    }
}
