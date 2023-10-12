package com.mily.milyx;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MilyXService {
    private final MilyXRepository milyXRepository;

    public void create (String title, String body) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        String nowDate = now.format(dtf);
        MilyX mx = new MilyX();

        mx.setTitle(title);
        mx.setBody(body);
        mx.setCreateDate(nowDate);
        this.milyXRepository.save(mx);
    }
}