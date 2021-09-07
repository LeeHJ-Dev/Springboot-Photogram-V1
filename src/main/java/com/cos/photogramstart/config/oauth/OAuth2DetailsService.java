package com.cos.photogramstart.config.oauth;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2DetailsService.loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String id = "facebook"+(String) attributes.get("id");
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        User userEntity = userRepository.findByUsername(id);
        if(userEntity == null){
            User saveUser = User.builder()
                    .username(id)
                    .password(new BCryptPasswordEncoder().encode("pass"))
                    .email(email)
                    .name(name)
                    .build();
            userRepository.save(saveUser);
            return new PrincipalDetails(saveUser,oAuth2User.getAttributes());
        }else{
            return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
        }





    }
}
