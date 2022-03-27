package com.telegrambot.telegram;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {
    Logger logger =  Logger.getLogger(BotService.class.getName());

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
                logger.info("receive -> " + text);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                LocalDate localDate = LocalDate.now();
                logger.info(dtf.format(localDate));
                sm.setText(dtf.format(localDate));
                sm.setChatId(update.getMessage().getChatId().toString());
                execute(sm);
            }
        }
    }


}
