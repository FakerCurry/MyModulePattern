package com.sjw.lib_common.greendaoopr;


import com.sjw.lib_common.base.BaseApplication;
import com.sjw.lib_common.entity.User;
import com.sjw.lib_common.greendao.DaoSession;
import com.sjw.lib_common.greendao.UserDao;

import java.util.List;

/**
 * Created by pc on 2018/7/21.
 */

public class DaoManager {
    //用户
    DaoSession daoSession = BaseApplication.getIns().getDaoSession();


    private static DaoManager instance = null;

    public static synchronized DaoManager getInstance() {
        // 这个方法比上面有所改进，不用每次都进行生成对象，只是第一次
        // 使用时生成实例，提高了效率！
        if (instance == null)
            instance = new DaoManager();

        return instance;

    }


    //==================================用户表=======================================

    /**
     * 新增用户
     */
    public void insertUser(User user) {
        daoSession.getUserDao().insert(user);
    }

    /**
     * 删除用户(根据主键)
     */
    public void deleteByKeyUser(Long key) {

        daoSession.getUserDao().deleteByKey(key);

    }

    /**
     * 删除用户(根据实体类)
     */
    public void deleteByKeyUser(User user) {

        daoSession.getUserDao().delete(user);

    }

    /**
     * 删除用户(根据实体类)
     */
    public void deleteAllUser() {

        daoSession.getUserDao().deleteAll();

    }

    /**
     * 修改用户（根据用户）
     */
    public void updateUser(User user) {

        daoSession.getUserDao().update(user);

    }


    /**
     * 查询所有用户
     */
    public List<User> searchAllUser() {
        return daoSession.getUserDao().loadAll();
//        return userDao.queryBuilder().list();
    }

    /**
     * 根据主键查询用户
     */
    public User searchByKeyUser(Long key) {
        return daoSession.getUserDao().load(key);
    }

    /**
     * 根据条件查询(这里根据用户名)
     */
    public List<User> searchByConditionUser(String userName) {
        // .eq(userName)
        //queryRaw(String where,String selectionArg)：返回：List
        return daoSession
                .getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.UserName.like(userName)).list();
    }


    //==================================用户表=======================================

}
