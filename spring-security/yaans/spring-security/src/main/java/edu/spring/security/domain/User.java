package edu.spring.security.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "security_user")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "password")
    String password;

    @Column(name = "user_role")
    String userRole;


    /**
     * 사용자 권한을 콜렉션 형태로 반환
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String r : userRole.split(",")){
            roles.add(new SimpleGrantedAuthority(userRole));
        }
        return roles;
    }

    /**
     * 사용자의 id(unique)를 반환
     */
    @Override
    public String getUsername() {
        return id;
    }

    /**
     * 계정 만료 여부 반환
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;        // -> True : 만료
    }

    /**
     * 계정 잠금 여부 반환
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;        // True -> 안잠김
    }

    /**
     *  비밀번호의 만료 여부 반환
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;       // True : 만료되지 않음
    }

    /**
     * 계정 사용 가능 여부 반환
     */
    @Override
    public boolean isEnabled() {
        return false;       // True : 사용 가능
    }
    //
    //https://mangkyu.tistory.com/77
}
