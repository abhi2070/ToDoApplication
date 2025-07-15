package com.makeiteasy.todo.service;

import com.makeiteasy.todo.model.Todo;
import com.makeiteasy.todo.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationScheduler {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(fixedRate = 60000)
    public void checkForDueTodos(){
        System.out.println("Sending email: ");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inNext5Min = now.plusMinutes(5);

        List<Todo> dueTodos = todoRepository.findByStartDateBetween(now, inNext5Min);
        System.out.println("⏰ Found " + dueTodos.size() + " todos due between " + now + " and " + inNext5Min);

        for (Todo todo : dueTodos) {
            if (todo.getStartDate() != null && !todo.isComplete()) {
                sendReminderEmail(todo);
            }
        }
    }

    private void sendReminderEmail(Todo todo) {
        try {
            String to = todo.getUser().getEmail();
            String subject = "⏰ Task Reminder: " + todo.getTitle();
            String body = "Hey " + todo.getUser().getName() +
                    ",\n\nYour task \"" + todo.getTitle() + "\" is scheduled to start at " + todo.getStartDate() + ".\n" +
                    "Description: " + todo.getDescription() + "\n\nGood luck!";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Mail sent to: " + to + " for task: " + todo.getTitle());
        } catch (Exception e) {
            System.err.println("❌ Failed to send email to: " + todo.getUser().getEmail());
            e.printStackTrace();
        }
    }


}
