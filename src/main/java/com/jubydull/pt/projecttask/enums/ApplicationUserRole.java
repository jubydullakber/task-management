package com.jubydull.pt.projecttask.enums;

import com.google.common.collect.Sets;
import com.jubydull.pt.projecttask.security.ApplicationUserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;



public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

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
