package com.yzh.www.entity;

import com.yzh.www.service.ManagerServiceImpl;
import javafx.beans.property.SimpleStringProperty;

public class Hotel {
    private int id;
    private String name;
    private String type;
    private String info;
    private Manager manager;
    private int managerId;
    private String managerAccont;
    private SimpleStringProperty nameProperty;
    private SimpleStringProperty typeProperty;
    private SimpleStringProperty infoProperty;
    private SimpleStringProperty accontProperty;



    public Hotel() {
        super();
    }

    public Hotel(int id, String name, String type, String info, int managerId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.info = info;
        this.managerId = managerId;
    }


    public Hotel( int id, String name, String type, String info, String managerAccont){
        this.id = id;
        this.name = name;
        this.type = type;
        this.info = info;
        infoProperty = new SimpleStringProperty(info);
        nameProperty = new SimpleStringProperty(name);
        typeProperty = new SimpleStringProperty(type);
        judgeAccont(managerAccont);
    }

    public void continueInit() {
        if (managerId != 0) {
            this.manager = new ManagerServiceImpl().findManaById(managerId);
            accontProperty = new SimpleStringProperty(this.manager.getAccont() + "");
        }
        nameProperty = new SimpleStringProperty(name);
        typeProperty = new SimpleStringProperty(type);
        infoProperty = new SimpleStringProperty(info);
    }

    public boolean judgeAccont(String managerAccont){
        if (!managerAccont.equals("")) {
            this.manager = new ManagerServiceImpl().findManaByAccont(Integer.parseInt(managerAccont));
            if(manager!=null){
                this.managerId = manager.getId();
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId ( int id){
            this.id = id;
    }

    public String getName() {

        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public void setManagerAccont(String managerAccont) {
        judgeAccont(managerAccont);
        this.managerAccont = managerAccont;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getType() {
        return type;
    }


    public String getInfo() {
        return info;
    }

    public SimpleStringProperty namePropertyProperty() {
        return nameProperty;
    }

    public SimpleStringProperty typePropertyProperty() {
        return typeProperty;
    }


    public SimpleStringProperty infoPropertyProperty() {
        return infoProperty;
    }

    public SimpleStringProperty accontPropertyProperty() {
        return accontProperty;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object hotel) {
        if (hotel != null && this.getId()==(((Hotel)hotel).getId())) {
            return true;
        }
        return false;
    }

}
