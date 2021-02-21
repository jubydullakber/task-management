package com.jubydull.pt.projecttask.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jubydull.pt.projecttask.security.ApplicationUserAuthority.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ,USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE,ADMIN_READ,ADMIN_WRITE));

    private final Set<ApplicationUserAuthority> permissions;

    ApplicationUserRole(Set<ApplicationUserAuthority> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserAuthority> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
