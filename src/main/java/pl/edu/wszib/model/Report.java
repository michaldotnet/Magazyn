package pl.edu.wszib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String pointOfSales;
    private String whenWasLogged;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPointOfSales() {
        return pointOfSales;
    }

    public void setPointOfSales(String pointOfSales) {
        this.pointOfSales = pointOfSales;
    }

    public String getWhenWasLogged() {
        return whenWasLogged;
    }

    public void setWhenWasLogged(String whenWasLogged) {
        this.whenWasLogged = whenWasLogged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
