package org.example.carrier.global.feign.gpt;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "GptClient", url = "https://")
public interface GptClient {
}
