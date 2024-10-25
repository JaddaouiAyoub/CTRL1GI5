package com.ejbserver;

import javax.ejb.Remote;

@Remote
public interface UserService {
        void preterCd(String titre, String utilisateur);
        void retournerCd(String titre);
}

