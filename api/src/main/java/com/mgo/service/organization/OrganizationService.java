package com.mgo.service.organization;

import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Organization;

public class OrganizationService {
    public static void deleteOrganization(AuthenticatedUser user,Organization organization){
        organization.delete();
    }
}
