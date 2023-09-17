package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@SuppressWarnings("unused")
public class ChannelWithVideoStudioConfig {
    @Bean
    public VideoStudio videoStudio(){
        return new VideoStudio() {
            private int currentMovieNum = 1;

            @Override
            public Video produce() {
                String movieName = "Cat & Curious " + currentMovieNum;
                long extraYears = 2L * (currentMovieNum++ - 1);
                LocalDateTime releaseDate = LocalDateTime
                        .of(2001, 10, 18, 10, 0)
                        .plusYears(extraYears);

                return new Video(movieName, releaseDate);
            }
        };
    }

    @Bean
    public Channel channel(){
        Channel channel = new Channel();
        VideoStudio vs = videoStudio();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(vs.produce());
        }
        return channel;
    }
}
