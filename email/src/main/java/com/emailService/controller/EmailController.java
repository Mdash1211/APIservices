package com.emailService.controller;


import com.emailService.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


 //API TEST URI:http://localhost:8080/sendEmail?to=recipient@example.com&subject=Test&text=This+is+a+test+email.

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam String to,
                                            @RequestParam String subject,
                                            @RequestParam String text){
        try {
            emailService.sendMailSender(to, subject, text);
            return new ResponseEntity<>("Email sent successfully,please check inbox", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
