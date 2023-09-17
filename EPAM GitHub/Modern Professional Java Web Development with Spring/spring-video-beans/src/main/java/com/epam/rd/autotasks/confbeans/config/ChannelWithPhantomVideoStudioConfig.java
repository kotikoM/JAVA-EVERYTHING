package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
@SuppressWarnings("unused")
public class ChannelWithPhantomVideoStudioConfig {
    private int currentMovieNum = 1;

    @Bean
    @Scope("prototype")
    public Video video() {
        long extraYears = 2L * (currentMovieNum - 1);
        return new Video("Cat & Curious " + currentMovieNum++, LocalDateTime
                .of(2001, 10, 18, 10, 0)
                .plusYears(extraYears));
    }

    @Bean
    public Channel channel() {
        Channel channel = new Channel();

        for (int i = 1; i <= 8; i++) {
            channel.addVideo(video());
        }
        return channel;
    }
}
