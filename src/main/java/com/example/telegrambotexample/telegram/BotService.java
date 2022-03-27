package com.example.telegrambotexample.telegram;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                SendMessage sm = new SendMessage();
                sm.setText("Hello " + text);
                sm.setChatId(update.getMessage().getChatId().toString());
                execute(sm);
            }
        }
    }


}
