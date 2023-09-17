package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Configuration
@SuppressWarnings("unused")
public class ChannelWithInjectedPrototypeVideoConfig extends Channel{
    private LocalDateTime time = LocalDateTime.now();
    @Bean
    @Scope("prototype")
    public Video getBeanVideo() {
        Video video = new Video("Cat Failure Compilation", time);
        time = time.plusDays(1);
        return video;
    }

    @Override
    public Stream<Video> videos(){
        Channel channel = new Channel();
        for (int i = 0; i < 7; i++) {
            channel.addVideo(getBeanVideo());
        }
        return channel.videos();
    }

}
