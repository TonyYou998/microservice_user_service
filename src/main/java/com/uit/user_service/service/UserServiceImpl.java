package com.uit.user_service.service;

import com.uit.user_service.common.jwt.JwtUtils;
import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import com.uit.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private  ModelMapper mapper;
    private JwtUtils jwtUtils;

//    private BCryptPasswordEncoder encoder;
    private PasswordEncoder encoder;

    private JavaMailSender mailSender;
    @Override
    public UserDto createUser(CreateUserDto dto, String siteURL) {
        User newUser=new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setFirstname(dto.getFirstname());
        newUser.setPassword(dto.getPassword());
        newUser.setLastname(dto.getLastname());
        newUser.setPhone(dto.getPhone());
        newUser.setAddress(dto.getAddress());
        newUser.setRole("User");
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setIsActive(false);

        String randomCode = RandomString.make(64);
        newUser.setVerificationCode(randomCode);
        newUser.setEnabled(false);

//        newUser.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        try{
            userRepository.save(newUser);
            sendVerificationEmail(newUser, siteURL);
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
        }
        return mapper.map(newUser, UserDto.class);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {

        try {
            System.out.println(user.getEmail());
            String toAddress = user.getEmail();
            String fromAddress = "datthanhphan22@gmail.com";
            String senderName = "Your company name";
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "Your company name.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getEmail());
            String verifyURL = "http://localhost:8080/api/v1/user" + "/verify?code=" + user.getVerificationCode();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }

    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }


    @Override
    public UserDto validateToken(String token) {
        return  jwtUtils.validateJwtToken(token);

    }

    @Override
    public User getUserInfoByUuid(String uuid) {
        return userRepository.findById(UUID.fromString(uuid)).get();
    }

    @Override
    public User saveUser(User u) {
        return userRepository.save(u);
    }
}
