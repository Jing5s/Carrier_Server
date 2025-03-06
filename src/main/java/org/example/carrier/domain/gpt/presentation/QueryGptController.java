package org.example.carrier.domain.gpt.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.gpt.service.QueryGptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/gpts")
@RestController
public class QueryGptController {
    private final QueryGptService queryGptService;

}
