package mymvc.service;

import mymvc.DBUtils;
import mymvc.dao.SystemAdminDao;
import mymvc.entity.SystemAdmin;

import java.sql.Connection;
import java.sql.SQLException;

public class SystemAdminService {
    public SystemAdmin register(SystemAdmin systemAdmin){
        DBUtils.startTrans();
        SystemAdminDao systemAdminDao = new SystemAdminDao();
        int insert = systemAdminDao.insert(systemAdmin);
        if(insert > 10 || insert == 0){
            DBUtils.rollback();
            return null;
        }
        DBUtils.commit();
        return systemAdmin;
    }
}
