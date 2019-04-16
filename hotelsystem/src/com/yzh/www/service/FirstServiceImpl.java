package com.yzh.www.service;

import Storage.CustomerStorage;
import Storage.UserStorage;
import com.yzh.www.dao.AdministratorUserDaoImpl;
import com.yzh.www.dao.CustomerUserDaoImpl;
import com.yzh.www.dao.UserDao;
import com.yzh.www.dao.ManagerUserDaoImpl;
import com.yzh.www.entity.Administrator;
import com.yzh.www.entity.Customer;
import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;
import com.yzh.www.util.MyAlert;
import com.yzh.www.view.*;

import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Storage.AdministratorStorage;
import Storage.MangerStorage;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class FirstServiceImpl implements FirstService {
    private UserDao userDao;
    private ManagerService managerService;
    private UserStorage userStorage;

    public boolean cheakInfo(int choice, String accont, String password) {
        if (accont.equals("")){
            return false;
        }
        int an = Integer.parseInt(accont);
        switch (choice) {
            case 1:
                       userDao = new CustomerUserDaoImpl();
                        Customer customer=(Customer) (userDao.findByAccont(an));
                        if(customer!=null){
                            userStorage = new CustomerStorage();
                           userStorage.setUser(customer);
                            if (password.equals(customer.getPassword())) {
                                return true; }
                        }
                         break;
            case 2:
                userDao= new ManagerUserDaoImpl();
                Manager manager=(Manager) (userDao.findByAccont(an));
                if(manager!=null){
                    userStorage = new MangerStorage();
                    userStorage.setUser(manager);
                    if (password.equals(manager.getPassword())) {
                        return true;}
                }
                break;
            case 3:
                userDao = new AdministratorUserDaoImpl();
                Administrator admi = (Administrator) userDao.findByAccont(an);
                if (admi != null) {
                    userStorage = new AdministratorStorage();
                    userStorage.setUser(admi);
                    if (password.equals(admi.getPassword())) {
                        return true;
                    }
                }
                break;
            }
            return false;
    }

    public void updataScene(int choice, Stage stage) {
        switch (choice){
            case 1:
                CustomView cv = new CustomView(stage);
                stage.setScene(cv.fistScene());
                stage.setTitle("Weaclome to here");
                break;
            case 2:
               managerService = new ManagerServiceImpl();
               if(managerService .cheakHotel()){
                   ManagerView mv = new ManagerView();
                   stage.setScene(mv.creatScene());
                   stage.setTitle("Weaclome Manager");break;
               }
                break;
            case 3:
                AdministratorView av = new AdministratorView();
                stage.setScene(av.creatScene());
                stage.setTitle("Weaclome Administrator");break;
        }

    }

    public void retainUserInfo(String accont, String password, boolean isSelcet) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("resoures\\userinfo.properties");
            bw = new BufferedWriter(fw);
            if (isSelcet) {
                bw.write("state=true");
                bw.newLine();
                bw.write("accont=" + accont);
                bw.newLine();
                bw.write("password=" + password);
            } else bw.write("state=false");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public boolean changeUserInfo(ChangeInfoView changeInfoView) {
        userDao = null;
        User user = changeInfoView.getUserStorage().getUser();
        if(changeInfoView.getUserStorage().getUser()instanceof Customer){
            userDao = new CustomerUserDaoImpl();
        }
        else if (changeInfoView.getUserStorage().getUser() instanceof Manager){
            userDao =new ManagerUserDaoImpl();
        }
        else if(changeInfoView.getUserStorage().getUser()instanceof Administrator){
            userDao = new AdministratorUserDaoImpl();
        }
        TextField[] tfs = changeInfoView.getTextFields();
        if(tfs[0].getText()!=null&&tfs[1].getText()!=null&&tfs[2].getText() != null&&tfs[3].getText() !=null){
            if (tfs[0].getText().equals("") || tfs[1].getText().equals("") || tfs[2].getText().equals("") ||
                    tfs[3].getText().equals("") || tfs[1].getText().length() !=11 || tfs[2].getText().length() !=18) {
                MyAlert.setAlert("请填入正确的格式", 0);
                return false;
            }
        } else {
            MyAlert.setAlert("请填入完整",0);
            return false;
        }
        user.setUserName(tfs[0].getText());
        user.setPhoneNumber(tfs[1].getText());
        user.setIdCard(tfs[2].getText());
        user.setPassword(tfs[3].getText());
            return userDao.update(user);
    }

    public void geLoginInfo(TextField textField, PasswordField passwordField, RadioButton radioButton) {
        FileInputStream fis = null;
        Properties p;
        try {
            fis = new FileInputStream("resoures\\userinfo.properties");
            p = new Properties();
            p.load(fis);
            if (p.getProperty("state").equals("true"))
                radioButton.setSelected(true);
            textField.setText(p.getProperty("accont"));
            passwordField.setText(p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public boolean addUser(RegistView rv) {
        if(rv.getTextField().getText()==null||rv.getTextField().getText().equals("")||rv.getChoice()==0){
            MyAlert.setAlert("输入帐号或者选择注册类型",0);
            return false;
        }

        int an = Integer.parseInt(rv.getTextField().getText());

        if(rv.getPassword().getText()!=null&&rv.getPasswordField()!=null&&
                rv.getPassword().getText().equals(rv.getPasswordField().getText())) {
            switch (rv.getChoice()) {
                case 1:
                    userDao= new CustomerUserDaoImpl();
                    if (userDao.findByAccont(an) == null) {
                        Customer customer = new Customer();
                        customer.setAccont(an);
                        customer.setPassword(rv.getPassword().getText());
                        MyAlert.setAlert("注册成功", 1);
                        rv.getStage().close();
                        return userDao.add(customer);
                    } else {
                       MyAlert.setAlert("该用户已存在",0);
                    }
                    break;
                case 2:
                   userDao = new ManagerUserDaoImpl();
                    if (userDao.findByAccont(an) == null) {
                        Manager manager = new Manager();
                        manager.setAccont(an);
                        manager.setPassword(rv.getPassword().getText());
                        MyAlert.setAlert("注册成功",1);
                        rv.getStage().close();
                        return userDao.add(manager);
                    } else {
                        MyAlert.setAlert("该用户已存在",0);
                    }
                    break;
            }
        }
        else {
            MyAlert.setAlert("密码不一致或密码为空",0);
        }
        return false;
    }

}
