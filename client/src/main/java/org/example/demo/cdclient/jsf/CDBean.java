package org.example.demo.cdclient.jsf;




import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.example.demo.cdclient.service.CD;
import org.example.demo.cdclient.service.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;


@Named("cdBean")
@RequestScoped
public class CDBean {

    private UserService userService;

    private String utilisateur;
    private String titreCd;
    private List<CD> cdsDisponibles;
    private List<CD> cdsEmpruntes;

    public CDBean() {
        try {
            Properties jndiProperties = new Properties();
            jndiProperties.put("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put("java.naming.provider.url", "http-remoting://localhost:8082"); // Replace localhost with WildFly server IP
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext();
            userService = (UserService) context.lookup("ejb:/myserver-1.0-SNAPSHOT/DateManagerImpl!com.ejbserver.UserService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public List<CD> getCdsDisponibles() {
        return userService.listerCdsDisponibles();
    }

    public void emprunterCd() {
        userService.preterCd(titreCd, utilisateur);
    }

    public List<CD> getCdsEmpruntes() {
        return userService.listerCdsEmpruntes(utilisateur);
    }

    // Getters et Setters pour utilisateur, titreCd

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getTitreCd() {
        return titreCd;
    }

    public void setTitreCd(String titreCd) {
        this.titreCd = titreCd;
    }
}
