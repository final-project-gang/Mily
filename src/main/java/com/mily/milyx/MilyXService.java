package com.mily.milyx;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilyXService {
    private final MilyXRepository milyXRepository;
}