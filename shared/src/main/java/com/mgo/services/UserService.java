package com.mgo.services;

import com.mgo.ConnectedUser;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.SecurityContext;

@ApplicationScoped
public class UserService {
        public ConnectedUser getConnectedUser(SecurityContext securityContext) {
                /*Companyemployee companyemployee = Companyemployee
                                .find("username = :username", Parameters.with("username", securityContext
                                                .getUserPrincipal().getName()))
                                .firstResult();*/
                ConnectedUser connectedUser = new ConnectedUser();
                System.out.println(securityContext.getUserPrincipal().getName());
                System.out.println(securityContext.getUserPrincipal().toString());

               /* connectedUser.setCompany(companyemployee.getCompany().getName());
                connectedUser.setIdcompany(companyemployee.getCompany().getIdcompany());
                connectedUser.setContact(companyemployee.getContact());
                connectedUser.setCin(companyemployee.getCin());
                connectedUser.setName(companyemployee.getName());
                connectedUser.setUsername(companyemployee.getUsername());
                connectedUser.setIdcompanyagency(
                                companyemployee.getCompanyagency() != null
                                                ? companyemployee.getCompanyagency().getIdcompanyagency()
                                                : null);
                connectedUser.setCompanyagency(companyemployee.getCompanyagency() != null
                                && companyemployee.getCompanyagency().getName() != null
                                                ? companyemployee.getCompanyagency().getName()
                                                : companyemployee.getCompany().getName());*/
                return connectedUser;

        }
}
