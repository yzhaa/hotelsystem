package com.yzh.www.serviceImpl;

import Storage.CustomerStorage;
import Storage.UserStorage;
import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Administrator;
import com.yzh.www.entity.Customer;
import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;
import com.yzh.www.factory.DaoFactory;
import com.yzh.www.factory.ServiceFactory;
import com.yzh.www.service.FirstService;
import com.yzh.www.service.ManagerService;
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


    public boolean cheakInfo(int choice, String accont, String password) {
        if (accont.equals("")){
            return false;
        }
        int an = Integer.parseInt(accont);
        UserStorage userStorage;
        switch (choice) {
            case 1:
                userDao = DaoFactory.getCustomerUserDao();
                Customer customer = (Customer) (userDao.findByAccont(an));
                if (customer != null) {
                    userStorage = new CustomerStorage();
                    userStorage.setUser(customer);
                    if (password.equals(customer.getPassword())) {
                        return true;
                    }
                }
                break;
            case 2:
                userDao = DaoFactory.getManagerUserDao();
                Manager manager=(Manager) (userDao.findByAccont(an));
                if(manager!=null){
                    userStorage = new MangerStorage();
                    userStorage.setUser(manager);
                    if (password.equals(manager.getPassword())) {
                        return true;}
                }
                break;
            case 3:
                userDao = DaoFactory.getAdministratorUserDao();
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

    public int updataScene(int choice, Stage stage) {
        switch (choice){
            case 1:
                CustomView cv = new CustomView(stage);
                stage.setScene(cv.fistScene());
                return 1;
            case 2:
                ManagerService managerService = ServiceFactory.getManagerService();
               if(managerService .cheakHotel()){
                   ManagerView mv = new ManagerView();
                   stage.setScene(mv.creatScene());
                   return 2;
               }
               else return 3;
            case 3:
                AdministratorView av = new AdministratorView();
                stage.setScene(av.creatScene());
                return 4;
        }
        return 0;
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


    public int changeUserInfo(ChangeInfoView changeInfoView) {
        userDao = null;
        User user = changeInfoView.getUserStorage().getUser();
        if(changeInfoView.getUserStorage().getUser()instanceof Customer){
            userDao = DaoFactory.getCustomerUserDao();
        }
        else if (changeInfoView.getUserStorage().getUser() instanceof Manager){
            userDao = DaoFactory.getManagerUserDao();
        }
        else if(changeInfoView.getUserStorage().getUser()instanceof Administrator){
            userDao = DaoFactory.getAdministratorUserDao();
        }
        TextField[] tfs = changeInfoView.getTextFields();
        if(tfs[0].getText()!=null&&tfs[1].getText()!=null&&tfs[2].getText() != null&&tfs[3].getText() !=null){
            if (tfs[0].getText().equals("") || tfs[1].getText().equals("") || tfs[2].getText().equals("") ||
                    tfs[3].getText().equals("") || tfs[1].getText().length() !=11 || tfs[2].getText().length() !=18) {
                return 1;
            }
        } else {
            return 2;
        }
        user.setUserName(tfs[0].getText());
        user.setPhoneNumber(tfs[1].getText());
        user.setIdCard(tfs[2].getText());
        user.setPassword(tfs[3].getText());
        userDao.update(user);
        return 3;
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



    public int addUser(RegistView rv) {
        if(rv.getTextField().getText()==null||rv.getTextField().getText().equals("")||rv.getChoice()==0){
            return 1;
        }

        int an = Integer.parseInt(rv.getTextField().getText());

        if (rv.getPassword().getText() != null && rv.getPasswordField().getText() != null && !rv.getPassword().getText().equals("")
                && rv.getPasswordField().getText().equals(rv.getPassword().getText())) {
            switch (rv.getChoice()) {
                case 1:
                    userDao = DaoFactory.getCustomerUserDao();
                    if (userDao.findByAccont(an) == null) {
                        Customer customer = new Customer();
                        customer.setAccont(an);
                        customer.setPassword(rv.getPassword().getText());
                        rv.getStage().close();
                       if( userDao.add(customer))
                        return 2;
                    }
                    return 3;
                case 2:
                    userDao = DaoFactory.getManagerUserDao();
                    if (userDao.findByAccont(an) == null) {
                        Manager manager = new Manager();
                        manager.setAccont(an);
                        manager.setPassword(rv.getPassword().getText());
                        rv.getStage().close();
                       if( userDao.add(manager))
                        return 2;
                    }
                    return 2;
            }
        }
        return 0;
    }

}
