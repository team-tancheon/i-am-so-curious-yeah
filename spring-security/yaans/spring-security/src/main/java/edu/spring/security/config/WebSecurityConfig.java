package edu.spring.security.config;

import edu.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security를 사용하기 위한 설정 파일
 * WebSecurityConfigurerAdapter 상속
 */
@RequiredArgsConstructor
@EnableWebSecurity          // Spring Security 사용
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder pwdEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증에서 제외할 경로 설정 (Spring Security가 무시)
     * WebSecurity : FilterChainProxy 생성 필터
     */
    @Override
    public void configure(WebSecurity web){
        // 파일 기준 : resources/static
        web.ignoring().antMatchers("/css/**");
    }

    /**
     * http 관련 인증 설정
     * HttpSecurity : Http 요청에 대한 웹 기반 보안 구성
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // TAB Level
        //  .[연결]
        //      .[메서드]
        //          .[메서드 디테일]
        http
                .authorizeRequests()            
                    .antMatchers("/login","signup").permitAll()
//                    .antMatchers("/").hasAnyRole("USER")
                    .antMatchers("/admin").hasAnyRole("ADMIN")
                    .anyRequest().authenticated()
            /*
             * authorizeRequests()  : 경로에 따른 인증 설정
             * ----------------------
             * anyMatchers()        : 경로 패턴 지정
             * permitAll()          : 누구나 접근 가능
             * hasRole()            : 특정 권한이 있는 사람만 접근 가능
             * authenticated()      : 권한이 있으면 접근 가능
             * anyRequest()         : anyMatchers에서 설정하지 않은 나머지 경로
             */
            .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("id")        // fixme 맞나??
             /* 
              * formLogin()         : 로그인에 관한 설정
              * ---------------------
              * loginPage()         : 로그인 페이지 링크
              *        FIXME : 로그인의 form action 경로와 loginPage()의 경로가 일치해야 한다.
              * defaultSuccessUrl() : 로그인 성공 후 리다이렉트 주소
              */
            .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
            /*
             * logout()             : 로그아웃에 관한 설정
             * ----------------------
             * logoutSuccessUrl()   : 로그아웃 성공 후 리다이렉트할 주소
             * invalidateHttpSession()  : 로그아웃 이후 세션 전체 삭제 여부
             */
            .and()
                    .exceptionHandling().accessDeniedPage("/denied")
            /*
             * exceptionHandling()  :  예외 발생시 핸들링
             */
        ;
    }

    /**
     * 로그인할 때 필요한 정보 가져오기
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(pwdEncoder());
    }
}
