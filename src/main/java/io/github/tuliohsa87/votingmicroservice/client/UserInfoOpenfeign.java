package io.github.tuliohsa87.votingmicroservice.client;

import io.github.tuliohsa87.votingmicroservice.dto.StatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user", url = "${feing.url.user-info}")
public interface UserInfoOpenfeign {

    @GetMapping("/{cpf}")
    Optional<StatusDTO> getCpfStatus(@PathVariable String cpf);
}
