package com.liuli.controller.retry;


import com.liuli.dynamic.service.impl.AbsProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DelayProducerService extends AbsProducerService {
}
